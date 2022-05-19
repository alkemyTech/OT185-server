package com.alkemy.ong.ports.input.rs.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.request.UpdateUserRequest;
import com.alkemy.ong.ports.input.rs.response.UserResponseList;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringJUnitConfig(classes = H2Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerIT {

	@Autowired
	MockMvc mockMvc;

	@Test
	@Order(1)
	@WithUserDetails("admin@somosmas.org")
	void updateUser_shouldReturn204() throws Exception {

		UpdateUserRequest request = UpdateUserRequest.builder().firstName("Tested").lastName("User")
				.password("newpassword").photo("image").build();

		mockMvc.perform(patch(ApiConstants.USER_URI + "/99").contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtils.objectToJson(request))).andExpect(status().isNoContent()).andDo(print()).andReturn()
				.getResponse().getContentAsString();

	}

	@Test
	@Order(2)
	@WithUserDetails("admin@somosmas.org")
	void getUsers_shouldReturn200() throws Exception {
		String content = mockMvc.perform(get(ApiConstants.USER_URI)).andExpect(status().isOk()).andDo(print())
				.andReturn().getResponse().getContentAsString();

		assertThat(content).isNotBlank();

		UserResponseList response = JsonUtils.jsonToObject(content, UserResponseList.class);

		assertThat(response.getTotalElements()).isEqualTo(3);
		assertThat(response.getTotalPages()).isEqualTo(1);
		assertThat(response.getNextUri()).isEqualTo("http://localhost/v1/users?page=1");
		assertThat(response.getPreviousUri()).isEqualTo("http://localhost/v1/users?page=0");
		assertThat(response.getContent()).isNotEmpty();
	}

	@Test
	@Order(3)
	@WithUserDetails("admin@somosmas.org")
	void deleteUserById_shouldReturn204() throws Exception {
		mockMvc.perform(delete(ApiConstants.USER_URI + "/99")).andExpect(status().isNoContent()).andDo(print());
	}

	@Test
	@Order(4)
	@WithUserDetails("fbar@somosmas.org")
	void deleteUserById_shouldReturn403() throws Exception {
		mockMvc.perform(delete(ApiConstants.USER_URI + "/99")).andExpect(status().isForbidden()).andDo(print());
	}

	@Test
	@Order(5)
	@WithUserDetails("fbar@somosmas.org")
	void getUsers_shouldReturn403() throws Exception {
		mockMvc.perform(get(ApiConstants.USER_URI)).andExpect(status().isForbidden()).andDo(print());
	}

}
