package de.arnav.studl.adapter;

import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.organizationDto.OrganizationSummaryDto;
import de.arnav.studl.dto.organizationDto.OrganizationUpdateDto;
import de.arnav.studl.model.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationAdapter implements DtoAdapter<Organization, OrganizationResponseDto, OrganizationCreateDto, OrganizationUpdateDto, OrganizationSummaryDto> {

    @Override
    public Organization fromCreateDto(OrganizationCreateDto createDto) {
        Organization organization = new Organization();
        organization.setOrganizationName(createDto.getName());
        organization.setDomainName(createDto.getDomain());
        organization.setSubDomainNames(createDto.getCodomains());
        organization.setTld(createDto.getTopLevelDomains());
        return organization;
    }

    @Override
    public Organization fromUpdateDto(OrganizationUpdateDto updateDto, Organization organization) {
        if(updateDto.getName() != null) {
            organization.setOrganizationName(updateDto.getName());
        }
        if(updateDto.getDomain() != null) {
            organization.setDomainName(updateDto.getDomain());
        }
        if(updateDto.getCodomains() != null) {
            organization.setSubDomainNames(updateDto.getCodomains());
        }
        if(updateDto.getTopLevelDomains() != null) {
            organization.setTld(updateDto.getTopLevelDomains());
        }
        return organization;
    }

    @Override
    public OrganizationResponseDto toResponseDto(Organization organization) {
        OrganizationResponseDto dto = new OrganizationResponseDto();
        dto.setOrganizationId(organization.getOrganizationId());
        dto.setName(organization.getOrganizationName());
        dto.setDomain(organization.getDomainName());
        dto.setCodomains(organization.getSubDomainNames());
        dto.setTopLevelDomains(organization.getTld());
        return dto;
    }

    @Override
    public OrganizationSummaryDto toSummaryDto(Organization organization) {
        OrganizationSummaryDto dto = new OrganizationSummaryDto();
        dto.setName(organization.getOrganizationName());
        dto.setDomain(organization.getDomainName());
        dto.setTopLevelDomains(organization.getTld());
        return dto;
    }

}
