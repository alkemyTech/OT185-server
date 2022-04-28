package com.alkemy.ong.domain.repository.usecase.impl;

import com.alkemy.ong.domain.repository.usecase.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.ong.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberJpaRepository;

	@Override
	@Transactional
	public void deleteById(Long id) {
		memberJpaRepository.findById(id).ifPresent(memberJpaRepository::delete);
	}

}
