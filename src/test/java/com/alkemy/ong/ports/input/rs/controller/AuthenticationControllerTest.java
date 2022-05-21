package com.alkemy.ong.ports.input.rs.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.alkemy.ong.common.exception.handler.GlobalExceptionHandler;
import com.alkemy.ong.common.security.services.AuthenticationService;
import com.alkemy.ong.common.util.JsonUtils;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.usecase.UserService;
import com.alkemy.ong.ports.input.rs.mapper.AuthenticationControllerMapper;
import com.alkemy.ong.ports.input.rs.mapper.UserControllerMapper;
import com.alkemy.ong.ports.input.rs.request.CreateUserRequest;
import com.alkemy.ong.ports.input.rs.response.RegisterResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks AuthenticationController controller;
	@Mock AuthenticationService authService;
	@Mock UserService userService;
	@Spy UserControllerMapper userMapper = Mappers.getMapper(UserControllerMapper.class);
	@Spy AuthenticationControllerMapper authMapper = Mappers.getMapper(AuthenticationControllerMapper.class);
	
	@BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
    }
	
	@Test
	void createUser_shouldReturn201() throws Exception {

		CreateUserRequest request = CreateUserRequest.builder().email("test@test.com").firstName("Test")
				.lastName("User").password("testuser1").build();
		
		User thisUser = authMapper.createUserRequestToUser(request);
		thisUser.setId(99L);
		
		given(userService.createUser(any(User.class))).willReturn(thisUser);
		given(authService.singIn(any(), any())).willReturn("jwt");
		
		final String content = mockMvc
				.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON)
						.content(JsonUtils.objectToJson(request)))
				.andExpect(status().isCreated()).andDo(print()).andReturn().getResponse().getContentAsString();

		assertThat(content).isNotBlank();

		RegisterResponse response = JsonUtils.jsonToObject(content, RegisterResponse.class);

		assertThat(response.getAuthResponse().getJwt()).isNotBlank();
		assertThat(response.getUserResponse().getEmail()).isEqualTo(request.getEmail());

	}

}
