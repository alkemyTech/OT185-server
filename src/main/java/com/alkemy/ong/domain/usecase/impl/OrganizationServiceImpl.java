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
    @Transactional(readOnly = true)
    public boolean getById(Long id) {
        return organizationJpaRepository.findById(id).isPresent();
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

    @Override
    @Transactional
    public Long updateEntity(Long id, Organization organization) {

        Long idOrganization = organizationJpaRepository.findById(id)
                .map(organizationJpa -> {
                    organizationJpa.setName(organization.getName());
                    organizationJpa.setImage(organization.getImage());
                    organizationJpa.setPhone(organization.getPhone());
                    organizationJpa.setEmail(organization.getEmail());
                    organizationJpa.setWelcomeText(organization.getWelcomeText());
                    organizationJpa.setAboutUsText(organization.getAboutUsText());
                    organizationJpa.setAddress(organization.getAddress());
                    organizationJpa.setFacebookUrl(organization.getFacebookUrl());
                    organizationJpa.setLinkedinUrl(organization.getLinkedinUrl());
                    organizationJpa.setInstagramUrl(organization.getInstagramUrl());

                    return organizationJpaRepository.save(organizationJpa).getId();
                }).orElseGet(() -> organizationJpaRepository.save(organization).getId());

        return idOrganization;
    }

}
