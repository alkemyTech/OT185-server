package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.domain.model.Member;
import com.alkemy.ong.domain.usecase.MemberService;
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

	@Override
	public Long createMember(Member member) {
		return memberJpaRepository.save(member).getId();
	}

}
