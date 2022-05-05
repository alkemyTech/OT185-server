package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.common.exception.handler.GlobalExceptionHandler;
import com.alkemy.ong.common.util.JsonUtils;
import com.alkemy.ong.domain.model.Alkymer;
import com.alkemy.ong.domain.model.AlkymerList;
import com.alkemy.ong.domain.model.Skill;
import com.alkemy.ong.domain.usecase.AlkymerService;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.mapper.AlkymerControllerMapper;
import com.alkemy.ong.ports.input.rs.request.CreateAlkymerRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateAlkymerRequest;
import com.alkemy.ong.ports.input.rs.response.AlkymerResponse;
import com.alkemy.ong.ports.input.rs.response.AlkymerResponseList;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AlkymerControllerTest {

    private MockMvc mockMvc;

    @InjectMocks AlkymerController controller;
    @Mock AlkymerService service;
    @Spy AlkymerControllerMapper mapper = Mappers.getMapper(AlkymerControllerMapper.class);

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
    }

    @Test
    void createAlkymers_shouldReturn201() throws Exception {

        CreateAlkymerRequest request = CreateAlkymerRequest.builder()
                .name("foo")
                .startDate(Calendar.getInstance().getTime())
                .skills(List.of())
                .build();

        given(service.createEntity(any(Alkymer.class))).willReturn(99L);

        final String actualLocation = mockMvc.perform(post(ApiConstants.ALKYMERS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        assertThat(actualLocation).isEqualTo("http://localhost/v1/alkymers/99");
    }

    @Test
    void getAlkymer_shouldReturn200() throws Exception {

        Skill skill = new Skill();
        skill.setName("Java");

        Alkymer alkymer = new Alkymer();
        alkymer.setId(99L);
        alkymer.setName("foo");
        alkymer.getSkills().add(skill);

        given(service.getByIdIfExists(99L)).willReturn(alkymer);

        String content = mockMvc.perform(get(ApiConstants.ALKYMERS_URI + "/99"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        AlkymerResponse response = JsonUtils.jsonToObject(content, AlkymerResponse.class);
        assertThat(content).isNotBlank();
        assertThat(response.getId()).isEqualTo(99);
        assertThat(response.getName()).isEqualTo("foo");
        assertThat(response.getSkills()).isNotEmpty();
        assertThat(response.getSkills().stream().findFirst().orElseThrow().getName()).isEqualTo("Java");
    }

    @Test
    void getAlkymers_shouldReturn200() throws Exception {

        Skill skill = new Skill();
        skill.setName("Java");

        Alkymer alkymer = new Alkymer();
        alkymer.setId(99L);
        alkymer.setName("foo");
        alkymer.getSkills().add(skill);

        AlkymerList list = new AlkymerList(List.of(alkymer), Pageable.ofSize(1), 1);

        given(service.getList(any(PageRequest.class))).willReturn(list);

        String content = mockMvc.perform(get(ApiConstants.ALKYMERS_URI+"?page=0&size=1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).isNotBlank();

        AlkymerResponseList response = JsonUtils.jsonToObject(content, AlkymerResponseList.class);

        assertThat(response.getTotalElements()).isEqualTo(1);
        assertThat(response.getTotalPages()).isEqualTo(1);
        assertThat(response.getNextUri()).isEqualTo("http://localhost/v1/alkymers?size=1&page=1");
        assertThat(response.getPreviousUri()).isEqualTo("http://localhost/v1/alkymers?size=1&page=0");
        assertThat(response.getContent()).isNotEmpty();
    }

    @Test
    void updateAlkeymer_shouldReturn204() throws Exception {

        UpdateAlkymerRequest request = UpdateAlkymerRequest.builder()
                .endDate(Calendar.getInstance().getTime())
                .skills(List.of())
                .build();

        willDoNothing().given(service).updateEntityIfExists(eq(99L), any(Alkymer.class));

        mockMvc.perform(patch(ApiConstants.ALKYMERS_URI + "/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void deleteAlkeymer_shouldReturn204() throws Exception {

        mockMvc.perform(delete(ApiConstants.ALKYMERS_URI + "/1"))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

}
