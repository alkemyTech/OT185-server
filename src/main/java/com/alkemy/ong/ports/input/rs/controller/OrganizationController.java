package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.domain.usecase.OrganizationService;
import com.alkemy.ong.ports.input.rs.mapper.OrganizationControllerMapper;
import com.alkemy.ong.ports.input.rs.request.UpdateOrganizationRequest;
import com.alkemy.ong.ports.input.rs.response.OrganizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<OrganizationResponse> getOrganization(@Valid @NotNull @PathVariable Long id) {
        Organization organization = service.getByIdIfExists(id);
        OrganizationResponse response = mapper.organizationToOrganizationResponse(organization);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrganization(@Valid @NotNull @PathVariable Long id, @Valid @RequestBody UpdateOrganizationRequest updateOrganizationRequest) {
        Organization organization = mapper.updateOrganizationRequestToOrganization(updateOrganizationRequest);
        service.updateEntityIfExists(id, organization);
    }
}
