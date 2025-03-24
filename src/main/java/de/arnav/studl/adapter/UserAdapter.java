package de.arnav.studl.adapter;

import de.arnav.studl.dto.userDto.UserCreateDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userDto.UserUpdateDto;
import de.arnav.studl.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class UserAdapter implements DtoAdapter<User, UserResponseDto, UserCreateDto, UserUpdateDto> {

    @Override
    public UserResponseDto toResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setUserId(user.getUserId());
        dto.setName(user.getUserName());
        dto.setEmail(user.getUserEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setRoles(user.getRoleType());
        return dto;
    }

    @Override
    public User fromCreateDto(UserCreateDto createDto) {
        User user = new User();
        user.setUserName(createDto.getName());
        user.setUserEmail(createDto.getEmail());
        user.set(hashPassword(createDto.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    @Override
    public User updateEntityFromUpdateDto(UserUpdateDto updateDto, User user) {
        if (updateDto.getName() != null) {
            user.setName(updateDto.getName());
        }
        if (updateDto.getEmail() != null) {
            user.setEmail(updateDto.getEmail());
        }
        if (updateDto.getNewPassword() != null && !updateDto.getNewPassword().isEmpty()) {
            user.setPassword(hashPassword(updateDto.getNewPassword()));
        }
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    private String hashPassword(String rawPassword) {
        return rawPassword;
    }
}
