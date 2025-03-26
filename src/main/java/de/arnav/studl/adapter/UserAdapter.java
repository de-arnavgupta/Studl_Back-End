package de.arnav.studl.adapter;

import de.arnav.studl.dto.userDto.UserCreateDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userDto.UserSummaryDto;
import de.arnav.studl.dto.userDto.UserUpdateDto;
import de.arnav.studl.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter implements DtoAdapter<User, UserResponseDto, UserCreateDto, UserSummaryDto> {

    private final OrganizationAdapter organizationAdapter;

    public UserAdapter(OrganizationAdapter organizationAdapter) {
        this.organizationAdapter = organizationAdapter;
    }

    @Override
    public UserResponseDto toResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto(organizationAdapter);
        dto.setUserId(user.getUserId());
        dto.setName(user.getUserName());
        dto.setEmail(user.getUserEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setRoles(user.getRoleType());
        dto.setOrganizationId(user.getOrganization());
        return dto;
    }

    @Override
    public UserSummaryDto toSummaryDto(User user) {
        UserSummaryDto dto = new UserSummaryDto(organizationAdapter);
        dto.setName(user.getUserName());
        dto.setEmail(user.getUserEmail());
        dto.setRoles(user.getRoleType());
        return dto;
    }

    @Override
    public User fromCreateDto(UserCreateDto createDto) {
        User user = new User();
        user.setUserName(createDto.getName());
        user.setUserEmail(createDto.getEmail());
        user.setPassword(createDto.getPassword());
        return user;
    }

}
