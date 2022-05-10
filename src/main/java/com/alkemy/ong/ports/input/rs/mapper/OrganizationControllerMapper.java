package com.alkemy.ong.ports.input.rs.mapper;


import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.ports.input.rs.request.OrganizationRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateOrganizationRequest;
import com.alkemy.ong.ports.input.rs.response.OrganizationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface OrganizationControllerMapper extends CommonMapper {

    @Named("organizationToOrganizationResponse")
    OrganizationResponse organizationToOrganizationResponse(Organization organization);

    Organization updateOrganizationRequestToOrganization(UpdateOrganizationRequest update);

    Organization organizationRequestToOrganization(OrganizationRequest request);
}
