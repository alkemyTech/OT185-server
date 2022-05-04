package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.Member;
import com.alkemy.ong.ports.input.rs.request.MemberRequest;
import org.mapstruct.Mapper;

@Mapper
public interface MemberControllerMapper {
    Member memberRequestToMember (MemberRequest request);

}
