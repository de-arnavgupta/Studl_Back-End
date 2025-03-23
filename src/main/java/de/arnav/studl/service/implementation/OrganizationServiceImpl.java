package de.arnav.studl.service.implementation;

import de.arnav.studl.model.Organization;
import de.arnav.studl.service.OrganizationService;

import java.util.List;

public class OrganizationServiceImpl implements OrganizationService {

    @Override
    public void createOrganization(Organization organization) {

    }

    @Override
    public void updateOrganization(Organization organization, Long organizationId) {

    }

    @Override
    public void deleteOrganization(Long organizationId) {

    }

    @Override
    public List<Organization> findAllOrganizations() {
        return List.of();
    }

    @Override
    public Organization findOrganizationById(Long organizationId) {
        return null;
    }

    @Override
    public Integer countUsersByOrganization(Long userId) {
        return 0;
    }

}
