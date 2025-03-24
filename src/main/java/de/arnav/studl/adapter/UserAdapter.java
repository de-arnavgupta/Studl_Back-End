package de.arnav.studl.adapter;

import de.arnav.studl.dto.userDto.UserCreateDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userDto.UserUpdateDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class UserAdapter implements DtoAdapter<User, UserResponseDto, UserCreateDto, UserUpdateDto> {

    @Override
    public UserResponseDto toResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());

        if (user.getRoles() != null) {
            dto.setRoles(user.getRoles().stream()
                    .map(userRole -> userRole.getRole().name())
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    @Override
    public User fromCreateDto(UserCreateDto createDto) {
        User user = new User();
        user.setName(createDto.getName());
        user.setEmail(createDto.getEmail());
        user.setHashedPassword(hashPassword(createDto.getPassword()));
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
