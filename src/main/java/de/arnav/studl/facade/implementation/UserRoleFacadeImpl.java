package de.arnav.studl.facade.implementation;

import de.arnav.studl.dto.userRole.UserRoleCreateDto;
import de.arnav.studl.dto.userRole.UserRoleResponseDto;
import de.arnav.studl.facade.template.UserRoleFacade;
import de.arnav.studl.service.template.UserRoleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Transactional
public class UserRoleFacadeImpl implements UserRoleFacade {

    private final UserRoleService userRoleService;

    public UserRoleFacadeImpl(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Override
    public UserRoleResponseDto assignRole(UserRoleCreateDto dto) {
        return userRoleService.assignRole(dto);
    }

    @Override
    public List<UserRoleResponseDto> getUserRolesByUser(Long userId) {
        return userRoleService.getUserRolesByUser(userId);
    }

    @Override
    public void removeRoleFromUser(Long userRoleId) {
        userRoleService.removeRoleFromUser(userRoleId);
    }
}