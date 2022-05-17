package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.H2Config;
import com.alkemy.ong.common.util.JsonUtils;
import com.alkemy.ong.domain.model.Comment;
import com.alkemy.ong.domain.model.CommentList;
import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.request.CreateCommentRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateCommentRequest;
import com.alkemy.ong.ports.input.rs.response.CommentResponseList;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringJUnitConfig(classes = H2Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentControllerIT {

    @Autowired MockMvc mockMvc;

    @Test
    @Order(1)
    @WithUserDetails("admin@somosmas.org")
    void createComment_shouldReturn201() throws Exception {

        CreateCommentRequest request = CreateCommentRequest.builder()
                .commentBody("example comment")
                .newsId(1L)
                .build();

        final String actualLocation = mockMvc.perform(post(ApiConstants.COMMENT_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        assertThat(actualLocation).isEqualTo("http://localhost/v1/comments/1");
    }

    @Test
    @Order(2)
    @WithUserDetails("admin@somosmas.org")
    void updateComment_shouldReturn204() throws Exception {
        UpdateCommentRequest request = UpdateCommentRequest.builder()
                .commentBody("update")
                .build();

        mockMvc.perform(put(ApiConstants.COMMENT_URI + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isNoContent())
                .andDo(print());

    }

    @Test
    @Order(3)
    @WithUserDetails("fbar@somosmas.org")
    void updateComment_shouldReturn403() throws Exception {
        UpdateCommentRequest request = UpdateCommentRequest.builder()
                .commentBody("second update")
                .build();

        mockMvc.perform(put(ApiConstants.COMMENT_URI + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isForbidden())
                .andDo(print());

    }

    @Test
    @Order(4)
    @WithAnonymousUser
    void getComments_shouldReturn401() throws Exception {
        mockMvc.perform(get(ApiConstants.COMMENT_URI))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @Order(5)
    @WithUserDetails("fbar@somosmas.org")
    void getComments_shouldReturn200() throws Exception{

        String content = mockMvc.perform(get(ApiConstants.COMMENT_URI))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).isNotBlank();

        CommentResponseList response = JsonUtils.jsonToObject(content, CommentResponseList.class);

        assertThat(response.getTotalElements()).isEqualTo(1);
        assertThat(response.getTotalPages()).isEqualTo(1);
        assertThat(response.getNextUri()).isEqualTo("http://localhost/v1/comments?page=1");
        assertThat(response.getPreviousUri()).isEqualTo("http://localhost/v1/comments?page=0");
        assertThat(response.getContent()).isNotEmpty();
    }


    @Test
    @Order(6)
    @WithUserDetails("fbar@somosmas.org")
    void deleteComment_shouldReturn403() throws Exception {

        mockMvc.perform(delete(ApiConstants.COMMENT_URI + "/1"))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

    }

    @Test
    @Order(7)
    @WithUserDetails("admin@somosmas.org")
    void deleteComment_shouldReturn204() throws Exception {

        mockMvc.perform(delete(ApiConstants.COMMENT_URI + "/1"))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

    }

}