package de.arnav.studl.service.template;

import de.arnav.studl.dto.userRole.UserRoleCreateDto;
import de.arnav.studl.dto.userRole.UserRoleResponseDto;

import java.util.List;

public interface UserRoleService {
    UserRoleResponseDto assignRole(UserRoleCreateDto dto);
    List<UserRoleResponseDto> getUserRolesByUser(Long userId);
    void removeRoleFromUser(Long userRoleId);
}
