package com.alkemy.ong.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.alkemy.ong.domain.model.Member;

@Repository
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {

}
