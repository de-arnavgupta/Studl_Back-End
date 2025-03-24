package de.arnav.studl.service.implementation;

import de.arnav.studl.adapter.user.UserAdapter;
import de.arnav.studl.dto.user.UserCreateDto;
import de.arnav.studl.dto.user.UserResponseDto;
import de.arnav.studl.dto.user.UserUpdateDto;
import de.arnav.studl.exception.customExceptions.ResourceNotFoundException;
import de.arnav.studl.model.Organization;
import de.arnav.studl.model.User;
import de.arnav.studl.model.UserRole;
import de.arnav.studl.model.enums.Role;
import de.arnav.studl.repository.OrganizationRepository;
import de.arnav.studl.repository.UserRepository;
import de.arnav.studl.service.template.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final UserAdapter userAdapter;

    public UserServiceImpl(UserRepository userRepository,
                           OrganizationRepository organizationRepository,
                           UserAdapter userAdapter) {
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
        this.userAdapter = userAdapter;
    }

    @Override
    public UserResponseDto createUser(UserCreateDto dto) {
        User user = userAdapter.fromCreateDto(dto);

         Organization org = organizationRepository.findById(dto.getOrganizationId())
             .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id " + dto.getOrganizationId()));
         user.setOrganization(org);

        if(dto.getRoles() != null && !dto.getRoles().isEmpty()) {
            List<UserRole> roles = dto.getRoles().stream().map(roleStr -> {
                UserRole userRole = new UserRole();
                userRole.setRole(Role.valueOf(roleStr.toUpperCase()));
                userRole.setUser(user);
                return userRole;
            }).collect(Collectors.toList());
            user.setUserRoles(roles);
        }

        User savedUser = userRepository.save(user);
        return userAdapter.toResponseDto(savedUser);
    }


    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return userAdapter.toResponseDto(user);
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email " + email));
        return userAdapter.toResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userAdapter::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto updateUser(Long id, UserUpdateDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        user = userAdapter.updateEntityFromUpdateDto(dto, user);
        if (dto.getOrganizationId() != null) {
            Organization organization = organizationRepository.findById(dto.getOrganizationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id " + dto.getOrganizationId()));
            user.setOrganization(organization);
        }
        User updatedUser = userRepository.save(user);
        return userAdapter.toResponseDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDto> getUsersByOrganization(Long organizationId) {
        return userRepository.findAllByOrganization_OrganizationId(organizationId)
                .stream()
                .map(userAdapter::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDto> findUsersByName(String keyword) {
        return userRepository.findByNameContaining(keyword)
                .stream()
                .map(userAdapter::toResponseDto)
                .collect(Collectors.toList());
    }
}
