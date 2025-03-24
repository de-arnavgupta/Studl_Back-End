package de.arnav.studl.facade.Implementation;

import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.organizationDto.OrganizationUpdateDto;
import de.arnav.studl.facade.OrganizationFacade;
import de.arnav.studl.service.OrganizationService;

import java.util.List;

public class OrganizationFacadeImpl implements OrganizationFacade {

    private final OrganizationService organizationService;

    public OrganizationFacadeImpl(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @Override
    public OrganizationResponseDto update(OrganizationUpdateDto organizationUpdateDto, Long organizationId) {
        return organizationService.updateOrganization(organizationUpdateDto, organizationId);
    }

    @Override
    public OrganizationResponseDto organizationRegister(OrganizationCreateDto organizationCreateDto) {
        return organizationService.createOrganization(organizationCreateDto);
    }

    @Override
    public void delete(Long organizationId) {
        organizationService.deleteOrganization(organizationId);
    }

    @Override
    public Integer countUsers(Long organizationId) {
        return organizationService.countUsersByOrganization(organizationId);
    }

    @Override
    public OrganizationResponseDto findById(Long organizationId) {
        return organizationService.findOrganizationById(organizationId);
    }

    @Override
    public List<OrganizationResponseDto> findAll() {
        return organizationService.findAllOrganizations();
    }

    @Override
    public List<OrganizationResponseDto> findByName(String name) {
        return organizationService.findOrganizationsByName(name);
    }
}
