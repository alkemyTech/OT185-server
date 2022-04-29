package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.domain.usecase.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.MEMBERS_URI;

@RestController
@RequestMapping(MEMBERS_URI)
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@Valid @NotNull @PathVariable Long id) {
        memberService.deleteById(id);
    }
}
