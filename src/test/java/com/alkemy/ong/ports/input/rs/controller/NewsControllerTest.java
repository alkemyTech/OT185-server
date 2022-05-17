package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.common.exception.handler.GlobalExceptionHandler;
import com.alkemy.ong.common.util.JsonUtils;
import com.alkemy.ong.domain.model.Category;
import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.domain.usecase.NewsService;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.mapper.NewsControllerMapper;
import com.alkemy.ong.ports.input.rs.request.CreateNewsRequest;
import com.alkemy.ong.ports.input.rs.response.AlkymerResponse;
import com.alkemy.ong.ports.input.rs.response.NewsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class NewsControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    NewsController controller;

    @Mock
    NewsService service;
    @Spy
    NewsControllerMapper mapper = Mappers.getMapper(NewsControllerMapper.class);

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
    }

    @Test
    void createNews_shouldReturn201() throws Exception {
        CreateNewsRequest request = CreateNewsRequest.builder()
                .name("foo")
                .image("foo")
                .content("foo")
                .build();

        given(service.createEntity(any(News.class))).willReturn(99L);

        final String actualLocation = mockMvc.perform(post(ApiConstants.NEWS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        assertThat(actualLocation).isEqualTo("http://localhost/v1/news/99");
    }

    @Test
    void getNews() throws Exception {
        Category category = new Category();
        category.setName("Java");
        category.setImage("foo");
        category.setDescription("foo");
        category.setId(4L);


        News news = new News();
        news.setId(99L);
        news.setContent("foo");
        news.setImage("foo");
        news.setComments(List.of());
        news.setName("foo");
        news.setCategory(category);

        given(service.getByIdIfExists(99L)).willReturn(news);

        String content = mockMvc.perform(get(ApiConstants.NEWS_URI + "/99"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        NewsResponse response = JsonUtils.jsonToObject(content, NewsResponse.class);
        assertThat(content).isNotBlank();
        assertThat(response.getId()).isEqualTo(99);
        assertThat(response.getName()).isEqualTo("foo");
        //assertThat(response.getName()).isNotEmpty();
        //assertThat(response.getSkills().stream().findFirst().orElseThrow().getName()).isEqualTo("Java");
    }

    @Test
    void updateNews() {
    }

    @Test
    void deleteNews() {
    }

    @Test
    void getNew() {
    }

    @Test
    void getCommentsByNewsId() {
    }


}