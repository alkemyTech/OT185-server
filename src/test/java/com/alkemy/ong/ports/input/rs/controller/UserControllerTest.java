package com.alkemy.ong.ports.input.rs.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.alkemy.ong.common.exception.handler.GlobalExceptionHandler;
import com.alkemy.ong.common.util.JsonUtils;
import com.alkemy.ong.domain.model.Role;
import com.alkemy.ong.domain.model.User;
import com.alkemy.ong.domain.model.UserList;
import com.alkemy.ong.domain.usecase.UserService;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.mapper.UserControllerMapper;
import com.alkemy.ong.ports.input.rs.request.UpdateUserRequest;
import com.alkemy.ong.ports.input.rs.response.UserResponseList;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
	
	private MockMvc mockMvc;
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	@InjectMocks UserController controller;
	@Mock UserService service;
	@Spy UserControllerMapper mapper = Mappers.getMapper(UserControllerMapper.class);
	
	@BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
    }
	
	@Test
	void updateUser_shouldReturn204() throws Exception {
		
		UpdateUserRequest request = UpdateUserRequest.builder()
				.firstName("Tested")
				.lastName("User")
				.password("newpassword")
				.photo("image")
				.build();
		
		willDoNothing().given(service).updateEntityIfExists(eq(99L), any(User.class));
		
		mockMvc.perform(patch(ApiConstants.USER_URI + "/99")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtils.objectToJson(request)))
		.andExpect(status().isNoContent())
		.andDo(print());
	}
	
	@Test
	void getUsers_shouldReturn200() throws Exception {
		
		Role role = new Role();
		role.setId(1L);
		role.setName("ROLE_ADMIN");
		role.setDescription("Admin role");
		
		User user = new User();
		user.setId(99L);
		user.setFirstName("Testing");
		user.setLastName("User");
		user.setEmail("test@test.com");
		user.setPassword("testuserpassword");
		user.setRole(role);
		
		UserList list = new UserList(List.of(user), Pageable.ofSize(1), 1);
		given(service.getList(any(PageRequest.class))).willReturn(list);
		
		String content = mockMvc.perform(get(ApiConstants.USER_URI+"?page=0&size=1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).isNotBlank();
        
        UserResponseList response = OBJECT_MAPPER.readValue(content, UserResponseList.class);
        
        assertThat(response.getTotalElements()).isEqualTo(1);
        assertThat(response.getTotalPages()).isEqualTo(1);
        assertThat(response.getNextUri()).isEqualTo("http://localhost/v1/users?size=1&page=1");
        assertThat(response.getPreviousUri()).isEqualTo("http://localhost/v1/users?size=1&page=0");
        assertThat(response.getContent()).isNotEmpty();
	}
	
	@Test
	void deleteUserById_shouldReturn204() throws Exception {
		mockMvc.perform(delete(ApiConstants.USER_URI + "/99"))
        .andExpect(status().isNoContent())
        .andDo(print())
        .andReturn()
        .getResponse()
        .getContentAsString();
	}
	

}
