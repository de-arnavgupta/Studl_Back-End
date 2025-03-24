package de.arnav.studl.facade;

import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.organizationDto.OrganizationUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrganizationFacade {
    OrganizationResponseDto organizationRegister (OrganizationCreateDto organizationCreateDto);
    OrganizationResponseDto update(OrganizationUpdateDto organizationUpdateDto, Long organizationId);
    void delete(Long organizationId);
    Integer countUsers(Long organizationId);
    OrganizationResponseDto findById(Long organizationId);
    List<OrganizationResponseDto> findAll();
    List<OrganizationResponseDto> findByName(String name);
}
