package com.alkemy.ong.domain.repository.usecase.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alkemy.ong.domain.repository.SlideRepository;
import com.alkemy.ong.domain.repository.usecase.SlideService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {

	private final SlideRepository slideJpaRepository;

	@Override
	@Transactional
	public void deleteById(Long id) {
		slideJpaRepository.findById(id).ifPresent(slideJpaRepository::delete);
	}
}
