package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.domain.repository.OrganizationRepository;
import com.alkemy.ong.domain.usecase.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public Organization getByIdIfExists(Long id) {
        return organizationJpaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    @Override
    @Transactional
    public void updateEntityIfExists(Long id, Organization organization) {
        organizationJpaRepository.findById(id)
                .map(organizationJpa -> {
                    Optional.ofNullable(organization.getFacebookUrl()).ifPresent(organizationJpa::setFacebookUrl);
                    Optional.ofNullable(organization.getLinkedinUrl()).ifPresent(organizationJpa::setLinkedinUrl);
                    Optional.ofNullable(organization.getInstagramUrl()).ifPresent(organizationJpa::setInstagramUrl);

                    return organizationJpaRepository.save(organizationJpa);
                }).orElseThrow(() -> new NotFoundException(id));
    }

}
