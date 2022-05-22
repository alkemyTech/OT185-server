package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.common.exception.handler.GlobalExceptionHandler;
import com.alkemy.ong.common.security.services.AuthenticationService;
import com.alkemy.ong.common.util.JsonUtils;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.usecase.UserService;
import com.alkemy.ong.ports.input.rs.mapper.AuthenticationControllerMapper;
import com.alkemy.ong.ports.input.rs.request.AuthenticationRequest;
import com.alkemy.ong.ports.input.rs.request.CreateUserRequest;
import com.alkemy.ong.ports.input.rs.response.AuthenticationResponse;
import com.alkemy.ong.ports.input.rs.response.RegisterResponse;
import com.alkemy.ong.ports.input.rs.response.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

    private MockMvc mockMvc;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @InjectMocks
    AuthenticationController controller;
    @Mock
    AuthenticationService authService;
    @Mock
    UserService userService;
    @Spy
    AuthenticationControllerMapper mapper = Mappers.getMapper(AuthenticationControllerMapper.class);

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
    }

    @Test
    void createUser_shouldReturn201() throws Exception {

		String username = "test@test.com";
		String password = "testuser1";

		CreateUserRequest request = CreateUserRequest.builder()
				.email(username)
				.firstName("Test")
                .lastName("User")
				.password(password)
				.build();

		User user = new User();
		user.setEmail(username);

		given(userService.createUser(any(User.class))).willReturn(user);
		given(authService.singIn(eq(username),eq(password))).willReturn("token");

        final String content = mockMvc
                .perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
				.andExpect(status().isCreated())
				.andDo(print()).andReturn()
				.getResponse()
				.getContentAsString();

        assertThat(content).isNotBlank();

        RegisterResponse response = OBJECT_MAPPER.readValue(content, RegisterResponse.class);

        assertThat(response.getAuthResponse().getJwt()).isNotBlank();
        assertThat(response.getUserResponse().getEmail()).isEqualTo(request.getEmail());

    }
    
    @Test
    void userLogin_shouldReturn200() throws Exception {
    	
    	String email = "test@test.com";
    	String password = "testuser1";
    	
    	AuthenticationRequest request = AuthenticationRequest.builder()
    			.email(email)
    			.password(password)
    			.build();
    	
    	given(authService.singIn(eq(email),eq(password))).willReturn("token");
    	
    	final String content = mockMvc
    			.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON)
    					.content(JsonUtils.objectToJson(request)))
    			.andExpect(status().isOk())
    			.andDo(print())
    			.andReturn()
    			.getResponse()
    			.getContentAsString();
    	
    	assertThat(content).isNotBlank();
    	
    	AuthenticationResponse response = OBJECT_MAPPER.readValue(content, AuthenticationResponse.class);
    	
    	assertThat(response.getJwt()).isEqualTo("token");
    }
    
    @Test
    void meData_shouldReturn200() throws Exception {
    	
    	UserResponse user = new UserResponse();
    	user.setId(99L);
    	user.setFirstName("Test");
    	user.setLastName("User");
    	user.setEmail("test@test.com");
    	user.setPhoto("image.com");
    	
    	given(mapper.userToUserResponse(any(User.class))).willReturn(user);
    	
    	final String content = mockMvc
    			.perform(get("/auth/me"))
    			.andExpect(status().isOk())
    			.andDo(print())
    			.andReturn()
    			.getResponse()
    			.getContentAsString();
    	
    	assertThat(content).isNotBlank();
    	
    	UserResponse response = OBJECT_MAPPER.readValue(content, UserResponse.class);
    	
    	assertThat(response.getEmail()).isEqualTo(user.getEmail());
    	assertThat(response.getId()).isEqualTo(user.getId());
    }

}
