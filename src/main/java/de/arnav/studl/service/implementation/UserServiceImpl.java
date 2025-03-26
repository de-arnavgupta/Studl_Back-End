package de.arnav.studl.service.implementation;

import de.arnav.studl.adapter.OrganizationAdapter;
import de.arnav.studl.adapter.UserAdapter;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.userDto.UserCreateDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userDto.UserUpdateDto;
import de.arnav.studl.exception.DuplicateUserException;
import de.arnav.studl.exception.InvalidCredentialsException;
import de.arnav.studl.exception.OrganizationNotFoundException;
import de.arnav.studl.exception.UserNotFoundException;
import de.arnav.studl.model.Organization;
import de.arnav.studl.model.RoleType;
import de.arnav.studl.model.User;
import de.arnav.studl.repository.OrganizationJpaRepository;
import de.arnav.studl.repository.UserJpaRepository;
import de.arnav.studl.security.service.CustomLogicService;
import de.arnav.studl.security.service.JwtService;
import de.arnav.studl.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserAdapter userAdapter;
    private final UserJpaRepository userJpaRepository;
    private final CustomLogicService customLogicService;
    private final PasswordEncoder passwordEncoder;
    private final OrganizationJpaRepository organizationJpaRepository;
    private final OrganizationAdapter organizationAdapter;

    public UserServiceImpl(UserAdapter userAdapter, UserJpaRepository userJpaRepository, CustomLogicService customLogicService, PasswordEncoder passwordEncoder, OrganizationJpaRepository organizationJpaRepository, OrganizationAdapter organizationAdapter) {
        this.userAdapter = userAdapter;
        this.userJpaRepository = userJpaRepository;
        this.customLogicService = customLogicService;
        this.passwordEncoder = passwordEncoder;
        this.organizationJpaRepository = organizationJpaRepository;
        this.organizationAdapter = organizationAdapter;
    }

    // Should i also check if mail, name, password etc are not null or will that be ensured by front-end?
    @Transactional
    @Override
    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        if (userJpaRepository.existsByUserEmail(userCreateDto.getEmail())) {
            throw new DuplicateUserException("User with email " + userCreateDto.getEmail() + " already exists. [Method: createUser]");
        }
        User user = userAdapter.fromCreateDto(userCreateDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<RoleType> roles = customLogicService.assignRoles(user.getUserEmail());
        if (roles.isEmpty()) {
            throw new InvalidCredentialsException("You're not authorized to create an account. Pls use the appropriate organization email address. [Method: createUser]");
        }
        user.setRoleType(roles);
        String domain = getDomainFromEmail(user.getUserEmail());
        Organization organization = organizationJpaRepository.findByDomainName(domain)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization with domain " + domain + " not found. [Method: createUser]"));
        user.setOrganization(organization);
        User savedUser = userJpaRepository.save(user);
        return userAdapter.toResponseDto(savedUser);
    }

    private String getDomainFromEmail(String email) {

        String domain = email.substring(email.indexOf("@") + 1); // Extracts domain (sst.scaler.com)
        String[] parts = domain.split("\\.");

        if (parts.length >= 2) {
            return parts[parts.length - 2]; // Extract the second-last part (scaler)
        } else {
            return domain; //if domain is not structured as expected
        }
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(UserUpdateDto userUpdateDto, Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null. [Method: updateUser]");
        }
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found. [Method: updateUser]"));
        if(userUpdateDto.getName() != null) {
            user.setUserName(userUpdateDto.getName());
        }
        if(userUpdateDto.getEmail() != null) {
            user.setUserEmail(userUpdateDto.getEmail());
        }
        if(userUpdateDto.getNewPassword() != null && userUpdateDto.getOldPassword() != null) {
            if (!passwordEncoder.matches(userUpdateDto.getOldPassword(), user.getPassword())) {
                throw new InvalidCredentialsException("Incorrect old password. [Method: updateUser]");
            }
            user.setPassword(passwordEncoder.encode(userUpdateDto.getNewPassword()));
        }
        User savedUser = userJpaRepository.save(user);
        return userAdapter.toResponseDto(savedUser);
    }

    @Override
    public void deleteUser(String email) {
        if (email == null) {
            throw new InvalidCredentialsException("Invalid email address. [Method: deleteUser]");
        }
        userJpaRepository.deleteByUserEmail(email);
    }

    @Transactional
    @Override
    public UserResponseDto removeAllRolesFromUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null. [Method: removeAllRolesFromUser]");
        }
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found. [Method: removeAllRolesFromUser]"));
        user.getRoleType().clear();
        User savedUser = userJpaRepository.save(user);
        return userAdapter.toResponseDto(savedUser);
    }

    @Override
    public UserResponseDto findUserById(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null. [Method: findUserById]");
        }
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found. [Method: findUserById]"));
        return userAdapter.toResponseDto(user);
    }

    @Override
    public UserResponseDto findUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null. [Method: findUserByEmail]");
        }
        User user = userJpaRepository.findByUserEmail(email).orElseThrow(()-> new UserNotFoundException("User with email " + email + " not found. [Method: findUserByEmail]"));
        return userAdapter.toResponseDto(user);
    }

    @Override
    public List<UserResponseDto> findUsersByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null. [Method: findUsersByUsername]");
        }
        List<User> users = userJpaRepository.findByUserName(username);
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for(User user : users) {
            userResponseDtos.add(userAdapter.toResponseDto(user));
        }
        return userResponseDtos;
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        List<User> users = userJpaRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for(User user : users) {
            userResponseDtos.add(userAdapter.toResponseDto(user));
        }
        return userResponseDtos;
    }

    @Override
    public OrganizationResponseDto findOrganizationByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null. [Method: findOrganizationByUserId]");
        }
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found. [Method: findOrganizationByUserId]"));
        Organization organization = user.getOrganization();
        return organizationAdapter.toResponseDto(organization);
    }

}