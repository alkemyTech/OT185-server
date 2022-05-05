package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.Member;
import com.alkemy.ong.ports.input.rs.request.MemberRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateMemberRequest;
import org.mapstruct.Mapper;

@Mapper
public interface MemberControllerMapper extends CommonMapper {
    Member memberRequestToMember(MemberRequest request);

    Member updateMemberRequestToMember(UpdateMemberRequest request);
}
