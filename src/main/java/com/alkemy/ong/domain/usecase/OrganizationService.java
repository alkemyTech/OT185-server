package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.domain.model.Slide;

import java.util.List;

public interface OrganizationService {
    Organization getByIdIfExists(Long id);

    void updateEntityIfExists(Long id, Organization entity);

    Long updateEntity(Long id, Organization organization);

    boolean existById(Long id);

    List<Slide> findSlides(Long id);
}
