package com.yukon.backstage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yukon.backstage.controller.UserController;
import com.yukon.backstage.dto.UserRequest;
import com.yukon.backstage.dto.UserResponse;
import com.yukon.backstage.entity.UserEntity;
import com.yukon.backstage.enums.UserRole;
import com.yukon.backstage.mapper.UserToUserDtoMapper;
import com.yukon.backstage.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    //private HttpMessageConverter<Object> mappingJsonConverter;


    @Autowired
    private MockMvc mock;

    @MockBean
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    private UserToUserDtoMapper mapper = Mappers.getMapper(UserToUserDtoMapper.class);

    private List<UserRequest> userListRequest;
    private List<UserResponse> userListResponse;

    private List<UserEntity> userList;

    ObjectMapper Obj = new ObjectMapper();



    /*protected void setConverters(HttpMessageConverter<Object>[] converters) {
        mappingJsonConverter = Stream.of(converters)
                .filter(msgConv -> msgConv instanceof MappingJackson2HttpMessageConverter)
                .findAny().get();
    }

    protected String getJson(Object object) throws IOException{
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        mappingJsonConverter.write(object, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }*/

    @BeforeEach
    void setUp() {
        userListResponse = new ArrayList<>();
        userListResponse.add(new UserResponse("Qwerty", "Ihor", "Qwertiovych",
                "customer@gmail.com", "0973234532", UserRole.CUSTOMER, false, Instant.now(),Instant.now()));
        userListResponse.add(new UserResponse("Qwerty", "Ivan", "Qwertiovych",
                "admin@gmail.com",  "0973234532", UserRole.ADMIN, false, Instant.now(),Instant.now()));
        userListRequest = new ArrayList<>();
        userListRequest.add(new UserRequest("Qwerty", "Ihor", "Qwertiovych",
                "customer@gmail.com", "12344321", "0973234532", UserRole.CUSTOMER, false));
        userListRequest.add(new UserRequest("Qwerty", "Ivan", "Qwertiovych",
                "admin@gmail.com", "12344321", "0973234532", UserRole.ADMIN, false));
        userList = new ArrayList<>();
        userList.add(new UserEntity(1L,"Qwerty", "Ihor", "Qwertiovych",
                "customer@gmail.com", "12344321", "0973234532", UserRole.CUSTOMER, false, Instant.now(),Instant.now()));
        userList.add(new UserEntity(2L,"Qwerty", "Ivan", "Qwertiovych",
                "admin@gmail.com", "12344321", "0973234532", UserRole.ADMIN, false, Instant.now(),Instant.now()));


    }

    @Test
    void shouldFetchAllUsers() throws Exception{
        given(userService.getAll()).willReturn(userList);

        mock.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void shouldFetchOneUserById() throws Exception{
        Long userId = 1L;

        given(userService.getById(userId)).willReturn(userList.get(1));

        mock.perform(get("/api/user/{id}",userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(userList.get(1).getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(userList.get(1).getPhoneNumber()));
    }

    @Test
    void shouldCreateUser() throws Exception{
        given(userService.createUser(mapper.userDtoToUser(userListRequest.get(1)))).willReturn(userList.get(1));

        RequestBuilder request = MockMvcRequestBuilders.post("/api/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Obj.writeValueAsString(userListRequest.get(1)));
        MockHttpServletResponse result = mock.perform(request).andReturn().getResponse();

            assertEquals(HttpStatus.OK.value(), result.getStatus());

    }

}
