package com.alkemy.ong.ports.input.rs.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import com.alkemy.ong.H2Config;
import com.alkemy.ong.common.util.JsonUtils;
import com.alkemy.ong.ports.input.rs.request.AuthenticationRequest;
import com.alkemy.ong.ports.input.rs.request.CreateUserRequest;
import com.alkemy.ong.ports.input.rs.response.AuthenticationResponse;
import com.alkemy.ong.ports.input.rs.response.RegisterResponse;
import com.alkemy.ong.ports.input.rs.response.UserResponse;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringJUnitConfig(classes = H2Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthenticationControllerIT {

	@Autowired
	MockMvc mockMvc;

	@Test
	@Order(1)
	void createUser_shouldReturn201() throws Exception {

		CreateUserRequest request = CreateUserRequest.builder().email("test@test.com").firstName("Test")
				.lastName("User").password("testuser1").build();

		final String content = mockMvc
				.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON)
						.content(JsonUtils.objectToJson(request)))
				.andExpect(status().isCreated()).andDo(print()).andReturn().getResponse().getContentAsString();

		assertThat(content).isNotBlank();

		RegisterResponse response = JsonUtils.jsonToObject(content, RegisterResponse.class);

		assertThat(response.getAuthResponse().getJwt()).isNotBlank();
		assertThat(response.getUserResponse().getEmail()).isEqualTo(request.getEmail());

	}

	@Test
	@Order(2)
	void userLogin_shouldReturn200() throws Exception {
		AuthenticationRequest request = AuthenticationRequest.builder().email("test@test.com").password("testuser1")
				.build();

		final String content = mockMvc
				.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON)
						.content(JsonUtils.objectToJson(request)))
				.andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();

		assertThat(content).isNotBlank();

		AuthenticationResponse response = JsonUtils.jsonToObject(content, AuthenticationResponse.class);

		assertThat(response.getJwt()).isNotBlank();
	}

	@Test
	@Order(3)
	@WithUserDetails("test@test.com")
	void meData_shouldReturn200() throws Exception {

		final String content = mockMvc.perform(get("/auth/me")).andExpect(status().isOk()).andDo(print()).andReturn()
				.getResponse().getContentAsString();

		assertThat(content).isNotBlank();

		UserResponse response = JsonUtils.jsonToObject(content, UserResponse.class);

		assertThat(response.getEmail()).isEqualTo("test@test.com");
		assertThat(response.getFirstName()).isEqualTo("Test");
	}
	
	@Test
	@Order(4)
	void meData_shouldReturn401() throws Exception {
		final int content = mockMvc.perform(get("/auth/me")).andExpect(status().isUnauthorized()).andDo(print()).andReturn()
				.getResponse().getStatus();

		assertThat(content).isEqualTo(401);

		
	}

}
