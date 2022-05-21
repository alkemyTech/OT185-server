package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.H2Config;
import com.alkemy.ong.common.util.JsonUtils;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.request.CreateCategoryRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateCategoryRequest;
import com.alkemy.ong.ports.input.rs.response.CategoryNameResponseList;
import com.alkemy.ong.ports.input.rs.response.CategoryResponse;
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
class CategoryControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Order(1)
    @WithUserDetails("admin@somosmas.org")
    void createCategory_shouldReturn201() throws Exception {
        CreateCategoryRequest request = CreateCategoryRequest.builder()
                .name("foo")
                .description("foo description")
                .image("foo.jpg")
                .build();

        final String actualLocation = mockMvc.perform(post(ApiConstants.CATEGORIES_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        assertThat(actualLocation).isEqualTo("http://localhost/v1/categories/2");

    }

    @Test
    @Order(2)
    @WithUserDetails("admin@somosmas.org")
    void getCategory_shouldReturn200() throws Exception {
        String content = mockMvc.perform(get(ApiConstants.CATEGORIES_URI + "/2"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).isNotBlank();

        CategoryResponse response = JsonUtils.jsonToObject(content, CategoryResponse.class);
        assertThat(response.getId()).isEqualTo(2);
        assertThat(response.getName()).isEqualTo("foo");
        assertThat(response.getDescription()).isEqualTo("foo description");
        assertThat(response.getImage()).isEqualTo("foo.jpg");

    }


    @Test
    @Order(3)
    @WithUserDetails("admin@somosmas.org")
    void getCategories_shouldReturn200() throws Exception {
        String content = mockMvc.perform(get(ApiConstants.CATEGORIES_URI))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).isNotBlank();

        CategoryNameResponseList response = JsonUtils.jsonToObject(content, CategoryNameResponseList.class);

        assertThat(response.getTotalElements()).isEqualTo(2);
        assertThat(response.getTotalPages()).isEqualTo(1);
        assertThat(response.getNextUri()).isEqualTo("http://localhost/v1/categories?page=1");
        assertThat(response.getPreviousUri()).isEqualTo("http://localhost/v1/categories?page=0");
        assertThat(response.getContent()).isNotEmpty();
    }

    @Test
    @Order(4)
    @WithUserDetails("admin@somosmas.org")
    void updateCategory_shouldReturn200() throws Exception {

        UpdateCategoryRequest request = UpdateCategoryRequest.builder()
                .name("categoria")
                .description("new description")
                .image("new.jpg")
                .build();

        mockMvc.perform(put(ApiConstants.CATEGORIES_URI +  "/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @Order(5)
    @WithUserDetails("admin@somosmas.org")
    void deleteCategory() throws Exception {
        mockMvc.perform(delete(ApiConstants.CATEGORIES_URI + "/2"))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    @Order(6)
    @WithUserDetails("fbar@somosmas.org")
    void getCategory_shouldReturn404() throws Exception {
        mockMvc.perform(get(ApiConstants.CATEGORIES_URI + "/2"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @Order(7)
    @WithUserDetails("fbar@somosmas.org")
    void deleteCategory_shouldReturn403() throws Exception {
        mockMvc.perform(delete(ApiConstants.CATEGORIES_URI + "/2"))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @Order(8)
    @WithAnonymousUser
    void getCategory_shouldReturn401() throws Exception {
        mockMvc.perform(get(ApiConstants.CATEGORIES_URI + "/2"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }
}
