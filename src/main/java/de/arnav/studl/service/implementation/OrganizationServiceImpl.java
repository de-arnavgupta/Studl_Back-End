package de.arnav.studl.service.implementation;

import de.arnav.studl.adapter.OrganizationAdapter;
import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.organizationDto.OrganizationUpdateDto;
import de.arnav.studl.exception.DuplicateOrganizationException;
import de.arnav.studl.exception.OrganizationNotFoundException;
import de.arnav.studl.model.Organization;
import de.arnav.studl.repository.OrganizationJpaRepository;
import de.arnav.studl.service.OrganizationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationAdapter organizationAdapter;
    private final OrganizationJpaRepository organizationJpaRepository;

    public OrganizationServiceImpl(OrganizationAdapter organizationAdapter, OrganizationJpaRepository organizationJpaRepository) {
        this.organizationAdapter = organizationAdapter;
        this.organizationJpaRepository = organizationJpaRepository;
    }

    @Transactional
    @Override
    public OrganizationResponseDto createOrganization(OrganizationCreateDto organizationCreateDto) {
        if (organizationJpaRepository.existsByDomain(organizationCreateDto.getDomain())) {
            throw new DuplicateOrganizationException();
        }
        Organization organization = organizationAdapter.fromCreateDto();
        Organization savedOrganization = organizationJpaRepository.save(organization);
        return organizationAdapter.toResponseDto(savedOrganization);
    }

    @Transactional
    @Override
    public OrganizationResponseDto updateOrganization(OrganizationUpdateDto organizationUpdateDto, Long organizationId) {

        if (organizationId == null) {
            throw new IllegalArgumentException();
        }
        Organization organization = organizationJpaRepository.findById(organizationId)
                .orElseThrow(() -> new OrganizationNotFoundException());

        if(organizationUpdateDto.getName() != null) {
            organization.setOrganizationName(organizationUpdateDto.getName());
        }
        if(organizationUpdateDto.getDomain() != null) {
            organization.setDomainName(organizationUpdateDto.getDomain());
        }
        if(organizationUpdateDto.getCodomains() != null) {
            organization.setSubDomainNames(organizationUpdateDto.getCodomains());
        }
        if(organizationUpdateDto.getTopLevelDomains() != null) {
            organization.setTld(organizationUpdateDto.getTopLevelDomains());
        }
        Organization savedOrganization = organizationJpaRepository.save(organization);
        return organizationAdapter.toResponseDto(savedOrganization);
    }

    @Transactional
    @Override
    public void deleteOrganization(Long organizationId) {
        if (organizationId == null) {
            throw new IllegalArgumentException();
        }
        if (!organizationJpaRepository.existsById(organizationId)) {
            throw new OrganizationNotFoundException();
        }
        organizationJpaRepository.deleteById(organizationId);
    }

    @Override
    public Integer countUsersByOrganization(Long organizationId) {
        if (organizationId == null) {
            throw new IllegalArgumentException();
        }
        if (!organizationJpaRepository.existsById(organizationId)) {
            throw new OrganizationNotFoundException();
        }
        return organizationJpaRepository.countUsersByOrganization(organizationId);
    }

    @Override
    public OrganizationResponseDto findOrganizationById(Long organizationId) {
        if (organizationId == null) {
            throw new IllegalArgumentException();
        }
        Organization organization = organizationJpaRepository.findById(organizationId)
                .orElseThrow(() -> new OrganizationNotFoundException());
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
        if (name == null) {
            throw new IllegalArgumentException();
        }
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