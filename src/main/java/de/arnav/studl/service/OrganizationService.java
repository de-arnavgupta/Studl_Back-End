package de.arnav.studl.service;

import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.organizationDto.OrganizationUpdateDto;

import java.util.List;

public interface OrganizationService {

    OrganizationResponseDto createOrganization(OrganizationCreateDto organizationCreateDto);
    OrganizationResponseDto updateOrganization(OrganizationUpdateDto organizationUpdateDto, Long organizationId, Boolean isPut);
    void deleteOrganization(Long organizationId);
    Integer countUsersByOrganizationId(Long organizationId);
    OrganizationResponseDto findOrganizationById(Long organizationId);
    List<OrganizationResponseDto> findAllOrganizations();
    List<OrganizationResponseDto> findOrganizationsByName(String name);

}