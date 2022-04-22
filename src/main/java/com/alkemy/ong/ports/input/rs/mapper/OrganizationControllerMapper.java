package com.alkemy.ong.ports.input.rs.mapper;


import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.ports.input.rs.response.OrganizationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface OrganizationControllerMapper{

    @Named("organizationToOrganizationResponse")
    OrganizationResponse organizationToOrganizationResponse(Organization organization);

}
