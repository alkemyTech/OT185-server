package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.domain.usecase.OrganizationService;
import com.alkemy.ong.ports.input.rs.mapper.OrganizationControllerMapper;
import com.alkemy.ong.ports.input.rs.request.OrganizationRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateOrganizationRequest;
import com.alkemy.ong.ports.input.rs.response.OrganizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

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

    @PutMapping("/public/{id}")
    public ResponseEntity<Void> updateOrganization(@Valid @NotNull @PathVariable Long id, @Valid @RequestBody OrganizationRequest organizationRequest) {
        Organization organization = mapper.organizationRequestToOrganization(organizationRequest);
        if (!service.existById(id)) {
            final long idOrganizationCreated = service.updateEntity(id, organization);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{idOrganizationCreated}").buildAndExpand(idOrganizationCreated)
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            service.updateEntity(id, organization);
            return ResponseEntity.noContent().build();
        }
    }
}
