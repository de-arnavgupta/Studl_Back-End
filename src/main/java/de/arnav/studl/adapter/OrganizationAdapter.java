package de.arnav.studl.adapter;

import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.organizationDto.OrganizationUpdateDto;
import de.arnav.studl.model.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationAdapter <Organization, OrganizationResponseDto, OrganizationCreateDto, OrganizationUpdateDto> {

    @Override
    public OrganizationResponseDto toResponseDto(Organization organization) {
        OrganizationResponseDto dto = new OrganizationResponseDto();
        dto.setOrganizationId(organization.getOrganizationId());
        dto.setName(organization.getName());
        dto.setDomain(organization.getDomain());
        dto.setCodomains(organization.getCodomains());
        dto.setTopLevelDomains(organization.getTopLevelDomains());
        return dto;
    }

    @Override
    public Organization fromCreateDto(OrganizationCreateDto createDto) {
        Organization organization = new Organization();
        organization.setName(createDto.getName());
        organization.setDomain(createDto.getDomain());
        organization.setCodomains(createDto.getCodomains());
        organization.setTopLevelDomains(createDto.getTopLevelDomains());
        return organization;
    }

    @Override
    public Organization updateEntityFromUpdateDto(OrganizationUpdateDto updateDto, Organization organization) {
        if(updateDto.getName() != null) {
            organization.setName(updateDto.getName());
        }
        if(updateDto.getDomain() != null) {
            organization.setDomain(updateDto.getDomain());
        }
        if(updateDto.getCodomains() != null) {
            organization.setCodomains(updateDto.getCodomains());
        }
        if(updateDto.getTopLevelDomains() != null) {
            organization.setTopLevelDomains(updateDto.getTopLevelDomains());
        }
        return organization;
    }
}
