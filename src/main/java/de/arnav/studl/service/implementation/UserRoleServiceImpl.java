package de.arnav.studl.service.implementation;

import de.arnav.studl.adapter.userRole.UserRoleAdapter;
import de.arnav.studl.dto.userRole.UserRoleCreateDto;
import de.arnav.studl.dto.userRole.UserRoleResponseDto;
import de.arnav.studl.exception.ResourceNotFoundException;
import de.arnav.studl.model.User;
import de.arnav.studl.model.UserRole;
import de.arnav.studl.repository.UserRepository;
import de.arnav.studl.repository.UserRoleRepository;
import de.arnav.studl.service.template.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final UserRoleAdapter userRoleAdapter;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository,
                               UserRepository userRepository,
                               UserRoleAdapter userRoleAdapter) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.userRoleAdapter = userRoleAdapter;
    }

    @Override
    public UserRoleResponseDto assignRole(UserRoleCreateDto dto) {
        UserRole userRole = userRoleAdapter.fromCreateDto(dto);
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getUserId()));
        userRole.setUser(user);
        UserRole savedUserRole = userRoleRepository.save(userRole);
        return userRoleAdapter.toResponseDto(savedUserRole);
    }

    @Override
    public List<UserRoleResponseDto> getUserRolesByUser(Long userId) {
        return userRoleRepository.findAllByUser_UserId(userId).stream()
                .map(userRoleAdapter::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public void removeRoleFromUser(Long userRoleId) {
        if (!userRoleRepository.existsById(userRoleId)) {
            throw new ResourceNotFoundException("User role not found with id " + userRoleId);
        }
        userRoleRepository.deleteById(userRoleId);
    }

}