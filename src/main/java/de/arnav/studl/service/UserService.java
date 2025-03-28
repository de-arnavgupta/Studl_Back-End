package de.arnav.studl.service;

import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.userDto.UserCreateDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userDto.UserUpdateDto;

import java.util.List;

public interface UserService {

    void deleteUser(String email);
    UserResponseDto createUser(UserCreateDto userCreateDto);
    UserResponseDto updateUser(UserUpdateDto userUpdateDto, Long userId, Boolean IsPut);
    UserResponseDto removeAllRolesFromUser(Long userId);
    UserResponseDto findUserById(Long userId);
    UserResponseDto findUserByEmail(String email);
    List<UserResponseDto> findUsersByUsername(String username);
    List<UserResponseDto> findAllUsers();
    OrganizationResponseDto findOrganizationByUserEmail(String email);

}
