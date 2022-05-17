package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.common.util.JsonUtils;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.request.MemberRequest;
import org.assertj.core.api.Assertions;
import org.h2.security.auth.H2AuthConfig;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringJUnitConfig(classes = H2AuthConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberControllerIT {

    @Autowired
    MockMvc mockMvc;


    @Test
    @Order(1)
    @WithUserDetails("admin@somosmas.org")
    void createMembers_shouldReturn201() throws Exception {


        MemberRequest request = MemberRequest.builder()
                .name("member")
                .facebookUrl("facebook")
                .instagramUrl("instagram")
                .linkedinUrl("linkedin")
                .image("image")
                .description("some description")
                .build();

        final String actualLocation = mockMvc.perform(post(ApiConstants.MEMBERS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        Assertions.assertThat(actualLocation).isEqualTo("http://localhost/v1/members/1");

    }


}
