package de.arnav.studl.service;

import de.arnav.studl.model.Organization;

import java.util.List;

public interface OrganizationService {

    void createOrganization(Organization organization);
    void updateOrganization(Organization organization, Long organizationId);
    void deleteOrganization(Long organizationId);
    List<Organization> findAllOrganizations();
    Organization findOrganizationById(Long organizationId);
    Integer countUsersByOrganization(Long userId);

}
