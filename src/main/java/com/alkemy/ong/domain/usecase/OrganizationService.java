package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Organization;

public interface OrganizationService {
    Organization getByIdIfExists(Long id);

    void updateEntityIfExists(Long id, Organization entity);

    Long updateEntity(Long id, Organization organization);

    boolean getById(Long id);
}
