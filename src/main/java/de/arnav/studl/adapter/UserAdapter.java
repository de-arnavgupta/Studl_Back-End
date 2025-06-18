package de.arnav.studl.adapter;

import de.arnav.studl.dto.userDto.UserCreateDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userDto.UserSummaryDto;
import de.arnav.studl.dto.userDto.UserUpdateDto;
import de.arnav.studl.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter implements DtoAdapter<User, UserResponseDto, UserCreateDto, UserUpdateDto, UserSummaryDto> {

    private final OrganizationAdapter organizationAdapter;
    private final PasswordEncoder passwordEncoder;

    public UserAdapter(OrganizationAdapter organizationAdapter, PasswordEncoder passwordEncoder) {
        this.organizationAdapter = organizationAdapter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User fromCreateDto(UserCreateDto createDto) {
        User user = new User();
        user.setUserName(createDto.getName());
        user.setUserEmail(createDto.getEmail());
        user.setPassword(createDto.getPassword());
        return user;
    }

    @Override
    public User fromUpdateDto(UserUpdateDto userUpdateDto, User user) {
        if(userUpdateDto.getName() != null) {
            user.setUserName(userUpdateDto.getName());
        }
        if(userUpdateDto.getNewPassword() != null) {
            user.setPassword(passwordEncoder.encode(userUpdateDto.getNewPassword()));
        }
        return user;
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

}
