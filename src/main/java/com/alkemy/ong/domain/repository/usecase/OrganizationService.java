package com.alkemy.ong.domain.repository.usecase;

import com.alkemy.ong.domain.model.Organization;

public interface OrganizationService {
    Organization getByIdIfExists(Long id);
}
