package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.domain.model.Alkymer;
import com.alkemy.ong.domain.model.AlkymerList;
import com.alkemy.ong.domain.model.Skill;
import com.alkemy.ong.domain.repository.AlkymerRepository;
import com.alkemy.ong.domain.repository.SkillRepository;
import com.alkemy.ong.domain.usecase.AlkymerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class AlkymerServiceImpl implements AlkymerService {

    private final AlkymerRepository alkymerJpaRepository;
    private final SkillRepository skillJpaRepository;

    @Override
    @Transactional
    public Long createEntity(Alkymer alkymer) {
        alkymer.getSkills().forEach(addSkillsToAlkymer(alkymer));
        return alkymerJpaRepository.save(alkymer).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Alkymer getByIdIfExists(Long id) {
        return alkymerJpaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public AlkymerList getList(PageRequest pageRequest) {
        Page<Alkymer> page = alkymerJpaRepository.findAll(pageRequest);
        return new AlkymerList(page.getContent(), pageRequest, page.getTotalElements());
    }

    @Override
    @Transactional
    public void updateEntityIfExists(Long id, Alkymer alkymer) {
        alkymerJpaRepository.findById(id)
                .map(alkymerJpa -> {
                    Optional.ofNullable(alkymer.getStartDate()).ifPresent(alkymerJpa::setStartDate);
                    Optional.ofNullable(alkymer.getEndDate()).ifPresent(alkymerJpa::setEndDate);
                    alkymer.getSkills().forEach(addSkillsToAlkymer(alkymerJpa));

                    return alkymerJpaRepository.save(alkymerJpa);
                }).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        alkymerJpaRepository.findById(id).ifPresent(alkymerJpaRepository::delete);
    }

    private Consumer<Skill> addSkillsToAlkymer(Alkymer alkymer) {
        return skill -> skillJpaRepository.findById(skill.getId())
                .ifPresent(skillJpa -> alkymer.getSkills().add(skillJpa));
    }

}
