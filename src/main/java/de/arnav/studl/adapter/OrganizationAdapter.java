package de.arnav.studl.adapter;

import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.organizationDto.OrganizationUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class OrganizationAdapter implements DtoAdapter<Organization, OrganizationResponseDto, OrganizationCreateDto, OrganizationUpdateDto> {

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

}
