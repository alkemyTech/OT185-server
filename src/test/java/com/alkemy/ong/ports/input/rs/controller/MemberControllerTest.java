package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.common.exception.handler.GlobalExceptionHandler;
import com.alkemy.ong.common.util.JsonUtils;
import com.alkemy.ong.domain.model.*;
import com.alkemy.ong.domain.usecase.MemberService;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.mapper.MemberControllerMapper;
import com.alkemy.ong.ports.input.rs.request.MemberRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateMemberRequest;
import com.alkemy.ong.ports.input.rs.response.AlkymerResponseList;
import com.alkemy.ong.ports.input.rs.response.MemberResponseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    MemberController controller;
    @Mock
    MemberService service;
    @Spy
    MemberControllerMapper mapper;

    {
        mapper = Mappers.getMapper(MemberControllerMapper.class);
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
    }

    @Test
    void createMember_shouldReturn201() throws Exception {

        MemberRequest request = MemberRequest.builder()
                .name("member")
                .facebookUrl("facebook")
                .instagramUrl("instagram")
                .linkedinUrl("linkedin")
                .image("image")
                .description("some description")
                .build();


        given(service.createMember(any(Member.class))).willReturn(99L);

        final String actualLocation = mockMvc.perform(post(ApiConstants.MEMBERS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        assertThat(actualLocation).isEqualTo("http://localhost/v1/members/99");
    }


    @Test
    void updateMember_shouldReturn200() throws Exception {

        UpdateMemberRequest request = UpdateMemberRequest.builder()
                .facebookUrl("facebook")
                .instagramUrl("instagram")
                .linkedinUrl("linkedin")
                .image("image")
                .description("some description")
                .build();

        final long id = 1L;

        willDoNothing().given(service).updateMember(eq(1L), any(Member.class));

        mockMvc.perform(put(ApiConstants.MEMBERS_URI + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void deleteMember_shouldReturn204() throws Exception {

        mockMvc.perform(delete(ApiConstants.MEMBERS_URI + "/1"))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void getAlkymers_shouldReturn200() throws Exception {


        Member member = new Member();
        member.setId(99L);
        member.setName("member");
        member.setImage("image");
        member.setDescription("description");


        MemberList list = new MemberList(List.of(member), Pageable.ofSize(1), 1);

        given(service.getAll(any(PageRequest.class))).willReturn(list);

        String content = mockMvc.perform(get(ApiConstants.MEMBERS_URI+"?page=0&size=1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).isNotBlank();

        MemberResponseList response = JsonUtils.jsonToObject(content, MemberResponseList.class);

        assertThat(response.getTotalElements()).isEqualTo(1);
        assertThat(response.getTotalPages()).isEqualTo(1);
        assertThat(response.getNextUri()).isEqualTo("http://localhost/v1/members?size=1&page=1");
        assertThat(response.getPreviousUri()).isEqualTo("http://localhost/v1/members?size=1&page=0");
        assertThat(response.getContent()).isNotEmpty();
    }

}
