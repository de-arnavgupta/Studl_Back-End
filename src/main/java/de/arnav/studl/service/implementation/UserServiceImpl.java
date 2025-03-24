package de.arnav.studl.service.implementation;

import de.arnav.studl.adapter.OrganizationAdapter;
import de.arnav.studl.adapter.UserAdapter;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.userDto.UserCreateDto;
import de.arnav.studl.dto.userDto.UserDeleteDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userDto.UserUpdateDto;
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

    @Transactional
    @Override
    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        User user = userAdapter.fromCreateDto(userCreateDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<RoleType> roles = customLogicService.assignRoles(user.getEmail());
        user.setRoles(roles);
        User savedUser = userJpaRepository.save(user);
        return userAdapter.toResponseDto(savedUser);
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(UserUpdateDto userUpdateDto, Long userId) {
        User user = userJpaRepository.findById(userId).orElseThrow();
        if(userUpdateDto.getName() != null) {
            user.setName(userUpdateDto.getName());
        }
        if(userUpdateDto.getEmail() != null) {
            user.setEmail(userUpdateDto.getEmail());
        }
        if(userUpdateDto.getNewPassword() != null && userUpdateDto.getOldPassword() != null) {
            if(user.getPassword().equals(passwordEncoder.encode(userUpdateDto.getOldPassword()))) {
                user.setPassword(passwordEncoder.encode(userUpdateDto.getNewPassword()));
            }
        }
        User savedUser = userJpaRepository.save(user);
        return userAdapter.toResponseDto(savedUser);
    }

    @Transactional
    @Override
    public void deleteUser(UserDeleteDto userDeleteDto) {
        String token = userDeleteDto.getJwtToken();
        String email = jwtService.extractEmail(token);
        userJpaRepository.deleteByEmail();
    }

    @Override
    public UserResponseDto removeRoleFromUser(Long userId, RoleType roleType) {
        User user = userJpaRepository.findById(userId).orElseThrow();
        user.getRoles().remove(roleType);
        User savedUser = userJpaRepository.save(user);
        return userAdapter.toResponseDto(savedUser);
    }

    @Transactional
    @Override
    public UserResponseDto removeAllRolesFromUser(Long userRoleId) {
        User user = userJpaRepository.findById(userRoleId).orElseThrow();
        user.getRoles().clear();
        User savedUser = userJpaRepository.save(user);
        return userAdapter.toResponseDto(savedUser);
    }

    @Override
    public UserResponseDto findUserById(Long userId) {
        User user = userJpaRepository.findById(userId).orElseThrow();
        return userAdapter.toResponseDto(user);
    }

    @Override
    public UserResponseDto findUserByEmail(String email) {
        User user = userJpaRepository.findByEmail(email);
        return userAdapter.toResponseDto(user);
    }

    @Override
    public List<UserResponseDto> findUsersByUsername(String username) {
        List<User> users = userJpaRepository.findByUsername(username);
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
        User user = userJpaRepository.findById(userId).orElseThrow();
        Organization organization = user.getOrganization();
        return organizationAdapter.toResponseDto(organization);
    }

    @Override
    public Boolean userHasRole(Long userId, RoleType roleType) {
        User user = userJpaRepository.findById(userId).orElseThrow();
        return user.getRoles().contains(roleType);
    }

}