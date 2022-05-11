package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.Member;
import com.alkemy.ong.ports.input.rs.request.MemberRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateMemberRequest;
import com.alkemy.ong.ports.input.rs.response.MemberResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MemberControllerMapper extends CommonMapper {
    Member memberRequestToMember(MemberRequest request);

    Member updateMemberRequestToMember(UpdateMemberRequest request);

    List<MemberResponse> memberListToMemberResponseList(List<Member> list);
}
