package de.arnav.studl.facade.implementation;

import de.arnav.studl.dto.user.UserCreateDto;
import de.arnav.studl.dto.user.UserResponseDto;
import de.arnav.studl.dto.user.UserUpdateDto;
import de.arnav.studl.facade.template.UserFacade;
import de.arnav.studl.service.template.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@Transactional
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserResponseDto createUser(UserCreateDto dto) {
        return userService.createUser(dto);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return userService.getUserById(id);
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public UserResponseDto updateUser(Long id, UserUpdateDto dto) {
        return userService.updateUser(id, dto);
    }

    @Override
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    @Override
    public List<UserResponseDto> getUsersByOrganization(Long organizationId) {
        return userService.getUsersByOrganization(organizationId);
    }

    @Override
    public List<UserResponseDto> findUsersByName(String keyword) {
        return userService.findUsersByName(keyword);
    }
}
