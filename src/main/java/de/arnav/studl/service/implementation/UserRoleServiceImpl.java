package de.arnav.studl.service.implementation;

import de.arnav.studl.adapter.UserAdapter;
import de.arnav.studl.adapter.UserRoleAdapter;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userRoleDto.UserRoleResponseDto;
import de.arnav.studl.model.RoleType;
import de.arnav.studl.model.User;
import de.arnav.studl.model.UserRole;
import de.arnav.studl.repository.UserJpaRepository;
import de.arnav.studl.repository.UserRoleJpaRepository;
import de.arnav.studl.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserJpaRepository userJpaRepository;
    private final UserRoleJpaRepository userRoleJpaRepository;
    private final UserAdapter userAdapter;
    private final UserRoleAdapter userRoleAdapter;

    public UserRoleServiceImpl(UserJpaRepository userJpaRepository, UserRoleJpaRepository userRoleJpaRepository, UserAdapter userAdapter, UserRoleAdapter userRoleAdapter) {
        this.userJpaRepository = userJpaRepository;
        this.userRoleJpaRepository = userRoleJpaRepository;
        this.userAdapter = userAdapter;
        this.userRoleAdapter = userRoleAdapter;
    }

    @Override
    public Integer countUsersByRole(RoleType roleType) {
        return userRoleJpaRepository.countUsersBy(roleType);
    }

    @Override
    public Set<RoleType> findRolesByUserId(Long userId) {
        User user = userJpaRepository.findById(userId).orElseThrow();
        return user.getRoles();
    }

    @Override
    public RoleType findNextRole(RoleType roleType) {
        RoleType[] roleTypes = RoleType.values();
        if (roleType.ordinal()+1 >= roleTypes.length) {
            throw new IllegalStateException();
        }
        return roleTypes[roleType.ordinal()+1];
    }

    @Override
    public RoleType findPrevRole(RoleType roleType) {
        RoleType[] roleTypes = RoleType.values();
        if (roleType.ordinal()-1 < 0) {
            throw new IllegalStateException();
        }
        return roleTypes[roleType.ordinal()-1];
    }

    @Override
    public List<UserResponseDto> findUsersByRole(RoleType roleType) {
        List<User> users = userRoleJpaRepository.findUsersByRoleType();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for (User user : users) {
            userResponseDtos.add(userAdapter.toResponseDto(user));
        }
        return userResponseDtos;
    }

    @Override
    public Boolean hasRole(Long userId, RoleType roleType) {
        User user = userJpaRepository.findById(userId).orElseThrow();
        return user.getRoles().contains(roleType);
    }

    @Override
    public Boolean isRoleHigher(RoleType roleType1, RoleType roleType2) {
        return roleType1.ordinal() > roleType2.ordinal();
    }

    @Override
    public UserRoleResponseDto assignRole(Long userId, RoleType roleType) {
        UserRole userRole = userRoleJpaRepository.save(new UserRole(userId, roleType));
        return userRoleAdapter.toResponseDto(userRole);
    }

    @Override
    public Set<RoleType> getRolesByUserId(Long userId) {
        User user = userJpaRepository.findById(userId).orElseThrow();
        return user.getRoles();
    }

}
