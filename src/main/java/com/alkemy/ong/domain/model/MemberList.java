package com.alkemy.ong.domain.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class MemberList extends PageImpl<Member> {
    public MemberList(List<Member> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}
