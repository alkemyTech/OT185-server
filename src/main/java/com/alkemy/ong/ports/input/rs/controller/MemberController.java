package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.model.Member;
import com.alkemy.ong.ports.input.rs.mapper.MemberControllerMapper;
import com.alkemy.ong.ports.input.rs.request.MemberRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateMemberRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alkemy.ong.domain.usecase.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.MEMBERS_URI;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping(MEMBERS_URI)
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberControllerMapper mapper;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@Valid @NotNull @PathVariable Long id) {
        memberService.deleteById(id);
    }



    @PostMapping
    public ResponseEntity<Void> createMember(@Valid @RequestBody MemberRequest request) {

        Member member = mapper.memberRequestToMember(request);
        final long id = memberService.createMember(member);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMember(@Valid @NotNull @PathVariable Long id,
                             @RequestBody UpdateMemberRequest upMember) {

        Member member = mapper.updateMemberRequestToMember(upMember);
        memberService.updateMember(id, member);
    }


}
