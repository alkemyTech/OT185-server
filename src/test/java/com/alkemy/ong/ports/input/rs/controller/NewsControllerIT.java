package com.alkemy.ong.ports.input.rs.controller;


import com.alkemy.ong.H2Config;
import com.alkemy.ong.common.util.JsonUtils;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.request.CreateNewsRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateNewsRequest;
import com.alkemy.ong.ports.input.rs.response.NewsResponse;
import com.alkemy.ong.ports.input.rs.response.NewsResponseList;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
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
class NewsControllerIT {


    @Autowired
    MockMvc mockMvc;

    @Test
    @Order(1)
    @WithUserDetails("admin@somosmas.org")
    void createNews_shouldReturn201() throws Exception {

        CreateNewsRequest request = CreateNewsRequest.builder()
                .name("foo")
                .image("foo")
                .content("foo")
                .categoryId(4L)
                .build();

        final String actualLocation = mockMvc.perform(post(ApiConstants.NEWS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        assertThat(actualLocation).isEqualTo("http://localhost/v1/news/1");
    }

    @Test
    @Order(2)
    @WithUserDetails("fbar@somosmas.org")
    void getNews_shouldReturn200() throws Exception {

        String content = mockMvc.perform(get(ApiConstants.NEWS_URI + "/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).isNotBlank();

        NewsResponse response = JsonUtils.jsonToObject(content, NewsResponse.class);
        assertThat(response.getId()).isEqualTo(1);
        assertThat(response.getName()).isEqualTo("foo");
        assertThat(response.getImage()).isEqualTo("foo");
        assertThat(response.getContent()).isEqualTo("foo");

    }

    @Test
    @Order(3)
    @WithUserDetails("fbar@somosmas.org")
    void getNewsList_shouldReturn200() throws Exception {

        String content = mockMvc.perform(get(ApiConstants.NEWS_URI))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).isNotBlank();

        NewsResponseList response = JsonUtils.jsonToObject(content, NewsResponseList.class);

        assertThat(response.getTotalElements()).isEqualTo(1);
        assertThat(response.getTotalPages()).isEqualTo(1);
        assertThat(response.getNextUri()).isEqualTo("http://localhost/v1/news?page=1");
        assertThat(response.getPreviousUri()).isEqualTo("http://localhost/v1/news?page=0");
        assertThat(response.getContent()).isNotEmpty();
    }

    @Test
    @Order(4)
    @WithUserDetails("admin@somosmas.org")
    void updateNews_shouldReturn204() throws Exception {

        UpdateNewsRequest request = UpdateNewsRequest.builder()
                .name("foo")
                .content("foo")
                .image("foo")
                .categoryId(4L)
                .build();


        mockMvc.perform(patch(ApiConstants.NEWS_URI + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @Order(5)
    @WithUserDetails("admin@somosmas.org")
    void deleteNews_shouldReturn204() throws Exception {

        mockMvc.perform(delete(ApiConstants.NEWS_URI + "/1"))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    @Order(6)
    @WithUserDetails("fbar@somosmas.org")
    void getNews_shouldReturn404() throws Exception {
        mockMvc.perform(get(ApiConstants.NEWS_URI + "/1"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @Order(7)
    @WithUserDetails("fbar@somosmas.org")
    void deleteNews_shouldReturn403() throws Exception {
        mockMvc.perform(delete(ApiConstants.NEWS_URI + "/1"))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @Order(8)
    @WithAnonymousUser
    void getNews_shouldReturn401() throws Exception {
        mockMvc.perform(get(ApiConstants.NEWS_URI + "/1"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }
}
