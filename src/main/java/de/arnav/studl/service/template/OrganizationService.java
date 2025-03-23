package de.arnav.studl.service.template;

import de.arnav.studl.dto.organization.OrganizationCreateDto;
import de.arnav.studl.dto.organization.OrganizationResponseDto;
import de.arnav.studl.dto.organization.OrganizationUpdateDto;
import java.util.List;

public interface OrganizationService {
    OrganizationResponseDto createOrganization(OrganizationCreateDto dto);
    OrganizationResponseDto getOrganizationById(Long id);
    OrganizationResponseDto updateOrganization(Long id, OrganizationUpdateDto dto);
    void deleteOrganization(Long id);
    List<OrganizationResponseDto> getAllOrganizations();
    List<OrganizationResponseDto> searchOrganizationsByName(String keyword);
    OrganizationResponseDto getOrganizationByDomain(String domain);
}
