package de.arnav.studl.service.implementation;

import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.organizationDto.OrganizationUpdateDto;
import de.arnav.studl.service.OrganizationService;

import java.util.List;

public class OrganizationServiceImpl implements OrganizationService {
    @Override
    public OrganizationResponseDto createOrganization(OrganizationCreateDto organizationCreateDto) {
        return null;
    }

    @Override
    public OrganizationResponseDto updateOrganization(OrganizationUpdateDto organizationUpdateDto, Long organizationId) {
        return null;
    }

    @Override
    public void deleteOrganization(Long organizationId) {

    }

    @Override
    public Integer countUsersByOrganization(Long userId) {
        return 0;
    }

    @Override
    public OrganizationResponseDto findOrganizationById(Long organizationId) {
        return null;
    }

    @Override
    public List<OrganizationResponseDto> findAllOrganizations() {
        return List.of();
    }

    @Override
    public List<OrganizationResponseDto> findOrganizationsByName(String name) {
        return List.of();
    }
}
