package de.arnav.studl.service.implementation;

import de.arnav.studl.adapter.OrganizationAdapter;
import de.arnav.studl.adapter.UserAdapter;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.userDto.UserCreateDto;
import de.arnav.studl.dto.userDto.UserDeleteDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userDto.UserUpdateDto;
import de.arnav.studl.exception.DuplicateUserException;
import de.arnav.studl.exception.InvalidCredentialsException;
import de.arnav.studl.exception.JwtAuthenticationException;
import de.arnav.studl.exception.UserNotFoundException;
import de.arnav.studl.model.Organization;
import de.arnav.studl.model.RoleType;
import de.arnav.studl.model.User;
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
    private final JwtService jwtService;
    private final OrganizationAdapter organizationAdapter;

    public UserServiceImpl(UserAdapter userAdapter, UserJpaRepository userJpaRepository, CustomLogicService customLogicService, PasswordEncoder passwordEncoder, JwtService jwtService, OrganizationAdapter organizationAdapter) {
        this.userAdapter = userAdapter;
        this.userJpaRepository = userJpaRepository;
        this.customLogicService = customLogicService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.organizationAdapter = organizationAdapter;
    }

    // Should i also check if mail, name, password etc are not null or will that be ensured by front-end?
    @Transactional
    @Override
    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        if (userJpaRepository.existsByUserEmail(userCreateDto.getEmail())) {
            throw new DuplicateUserException();
        }
        User user = userAdapter.fromCreateDto(userCreateDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<RoleType> roles = customLogicService.assignRoles(user.getUserEmail());
        user.setRoleType(roles);
        User savedUser = userJpaRepository.save(user);
        return userAdapter.toResponseDto(savedUser);
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(UserUpdateDto userUpdateDto, Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException();
        }
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());
        if(userUpdateDto.getName() != null) {
            user.setUserName(userUpdateDto.getName());
        }
        if(userUpdateDto.getEmail() != null) {
            user.setUserEmail(userUpdateDto.getEmail());
        }
        if(userUpdateDto.getNewPassword() != null && userUpdateDto.getOldPassword() != null) {
            if (!passwordEncoder.matches(userUpdateDto.getOldPassword(), user.getPassword())) {
                throw new InvalidCredentialsException();
            }
            user.setPassword(passwordEncoder.encode(userUpdateDto.getNewPassword()));
        }
        User savedUser = userJpaRepository.save(user);
        return userAdapter.toResponseDto(savedUser);
    }

    @Transactional
    @Override
    public void deleteUser(UserDeleteDto userDeleteDto) {
        String token = userDeleteDto.getJwtToken();
        if (token == null || token.isBlank()) {
            throw new JwtAuthenticationException();
        }
        String email = jwtService.extractEmail(token);
        if (email == null) {
            throw new JwtAuthenticationException();
        }
        userJpaRepository.deleteByEmail(email);
    }


    @Transactional
    @Override
    public UserResponseDto removeAllRolesFromUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException();
        }
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());
        user.getRoleType().clear();
        User savedUser = userJpaRepository.save(user);
        return userAdapter.toResponseDto(savedUser);
    }

    @Override
    public UserResponseDto findUserById(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException();
        }
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());
        return userAdapter.toResponseDto(user);
    }

    @Override
    public UserResponseDto findUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException();
        }
        User user = userJpaRepository.findByUserEmail(email).orElseThrow(()-> new UserNotFoundException());
        if (user == null) {
            throw new UserNotFoundException();
        }
        return userAdapter.toResponseDto(user);
    }

    @Override
    public List<UserResponseDto> findUsersByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException();
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
            throw new IllegalArgumentException();
        }
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());
        Organization organization = user.getOrganization();
        return organizationAdapter.toResponseDto(organization);
    }

}