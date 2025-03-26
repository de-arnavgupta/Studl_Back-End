package de.arnav.studl.facade;

import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userDto.UserUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserFacade {

    void delete(String email);
    UserResponseDto update(UserUpdateDto userUpdateDto, Long userId);
    UserResponseDto removeAllRoles(Long userRoleId);
    UserResponseDto findById(Long userId);
    UserResponseDto findByEmail(String email);
    List<UserResponseDto> findByUsername(String username);
    List<UserResponseDto> findAll();
    OrganizationResponseDto findOrganizationByEmail(String email);

}
