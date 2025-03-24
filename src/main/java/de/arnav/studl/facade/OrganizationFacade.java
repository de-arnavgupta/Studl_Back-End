package de.arnav.studl.facade;

import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.organizationDto.OrganizationUpdateDto;

import java.util.List;

public interface OrganizationFacade {
    OrganizationResponseDto update(OrganizationUpdateDto organizationUpdateDto, Long organizationId);
    void delete(Long organizationId);
    Integer countUsers(Long organizationId);
    OrganizationResponseDto findById(Long organizationId);
    List<OrganizationResponseDto> findAll();
    List<OrganizationResponseDto> findByName(String name);
}
