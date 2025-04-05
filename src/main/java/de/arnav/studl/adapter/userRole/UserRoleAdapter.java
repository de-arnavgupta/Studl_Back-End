package de.arnav.studl.adapter.userRole;

import de.arnav.studl.adapter.DtoAdapter;
import de.arnav.studl.dto.userRole.UserRoleCreateDto;
import de.arnav.studl.dto.userRole.UserRoleResponseDto;
import de.arnav.studl.model.UserRole;
import de.arnav.studl.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserRoleAdapter implements DtoAdapter<UserRole, UserRoleResponseDto, UserRoleCreateDto, Void> {

    @Override
    public UserRoleResponseDto toResponseDto(UserRole userRole) {
        UserRoleResponseDto dto = new UserRoleResponseDto();
        dto.setId(userRole.getId());
        dto.setRole(userRole.getRole().name());
        if(userRole.getUser() != null) {
            dto.setUserId(userRole.getUser().getUserId());
        }
        return dto;
    }

    @Override
    public UserRole fromCreateDto(UserRoleCreateDto createDto) {
        UserRole userRole = new UserRole();
        userRole.setRole(Role.valueOf(createDto.getRole().toUpperCase()));
        return userRole;
    }

    @Override
    public UserRole updateEntityFromUpdateDto(Void updateDto, UserRole entity) {
        throw new UnsupportedOperationException("Update not supported for UserRole.");
    }
}
