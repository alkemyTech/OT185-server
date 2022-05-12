package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.domain.model.Member;
import com.alkemy.ong.domain.model.MemberList;
import com.alkemy.ong.domain.usecase.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.ong.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
	@Transactional
	public Long createMember(Member member) {
		return memberJpaRepository.save(member).getId();
	}

	@Override
	@Transactional
	public void updateMember(Long id, Member upMember) {

		memberJpaRepository.findById(id)
				.map(memberJpa -> {

					memberJpa.setFacebookUrl(upMember.getFacebookUrl());
					memberJpa.setInstagramUrl(upMember.getInstagramUrl());
					memberJpa.setLinkedinUrl(upMember.getLinkedinUrl());
					memberJpa.setImage(upMember.getImage());
					memberJpa.setDescription(upMember.getDescription());

					return memberJpaRepository.save(memberJpa);
				}).orElseThrow(() -> new NotFoundException(id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Member> getAll() {
		return (List<Member>) memberJpaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public MemberList getAll(PageRequest pageRequest) {
		Page<Member> page = memberJpaRepository.findAll(pageRequest);
		return  new MemberList(page.getContent(), pageRequest, page.getTotalElements());
	}

}
