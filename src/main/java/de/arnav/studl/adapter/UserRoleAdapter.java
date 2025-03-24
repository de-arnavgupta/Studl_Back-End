package de.arnav.studl.adapter;

import de.arnav.studl.dto.userRoleDto.UserRoleCreateDto;
import de.arnav.studl.dto.userRoleDto.UserRoleResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserRoleAdapter implements DtoAdapter<UserRole, UserRoleResponseDto, UserRoleCreateDto, Void> {

    @Override
    public UserRoleResponseDto toResponseDto(UserRole userRole) {
        UserRoleResponseDto dto = new UserRoleResponseDto();
        dto.setUserRoleId(userRole.getId());
        dto.setRoleType(userRole.getRole().name());
        if(userRole.getUser() != null) {
            dto.setUserId(userRole.getUser().getUserId());
        }
        return dto;
    }

    @Override
    public UserRole fromCreateDto(UserRoleCreateDto createDto) {
        UserRole userRole = new UserRole();
        userRole.setRoleType(createDto.getRoleType());
        return userRole;
    }

    @Override
    public UserRole updateEntityFromUpdateDto(Void updateDto, UserRole entity) {
        throw new UnsupportedOperationException("Update not supported for UserRole.");
    }
}
