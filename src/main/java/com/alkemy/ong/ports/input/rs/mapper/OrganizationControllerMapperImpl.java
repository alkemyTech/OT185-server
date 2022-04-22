package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.ports.input.rs.response.OrganizationResponse;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Amazon.com Inc.)"
)
@Component
public class OrganizationControllerMapperImpl implements OrganizationControllerMapper {

    @Override
    public OrganizationResponse organizationToOrganizationResponse(Organization organization) {
        if ( organization == null ) {
            return null;
        }

        OrganizationResponse organizationResponse = new OrganizationResponse();

        organizationResponse.setId( organization.getId() );
        organizationResponse.setName( organization.getName() );
        organizationResponse.setImage( organization.getImage() );
        organizationResponse.setPhone( organization.getPhone() );
        organizationResponse.setAddress( organization.getAddress() );

        return organizationResponse;
    }
}
