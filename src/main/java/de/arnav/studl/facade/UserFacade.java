package de.arnav.studl.facade;

import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userDto.UserUpdateDto;

import java.util.List;

public interface UserFacade {

    UserResponseDto update(UserUpdateDto userUpdateDto, Long userId);
    UserResponseDto removeRole(Long userRoleId, RoleType roleType);
    UserResponseDto removeAllRoles(Long userRoleId);
    UserResponseDto findById(Long userId);
    UserResponseDto findByEmail(String email);
    List<UserResponseDto> findByUsername(String username);
    List<UserResponseDto> findAll();
    OrganizationResponseDto findOrganizationById(Long userId);
    Boolean hasRole(Long userId, RoleType roleType);

}
