package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Member;

public interface MemberService {
	void deleteById(Long id);

	Long createMember(Member member);
}
