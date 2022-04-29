package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.domain.usecase.OrganizationService;
import com.alkemy.ong.ports.input.rs.api.ApiGetByIdMappingDocs;
import com.alkemy.ong.ports.input.rs.api.ApiResponsesBody.ApiOrganizationResponseDocs;
import com.alkemy.ong.ports.input.rs.mapper.OrganizationControllerMapper;
import com.alkemy.ong.ports.input.rs.response.OrganizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.ORGANIZATIONS_URI;

@RestController
@RequestMapping(ORGANIZATIONS_URI)
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService service;
    private final OrganizationControllerMapper mapper;

    @GetMapping("/public/{id}")
    @ApiGetByIdMappingDocs
    @ApiOrganizationResponseDocs
    public ResponseEntity<OrganizationResponse> getOrganization(@Valid @NotNull @PathVariable Long id) {
        Organization organization = service.getByIdIfExists(id);
        OrganizationResponse response = mapper.organizationToOrganizationResponse(organization);
        return ResponseEntity.ok(response);
    }
}
