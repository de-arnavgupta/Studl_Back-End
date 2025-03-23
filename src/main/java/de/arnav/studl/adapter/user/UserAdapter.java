package de.arnav.studl.adapter.user;

import de.arnav.studl.adapter.DtoAdapter;
import de.arnav.studl.dto.user.UserCreateDto;
import de.arnav.studl.dto.user.UserOrganizationSummaryDto;
import de.arnav.studl.dto.user.UserResponseDto;
import de.arnav.studl.dto.user.UserUpdateDto;
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
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());

        if (user.getOrganization() != null) {
            UserOrganizationSummaryDto orgSummary = new UserOrganizationSummaryDto();
            orgSummary.setOrganizationId(user.getOrganization().getOrganizationId());
            orgSummary.setName(user.getOrganization().getName());
            dto.setOrganization(orgSummary);
        }

        if (user.getUserRoles() != null) {
            dto.setRoles(user.getUserRoles().stream()
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
        if (updateDto.getPassword() != null && !updateDto.getPassword().isEmpty()) {
            user.setHashedPassword(hashPassword(updateDto.getPassword()));
        }
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    private String hashPassword(String rawPassword) {
        return rawPassword;
    }
}
