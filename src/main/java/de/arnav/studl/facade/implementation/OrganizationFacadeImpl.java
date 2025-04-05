package de.arnav.studl.facade.implementation;

import de.arnav.studl.dto.organization.OrganizationCreateDto;
import de.arnav.studl.dto.organization.OrganizationResponseDto;
import de.arnav.studl.dto.organization.OrganizationUpdateDto;
import de.arnav.studl.facade.template.OrganizationFacade;
import de.arnav.studl.service.template.OrganizationService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@Transactional
public class OrganizationFacadeImpl implements OrganizationFacade {

    private final OrganizationService organizationService;

    public OrganizationFacadeImpl(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @Override
    public OrganizationResponseDto createOrganization(OrganizationCreateDto dto) {
        return organizationService.createOrganization(dto);
    }

    @Override
    public OrganizationResponseDto getOrganizationById(Long id) {
        return organizationService.getOrganizationById(id);
    }

    @Override
    public OrganizationResponseDto updateOrganization(Long id, OrganizationUpdateDto dto) {
        return organizationService.updateOrganization(id, dto);
    }

    @Override
    public void deleteOrganization(Long id) {
        organizationService.deleteOrganization(id);
    }

    @Override
    public List<OrganizationResponseDto> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    @Override
    public List<OrganizationResponseDto> searchOrganizationsByName(String keyword) {
        return organizationService.searchOrganizationsByName(keyword);
    }

    @Override
    public OrganizationResponseDto getOrganizationByDomain(String domain) {
        return organizationService.getOrganizationByDomain(domain);
    }
}
