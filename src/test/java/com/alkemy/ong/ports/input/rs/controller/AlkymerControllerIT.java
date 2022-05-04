package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.H2Config;
import com.alkemy.ong.common.util.JsonUtils;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.request.CreateAlkymerRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateAlkymerRequest;
import com.alkemy.ong.ports.input.rs.response.AlkymerResponse;
import com.alkemy.ong.ports.input.rs.response.AlkymerResponseList;
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

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringJUnitConfig(classes = H2Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AlkymerControllerIT {

    @Autowired MockMvc mockMvc;

    @Test
    @Order(1)
    @WithUserDetails("admin@somosmas.org")
    void createAlkymers_shouldReturn201() throws Exception {

        CreateAlkymerRequest request = CreateAlkymerRequest.builder()
                .name("foo")
                .startDate(Calendar.getInstance().getTime())
                .skills(List.of())
                .build();

        final String actualLocation = mockMvc.perform(post(ApiConstants.ALKYMERS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        assertThat(actualLocation).isEqualTo("http://localhost/v1/alkymers/1");
    }

    @Test
    @Order(2)
    @WithUserDetails("fbar@somosmas.org")
    void getAlkymer_shouldReturn200() throws Exception {

        String content = mockMvc.perform(get(ApiConstants.ALKYMERS_URI + "/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).isNotBlank();

        AlkymerResponse response = JsonUtils.jsonToObject(content, AlkymerResponse.class);
        assertThat(response.getId()).isEqualTo(1);
        assertThat(response.getName()).isEqualTo("foo");
    }

    @Test
    @Order(3)
    @WithUserDetails("fbar@somosmas.org")
    void getAlkymers_shouldReturn200() throws Exception {

        String content = mockMvc.perform(get(ApiConstants.ALKYMERS_URI))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).isNotBlank();

        AlkymerResponseList response = JsonUtils.jsonToObject(content, AlkymerResponseList.class);

        assertThat(response.getTotalElements()).isEqualTo(1);
        assertThat(response.getTotalPages()).isEqualTo(1);
        assertThat(response.getNextUri()).isEqualTo("http://localhost/v1/alkymers?page=1");
        assertThat(response.getPreviousUri()).isEqualTo("http://localhost/v1/alkymers?page=0");
        assertThat(response.getContent()).isNotEmpty();
    }

    @Test
    @Order(4)
    @WithUserDetails("admin@somosmas.org")
    void updateAlkeymer_shouldReturn204() throws Exception {

        UpdateAlkymerRequest request = UpdateAlkymerRequest.builder()
                .endDate(Calendar.getInstance().getTime())
                .skills(List.of())
                .build();

        mockMvc.perform(patch(ApiConstants.ALKYMERS_URI + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @Order(5)
    @WithUserDetails("admin@somosmas.org")
    void deleteAlkeymer_shouldReturn204() throws Exception {

        mockMvc.perform(delete(ApiConstants.ALKYMERS_URI + "/1"))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    @Order(6)
    @WithUserDetails("fbar@somosmas.org")
    void getAlkymer_shouldReturn404() throws Exception {
        mockMvc.perform(get(ApiConstants.ALKYMERS_URI + "/1"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @Order(7)
    @WithUserDetails("fbar@somosmas.org")
    void deleteAlkymer_shouldReturn403() throws Exception {
        mockMvc.perform(delete(ApiConstants.ALKYMERS_URI + "/1"))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @Order(8)
    @WithAnonymousUser
    void getAlkymer_shouldReturn401() throws Exception {
        mockMvc.perform(get(ApiConstants.ALKYMERS_URI + "/1"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }
}
