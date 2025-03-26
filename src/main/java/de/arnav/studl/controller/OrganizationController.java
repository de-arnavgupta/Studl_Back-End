package de.arnav.studl.controller;

import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
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
    public ResponseEntity<String> registerUser(@Valid @RequestBody OrganizationCreateDto organizatonCreateDto){
        organizationFacade.organizationRegister(organizatonCreateDto);
        return ResponseEntity.ok("Organization registered successfully");
    }

}
