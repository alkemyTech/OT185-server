package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.domain.model.Alkymer;
import com.alkemy.ong.domain.model.AlkymerList;
import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.domain.model.Skill;
import com.alkemy.ong.domain.repository.AlkymerRepository;
import com.alkemy.ong.domain.repository.OrganizationRepository;
import com.alkemy.ong.domain.repository.SkillRepository;
import com.alkemy.ong.domain.usecase.AlkymerService;
import com.alkemy.ong.domain.usecase.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public Organization getByIdIfExists(Long id) {
        return organizationJpaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

}
