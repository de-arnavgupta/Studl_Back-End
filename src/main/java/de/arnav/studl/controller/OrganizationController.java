package de.arnav.studl.controller;

import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.facade.OrganizationFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganizationController {

    private final OrganizationFacade organizationFacade;


    public OrganizationController(OrganizationController organizationController) {
        this.organizationFacade = organizationController.organizationFacade;
    }

    @PostMapping("/organization/register")
    public ResponseEntity<String> registerUser(@RequestBody OrganizationCreateDto organizatonCreateDto){
        organizationFacade.organizationRegister(organizatonCreateDto);
        return ResponseEntity.ok("Organization registered successfully");
    }

}
