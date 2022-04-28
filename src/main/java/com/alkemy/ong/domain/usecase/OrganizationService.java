package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Organization;

public interface OrganizationService {
    Organization getByIdIfExists(Long id);
}
