package de.arnav.studl.facade;

import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.organizationDto.OrganizationUpdateDto;
import de.arnav.studl.model.RoleType;
import de.arnav.studl.model.User;

import java.util.HashSet;
import java.util.List;

public interface CrudFacade {

    void updateUser(User user, Long userId);
    void deleteUser(Long userId);
    User findUserById(Long userId);
    User findUserByEmail(String email);
    List<User> findUsersByUsername(String username);
    List<User> findUsersByRole(RoleType roleType);
    List<User> findAllUsers();
    HashSet<RoleType> findRolesByUserId(Long userId);
    HashSet<RoleType> findRolesByUsername(String username);
    Boolean hasRole(Long userId, RoleType roleType);
    OrganizationResponseDto registerOrganization(OrganizationCreateDto organizationCreateDto);
    OrganizationResponseDto updateOrganization(OrganizationUpdateDto organizationUpdateDto, Long organizationId);
    void deleteOrganization(Long organizationId);
    OrganizationResponseDto findOrganizationById(Long organizationId);
    List<OrganizationResponseDto> findAllOrganizations();
    List<OrganizationResponseDto> findOrganizationsByName(String name);

}