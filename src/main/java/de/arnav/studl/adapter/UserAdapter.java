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
        dto.setRoles(user.getRoles());
        return dto;
    }

    @Override
    public User fromCreateDto(UserCreateDto createDto) {
        User user = new User();
        user.setName(createDto.getName());
        user.setEmail(createDto.getEmail());
        user.setHashedPassword(createDto.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

}
