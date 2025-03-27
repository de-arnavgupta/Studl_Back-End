package de.arnav.studl.service.implementation;

import de.arnav.studl.adapter.OrganizationAdapter;
import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.organizationDto.OrganizationUpdateDto;
import de.arnav.studl.exception.DuplicateOrganizationException;
import de.arnav.studl.exception.OrganizationNotFoundException;
import de.arnav.studl.model.Organization;
import de.arnav.studl.repository.OrganizationJpaRepository;
import de.arnav.studl.repository.UserJpaRepository;
import de.arnav.studl.service.OrganizationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationAdapter organizationAdapter;
    private final OrganizationJpaRepository organizationJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public OrganizationServiceImpl(OrganizationAdapter organizationAdapter, OrganizationJpaRepository organizationJpaRepository, UserJpaRepository userJpaRepository) {
        this.organizationAdapter = organizationAdapter;
        this.organizationJpaRepository = organizationJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Transactional
    @Override
    public OrganizationResponseDto createOrganization(OrganizationCreateDto organizationCreateDto) {
        if (organizationJpaRepository.existsByDomainName(organizationCreateDto.getDomain())) {
            throw new DuplicateOrganizationException("Organization with domain " + organizationCreateDto.getDomain() + " already exists. (createOrganization)");
        }
        Organization organization = organizationAdapter.fromCreateDto(organizationCreateDto);
        Organization savedOrganization = organizationJpaRepository.save(organization);
        return organizationAdapter.toResponseDto(savedOrganization);
    }

    @Transactional
    @Override
    public OrganizationResponseDto updateOrganization(OrganizationUpdateDto organizationUpdateDto, Long organizationId) {

        if (organizationId == null) {
            throw new IllegalArgumentException("Organization ID cannot be null. (updateOrganization)");
        }
        Organization organization = organizationJpaRepository.findById(organizationId)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization with ID " + organizationId + " not found. (updateOrganization)"));
        organization = organizationAdapter.fromUpdateDto(organizationUpdateDto, organization);
        Organization savedOrganization = organizationJpaRepository.save(organization);
        return organizationAdapter.toResponseDto(savedOrganization);
    }

    @Transactional
    @Override
    public void deleteOrganization(Long organizationId) {
        if (organizationId == null) {
            throw new IllegalArgumentException("Organization ID cannot be null. (deleteOrganization)");
        }
        Organization organization = organizationJpaRepository.findById(organizationId)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization with ID " + organizationId + " not found. (deleteOrganization)"));
        userJpaRepository.deleteAllByOrganization(organization);
        organizationJpaRepository.deleteById(organizationId);
    }

    @Override
    public Integer countUsersByOrganizationId(Long organizationId) {
        if (organizationId == null) {
            throw new IllegalArgumentException("Organization ID cannot be null. (countUsersByOrganizationId)");
        }
        if (!organizationJpaRepository.existsById(organizationId)) {
            throw new OrganizationNotFoundException("Organization with ID " + organizationId + " not found. (countUsersByOrganizationId)");
        }
        return organizationJpaRepository.countUsersByOrganizationId(organizationId);
    }

    @Override
    public OrganizationResponseDto findOrganizationById(Long organizationId) {
        if (organizationId == null) {
            throw new IllegalArgumentException("Organization ID cannot be null. (findOrganizationById)");
        }
        Organization organization = organizationJpaRepository.findById(organizationId)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization with ID " + organizationId + " not found. (countUsersByOrganizationId)"));
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
            throw new IllegalArgumentException("Organization name cannot be null. (findOrganizationsByName)");
        }
        List<Organization> organizations = organizationJpaRepository.findByOrganizationName(name);
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