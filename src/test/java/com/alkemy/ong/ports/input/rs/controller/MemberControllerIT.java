package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.H2Config;
import com.alkemy.ong.common.util.JsonUtils;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.request.MemberRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateMemberRequest;
import com.alkemy.ong.ports.input.rs.response.AlkymerResponseList;
import com.alkemy.ong.ports.input.rs.response.MemberResponseList;
import org.assertj.core.api.Assertions;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringJUnitConfig(classes = H2Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberControllerIT {

    @Autowired
    MockMvc mockMvc;


    /* * * CREATE TEST * * */

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


    @Test
    @Order(2)
    @WithUserDetails("admin@somosmas.org")
    void createMembers_shouldReturn400_name_null() throws Exception {
        MemberRequest request = MemberRequest.builder()
                .facebookUrl("facebook")
                .instagramUrl("instagram")
                .linkedinUrl("linkedin")
                .image("image")
                .description("some description")
                .build();

        mockMvc.perform(post(ApiConstants.MEMBERS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Order(3)
    void createMembers_shouldReturn401_Unauthorized() throws Exception {
        MemberRequest request = MemberRequest.builder()
                .facebookUrl("facebook")
                .instagramUrl("instagram")
                .linkedinUrl("linkedin")
                .image("image")
                .description("some description")
                .build();

        mockMvc.perform(post(ApiConstants.MEMBERS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }


    @Test
    @Order(4)
    @WithUserDetails("admin@somosmas.org")
    void createMembers_shouldReturn400_image_null() throws Exception {
        MemberRequest request = MemberRequest.builder()
                .name("member")
                .facebookUrl("facebook")
                .instagramUrl("instagram")
                .linkedinUrl("linkedin")
                .description("some description")
                .build();

        mockMvc.perform(post(ApiConstants.MEMBERS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Order(5)
    @WithUserDetails("admin@somosmas.org")
    void createMembers_shouldReturn400_description_null() throws Exception {
        MemberRequest request = MemberRequest.builder()
                .name("member")
                .facebookUrl("facebook")
                .instagramUrl("instagram")
                .linkedinUrl("linkedin")
                .description("some description")
                .build();

        mockMvc.perform(post(ApiConstants.MEMBERS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Order(6)
    @WithUserDetails("user@somosmas.org")
    void createMembers_shouldReturn403_role_user_forbidden() throws Exception {
        MemberRequest request = MemberRequest.builder()
                .name("member")
                .facebookUrl("facebook")
                .instagramUrl("instagram")
                .linkedinUrl("linkedin")
                .image("image")
                .description("some description")
                .build();

        mockMvc.perform(post(ApiConstants.MEMBERS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    /* * * UPDATE TEST * * */

    @Test
    @Order(7)
    @WithUserDetails("admin@somosmas.org")
    void updateMembers_shouldReturn204() throws Exception {
        UpdateMemberRequest request = UpdateMemberRequest.builder()
                .facebookUrl("facebook update")
                .instagramUrl("instagram update")
                .linkedinUrl("linkedin update")
                .image("image update")
                .description("some description update")
                .build();

        mockMvc.perform(put(ApiConstants.MEMBERS_URI + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isNoContent())
                .andDo(print());
    }


    @Test
    @Order(8)
    @WithUserDetails("admin@somosmas.org")
    void updateMembers_shouldReturn400_bad_request() throws Exception {
        UpdateMemberRequest request = UpdateMemberRequest.builder()
                .facebookUrl("facebook update")
                .instagramUrl("instagram update")
                .linkedinUrl("linkedin update")
                .image("image update")
                .description("some description update")
                .build();

        mockMvc.perform(put(ApiConstants.MEMBERS_URI + "/string")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }


    @Test
    @Order(9)
    void updateMembers_shouldReturn401_Unauthorized() throws Exception {
        UpdateMemberRequest request = UpdateMemberRequest.builder()
                .facebookUrl("facebook update")
                .instagramUrl("instagram update")
                .linkedinUrl("linkedin update")
                .image("image update")
                .description("some description update")
                .build();

        mockMvc.perform(put(ApiConstants.MEMBERS_URI + "/string")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @Order(10)
    @WithUserDetails("user@somosmas.org")
    void updateMembers_shouldReturn403_role_user_forbidden() throws Exception {
        MemberRequest request = MemberRequest.builder()
                .name("member")
                .facebookUrl("facebook")
                .instagramUrl("instagram")
                .linkedinUrl("linkedin")
                .image("image")
                .description("some description")
                .build();

        mockMvc.perform(post(ApiConstants.MEMBERS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }


    @Test
    @Order(11)
    @WithUserDetails("admin@somosmas.org")
    void updateMembers_shouldReturn404_not_found_id() throws Exception {
        UpdateMemberRequest request = UpdateMemberRequest.builder()
                .facebookUrl("facebook update")
                .instagramUrl("instagram update")
                .linkedinUrl("linkedin update")
                .image("image update")
                .description("some description update")
                .build();

        mockMvc.perform(put(ApiConstants.MEMBERS_URI + "/50")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }


    @Test
    @Order(12)
    @WithUserDetails("user@somosmas.org")
    void getAllMembers_shouldReturn200() throws Exception {
        String content = mockMvc.perform(get(ApiConstants.MEMBERS_URI))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).isNotBlank();

        MemberResponseList response = JsonUtils.jsonToObject(content, MemberResponseList.class);

        assertThat(response.getTotalElements()).isEqualTo(1);
        assertThat(response.getTotalPages()).isEqualTo(1);
        assertThat(response.getNextUri()).isEqualTo("http://localhost/v1/members?page=1");
        assertThat(response.getPreviousUri()).isEqualTo("http://localhost/v1/members?page=0");
        assertThat(response.getContent()).isNotEmpty();
    }


    /* * * DELETE TEST * * */

    @Test
    @Order(13)
    @WithUserDetails("admin@somosmas.org")
    void deteleMember_shouldReturn204() throws Exception {
        mockMvc.perform(delete(ApiConstants.MEMBERS_URI + "/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(14)
    @WithUserDetails("admin@somosmas.org")
    void deteleMember_shouldReturn400_invalid_request() throws Exception {
        mockMvc.perform(delete(ApiConstants.MEMBERS_URI + "/string"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(15)
    void deteleMember_shouldReturn401_Unauthorized() throws Exception {
        mockMvc.perform(delete(ApiConstants.MEMBERS_URI + "/string"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(16)
    @WithUserDetails("user@somosmas.org")
    void deteleMember_shouldReturn403_role_user_forbidden() throws Exception {
        mockMvc.perform(delete(ApiConstants.MEMBERS_URI + "/string"))
                .andExpect(status().isForbidden());
    }
}
