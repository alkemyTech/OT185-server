package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.common.exception.handler.GlobalExceptionHandler;
import com.alkemy.ong.common.util.JsonUtils;
import com.alkemy.ong.domain.model.Comment;
import com.alkemy.ong.domain.model.CommentList;
import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.usecase.CommentService;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.mapper.CommentControllerMapper;
import com.alkemy.ong.ports.input.rs.request.CreateCommentRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateCommentRequest;
import com.alkemy.ong.ports.input.rs.response.CommentResponseList;
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

import java.util.Calendar;
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
class CommentControllerTest {

    private MockMvc mockMvc;

    @InjectMocks CommentController controller;
    @Mock CommentService service;
    @Spy CommentControllerMapper mapper = Mappers.getMapper(CommentControllerMapper.class);


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
    }

    @Test
    void createComment_shouldReturn201() throws Exception {

        CreateCommentRequest request = CreateCommentRequest.builder()
                .commentBody("example comment")
                .newsId(1L)
                .build();

        given(service.createComment(any(Comment.class), any(User.class) )).willReturn(99L);

        final String actualLocation = mockMvc.perform(post(ApiConstants.COMMENT_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(request)))
        .andExpect(status().isCreated())
        .andDo(print())
        .andReturn()
        .getResponse()
        .getHeader(HttpHeaders.LOCATION);

        assertThat(actualLocation).isEqualTo("http://localhost/v1/comments/99");
    }

    @Test
    void updateComment_shouldReturn204() throws Exception {
        UpdateCommentRequest request = UpdateCommentRequest.builder()
                .commentBody("update")
                .build();

        willDoNothing().given(service).updateCommentIfExists(eq(99L), any(Comment.class), any(User.class));

        mockMvc.perform(put(ApiConstants.COMMENT_URI + "/99")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isNoContent())
                .andDo(print());

    }

    @Test
    void getComments_shouldReturn200() throws Exception{

        News news = new News();
        news.setName("Some News");

        User user = new User();
        user.setFirstName("Guy");

        Comment comment = new Comment();
        comment.setId(99L);
        comment.setCommentBody("Some comment");
        comment.setNews(news);
        comment.setUser(user);
        CommentList list = new CommentList(List.of(comment), Pageable.ofSize(1), 1);

        given(service.getAll(any(PageRequest.class))).willReturn(list);

        String content = mockMvc.perform(get(ApiConstants.COMMENT_URI + "?page=0&size=1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).isNotBlank();

        CommentResponseList response = JsonUtils.jsonToObject(content, CommentResponseList.class);

        assertThat(response.getTotalElements()).isEqualTo(1);
        assertThat(response.getTotalPages()).isEqualTo(1);
        assertThat(response.getNextUri()).isEqualTo("http://localhost/v1/comments?size=1&page=1");
        assertThat(response.getPreviousUri()).isEqualTo("http://localhost/v1/comments?size=1&page=0");
        assertThat(response.getContent()).isNotEmpty();


    }

    @Test
    void deleteComment_shouldReturn204() throws Exception {

        mockMvc.perform(delete(ApiConstants.COMMENT_URI + "/1"))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

    }
}