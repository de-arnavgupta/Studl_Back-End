package de.arnav.studl.service.template;


import de.arnav.studl.dto.user.UserCreateDto;
import de.arnav.studl.dto.user.UserResponseDto;
import de.arnav.studl.dto.user.UserUpdateDto;
import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserCreateDto dto);
    UserResponseDto getUserById(Long id);
    UserResponseDto getUserByEmail(String email);
    List<UserResponseDto> getAllUsers();
    UserResponseDto updateUser(Long id, UserUpdateDto dto);
    void deleteUser(Long id);
    List<UserResponseDto> getUsersByOrganization(Long organizationId);
    List<UserResponseDto> findUsersByName(String keyword);
}
