package de.arnav.studl.facade;

import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.organizationDto.OrganizationUpdateDto;
import de.arnav.studl.model.Organization;
import de.arnav.studl.model.RoleType;
import de.arnav.studl.model.User;

import java.util.List;

public interface CrudFacade {

    void createUser(User user);
    void updateUser(User user, Long userId);
    void deleteUser(Long userId);
    User findUserById(Long userId);
    User findUserByEmail(String email);
    List<User> findUsersByUsername(String username);
    List<User> findUsersByRole(RoleType roleType);
    List<User> findAllUsers();
    RoleType findRoleByUserId(Long userId);
    RoleType findRoleByUsername(String username);
    Boolean hasRole(Long userId, RoleType roleType);
    OrganizationResponseDto createOrganization(OrganizationCreateDto organizationCreateDto);
    OrganizationResponseDto updateOrganization(OrganizationUpdateDto organizationUpdateDto, Long organizationId);
    void deleteOrganization(Long organizationId);
    OrganizationResponseDto findOrganizationById(Long organizationId);
    List<OrganizationResponseDto> findAllOrganizations();
    List<OrganizationResponseDto> findOrganizationsByName(String name);

}