package de.arnav.studl.controller;

import de.arnav.studl.dto.organization.OrganizationCreateDto;
import de.arnav.studl.dto.organization.OrganizationResponseDto;
import de.arnav.studl.dto.organization.OrganizationUpdateDto;
import de.arnav.studl.facade.template.OrganizationFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    private final OrganizationFacade organizationFacade;

    public OrganizationController(OrganizationFacade organizationFacade) {
        this.organizationFacade = organizationFacade;
    }

    @PostMapping
    public ResponseEntity<OrganizationResponseDto> createOrganization(@RequestBody OrganizationCreateDto dto) {
        OrganizationResponseDto response = organizationFacade.createOrganization(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponseDto> getOrganizationById(@PathVariable Long id) {
        OrganizationResponseDto response = organizationFacade.getOrganizationById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizationResponseDto> updateOrganization(@PathVariable Long id, @RequestBody OrganizationUpdateDto dto) {
        OrganizationResponseDto response = organizationFacade.updateOrganization(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        organizationFacade.deleteOrganization(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<OrganizationResponseDto>> getAllOrganizations() {
        List<OrganizationResponseDto> responses = organizationFacade.getAllOrganizations();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<OrganizationResponseDto>> searchOrganizationsByName(@RequestParam("keyword") String keyword) {
        List<OrganizationResponseDto> responses = organizationFacade.searchOrganizationsByName(keyword);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/domain")
    public ResponseEntity<OrganizationResponseDto> getOrganizationByDomain(@RequestParam("value") String domain) {
        OrganizationResponseDto response = organizationFacade.getOrganizationByDomain(domain);
        return ResponseEntity.ok(response);
    }
}