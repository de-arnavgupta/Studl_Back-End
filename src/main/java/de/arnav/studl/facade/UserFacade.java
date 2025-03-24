package de.arnav.studl.facade;

import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.userDto.UserDeleteDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userDto.UserUpdateDto;
import de.arnav.studl.model.RoleType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserFacade {

    void delete(UserDeleteDto userDeleteDto);
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
