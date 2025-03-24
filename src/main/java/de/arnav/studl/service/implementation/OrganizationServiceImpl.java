package de.arnav.studl.service.implementation;

import de.arnav.studl.adapter.OrganizationAdapter;
import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.organizationDto.OrganizationUpdateDto;
import de.arnav.studl.model.Organization;
import de.arnav.studl.repository.OrganizationJpaRepository;
import de.arnav.studl.service.OrganizationService;

import java.util.ArrayList;
import java.util.List;

public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationAdapter organizationAdapter;
    private final OrganizationJpaRepository organizationJpaRepository;

    public OrganizationServiceImpl(OrganizationAdapter organizationAdapter, OrganizationJpaRepository organizationJpaRepository) {
        this.organizationAdapter = organizationAdapter;
        this.organizationJpaRepository = organizationJpaRepository;
    }

    @Override
    public OrganizationResponseDto createOrganization(OrganizationCreateDto organizationCreateDto) {
        Organization organization = organizationAdapter.fromCreateDto();
        Organization savedOrganization = organizationJpaRepository.save(organization);
        return organizationAdapter.toResponseDto(savedOrganization);
    }

    @Override
    public OrganizationResponseDto updateOrganization(OrganizationUpdateDto organizationUpdateDto, Long organizationId) {
        Organization organization = organizationJpaRepository.findById(organizationId).orElseThrow();
        if(organizationUpdateDto.getName() != null) {
            organization.setName(organizationUpdateDto.getName());
        }
        if(organizationUpdateDto.getDescription() != null) {
            organization.setDescription(organizationUpdateDto.getDescription());
        }
        if(organizationUpdateDto.getDomain() != null) {
            organization.setDomain(organizationUpdateDto.getDomain());
        }
        if(organizationUpdateDto.getSubDomains() != null) {
            organization.setSubDomains(organizationUpdateDto.getSubDomains());
        }
        if(organizationUpdateDto.getTopLevelDomains() != null) {
            organization.setTopLevelDomains(organizationUpdateDto.getTopLevelDomains());
        }
        Organization savedOrganization = organizationJpaRepository.save(organization);
        return organizationAdapter.toResponseDto(savedOrganization);
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        organizationJpaRepository.deleteById(organizationId);
    }

    @Override
    public OrganizationResponseDto getOrganization(Long organizationId) {
        Organization organization = organizationJpaRepository.findById(organizationId).orElseThrow();
        return organizationAdapter.toResponseDto(organization);
    }

    @Override
    public Integer countUsersByOrganization(Long organizationId) {
        return organizationJpaRepository.countUsersByOrganization(organizationId);
    }

    @Override
    public OrganizationResponseDto findOrganizationById(Long organizationId) {
        Organization organization = organizationJpaRepository.findById(organizationId).orElseThrow();
        return organizationAdapter.toResponseDto(organization);
    }

    @Override
    public List<OrganizationResponseDto> findAllOrganizations() {
        List<Organization> organizations = organizationJpaRepository.findAll();
        List<OrganizationResponseDto> organizationResponseDtos = new ArrayList<>();
        for(Organization organization : organizations) {
            organizationResponseDtos.add(organizationAdapter.toResponseDto(organization));
        }
        return organizationResponseDtos;
    }

    @Override
    public List<OrganizationResponseDto> findOrganizationsByName(String name) {
        List<Organization> organizations = organizationJpaRepository.findByName(name);
        List<OrganizationResponseDto> organizationResponseDtos = new ArrayList<>();
        for(Organization organization : organizations) {
            organizationResponseDtos.add(organizationAdapter.toResponseDto(organization));
        }
        return organizationResponseDtos;
    }
}

/*
* Questions to ask yourself next time b4 jumping to code (thought process):
* What are my DTOs and how will they flow between the layers?
* What do my adapters do?
* Which facade will use which methods.
* */