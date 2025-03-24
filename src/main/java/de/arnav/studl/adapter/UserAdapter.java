package de.arnav.studl.adapter;

import de.arnav.studl.dto.userDto.UserCreateDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter {
    public UserResponseDto toResponseDto(User user) {
        return null;
    }
    public User fromCreateDto(UserCreateDto user) {
        return null;
    }
}
