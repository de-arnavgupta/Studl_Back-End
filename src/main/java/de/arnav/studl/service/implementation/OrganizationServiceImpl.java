package de.arnav.studl.service.implementation;

import de.arnav.studl.adapter.organization.OrganizationAdapter;
import de.arnav.studl.dto.organization.OrganizationCreateDto;
import de.arnav.studl.dto.organization.OrganizationResponseDto;
import de.arnav.studl.dto.organization.OrganizationUpdateDto;
import de.arnav.studl.exception.ResourceNotFoundException;
import de.arnav.studl.model.Organization;
import de.arnav.studl.repository.OrganizationRepository;
import de.arnav.studl.service.template.OrganizationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationAdapter organizationAdapter;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository,
                                   OrganizationAdapter organizationAdapter) {
        this.organizationRepository = organizationRepository;
        this.organizationAdapter = organizationAdapter;
    }

    @Override
    public OrganizationResponseDto createOrganization(OrganizationCreateDto dto) {
        Organization organization = organizationAdapter.fromCreateDto(dto);
        Organization savedOrg = organizationRepository.save(organization);
        return organizationAdapter.toResponseDto(savedOrg);
    }

    @Override
    public OrganizationResponseDto getOrganizationById(Long id) {
        Organization org = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id " + id));
        return organizationAdapter.toResponseDto(org);
    }

    @Override
    public OrganizationResponseDto updateOrganization(Long id, OrganizationUpdateDto dto) {
        Organization org = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id " + id));
        org = organizationAdapter.updateEntityFromUpdateDto(dto, org);
        Organization updatedOrg = organizationRepository.save(org);
        return organizationAdapter.toResponseDto(updatedOrg);
    }

    @Override
    public void deleteOrganization(Long id) {
        if(!organizationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Organization not found with id " + id);
        }
        organizationRepository.deleteById(id);
    }

    @Override
    public List<OrganizationResponseDto> getAllOrganizations() {
        return organizationRepository.findAll().stream()
                .map(organizationAdapter::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrganizationResponseDto> searchOrganizationsByName(String keyword) {
        return organizationRepository.findByNameContaining(keyword).stream()
                .map(organizationAdapter::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationResponseDto getOrganizationByDomain(String domain) {
        Organization org = organizationRepository.findByDomain(domain)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
        return organizationAdapter.toResponseDto(org);
    }
}
