package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.common.exception.handler.GlobalExceptionHandler;
import com.alkemy.ong.common.util.JsonUtils;
import com.alkemy.ong.domain.model.Category;
import com.alkemy.ong.domain.model.CategoryList;
import com.alkemy.ong.domain.usecase.CategoryService;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.mapper.CategoryControllerMapper;
import com.alkemy.ong.ports.input.rs.request.CreateCategoryRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateCategoryRequest;
import com.alkemy.ong.ports.input.rs.response.CategoryNameResponseList;
import com.alkemy.ong.ports.input.rs.response.CategoryResponse;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    private MockMvc mockMvc;

    @InjectMocks CategoryController controller;
    @Mock
    CategoryService service;
    @Spy
    CategoryControllerMapper mapper = Mappers.getMapper(CategoryControllerMapper.class);

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
    }


    @Test
    void createCategory_shouldReturn201() throws Exception {
        CreateCategoryRequest request = CreateCategoryRequest.builder()
                .name("foo")
                .description("foo description")
                .image("foo.jpg")
                .build();

        given(service.createCategory(any(Category.class))).willReturn(99L);

        final String actualLocation = mockMvc.perform(post(ApiConstants.CATEGORIES_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        assertThat(actualLocation).isEqualTo("http://localhost/v1/categories/99");
    }
    @Test
    void getCategory_shouldReturn200() throws Exception {

        Category c = new Category();
        c.setId(99L);
        c.setName("foo");
        c.setDescription("foo description");
        c.setImage("foo.jpg");

        given(service.getByIdIfExists(99L)).willReturn(c);

        String content = mockMvc.perform(get(ApiConstants.CATEGORIES_URI + "/99"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        CategoryResponse response = JsonUtils.jsonToObject(content, CategoryResponse.class);
        assertThat(content).isNotBlank();
        assertThat(response.getId()).isEqualTo(99);
        assertThat(response.getName()).isEqualTo("foo");
        assertThat(response.getDescription()).isEqualTo("foo description");
        assertThat(response.getImage()).isEqualTo("foo.jpg");
    }

    @Test
    void getCategories_shouldReturn200() throws Exception {
        Category c = new Category();
        c.setId(99L);
        c.setName("foo");
        c.setDescription("foo description");
        c.setImage("foo.jpg");

        CategoryList list = new CategoryList(List.of(c), Pageable.ofSize(1), 1);
        given(service.getList(any(PageRequest.class))).willReturn(list);

        String content = mockMvc.perform(get(ApiConstants.CATEGORIES_URI+"?page=0&size=1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).isNotBlank();

        CategoryNameResponseList response = JsonUtils.jsonToObject(content, CategoryNameResponseList.class);

        assertThat(response.getTotalElements()).isEqualTo(1);
        assertThat(response.getTotalPages()).isEqualTo(1);
        assertThat(response.getNextUri()).isEqualTo("http://localhost/v1/categories?size=1&page=1");
        assertThat(response.getPreviousUri()).isEqualTo("http://localhost/v1/categories?size=1&page=0");
        assertThat(response.getContent()).isNotEmpty();
    }


    @Test
    void updateCategory_shouldReturn200() throws Exception {
        Category c = new Category();
        c.setId(99L);
        c.setName("foo");
        c.setDescription("new description");
        c.setImage("new.jpg");

        UpdateCategoryRequest request = UpdateCategoryRequest.builder()
                .name("foo")
                .description("foo description")
                .image("foo.jpg")
                .build();

        given(service.updateCategory(eq(99L), any(Category.class))).willReturn(c);

        mockMvc.perform(put(ApiConstants.CATEGORIES_URI +  "/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteCategory_shouldReturn204() throws Exception {
        mockMvc.perform(delete(ApiConstants.CATEGORIES_URI + "/1"))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}