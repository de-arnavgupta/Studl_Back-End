package de.arnav.studl.controller;

import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.organizationDto.OrganizationUpdateDto;
import de.arnav.studl.facade.OrganizationFacade;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private final OrganizationFacade organizationFacade;


    public OrganizationController(OrganizationFacade organizationFacade) {
        this.organizationFacade = organizationFacade;
    }

    @PostMapping("/register")
    public ResponseEntity<OrganizationResponseDto> registerUser(@Valid @RequestBody OrganizationCreateDto organizatonCreateDto){
        OrganizationResponseDto organizationResponseDto = organizationFacade.organizationRegister(organizatonCreateDto);
        return ResponseEntity.ok(organizationResponseDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrganizationResponseDto> updateUser(@Valid @RequestBody OrganizationUpdateDto organizationUpdateDto, @PathVariable Long id){
        OrganizationResponseDto organizationResponseDto = organizationFacade.update(organizationUpdateDto, id, true);
        return ResponseEntity.ok(organizationResponseDto);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<OrganizationResponseDto> updateUserPartially(@Valid @RequestBody OrganizationUpdateDto organizationUpdateDto, @PathVariable Long id){
        OrganizationResponseDto organizationResponseDto = organizationFacade.update(organizationUpdateDto, id, false);
        return ResponseEntity.ok(organizationResponseDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrganization(@Valid @PathVariable Long id) {
        organizationFacade.delete(id);
        return ResponseEntity.ok("✅ Organization and its uers deleted successfully");
    }

}
