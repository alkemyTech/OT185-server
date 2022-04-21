package com.alkemy.ong.domain.usecase.impl;

import org.springframework.stereotype.Service;
import com.alkemy.ong.domain.repository.MemberRepository;
import com.alkemy.ong.domain.usecase.MemberService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberJpaRepository;

	@Override
	public void deleteById(Long id) {
		memberJpaRepository.findById(id).ifPresent(memberJpaRepository::delete);
	}

}
