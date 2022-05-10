package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Member;

import java.util.List;

public interface MemberService {
	void deleteById(Long id);

	Long createMember(Member member);

	void updateMember(Long id, Member upMember);

	List<Member> getAll();


}
