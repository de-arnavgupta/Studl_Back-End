package de.arnav.studl.facade.template;

import de.arnav.studl.dto.userRole.UserRoleResponseDto;
import de.arnav.studl.dto.userRole.UserRoleCreateDto;
import java.util.List;

public interface UserRoleFacade {
    UserRoleResponseDto assignRole(UserRoleCreateDto dto);
    List<UserRoleResponseDto> getUserRolesByUser(Long userId);
    void removeRoleFromUser(Long userRoleId);
}
