package de.arnav.studl.service;

import de.arnav.studl.model.Organization;

import java.util.List;

public interface OrganizationService {

    void createOrganization(Organization organization);
    void updateOrganization(Organization organization, Long organizationId);
    void deleteOrganization(Long organizationId);
    Integer countUsersByOrganization(Long userId);
    Organization findOrganizationById(Long organizationId);
    List<Organization> findAllOrganizations();
    List<Organization> findOrganizationsByName(String name);

}