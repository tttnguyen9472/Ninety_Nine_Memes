package com.greenfoxacademy.backend.integrationtests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfoxacademy.backend.model.user.RegisterRequestDTO;
import com.greenfoxacademy.backend.model.user.User;
import com.greenfoxacademy.backend.repository.UserRepository;
import com.greenfoxacademy.backend.security.AuthenticationRequest;
import com.greenfoxacademy.backend.security.PasswordSecurity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  UserRepository userRepository;

  @Autowired
  ObjectMapper objectMapper;



  @Test
  public void givenRegisterURL_whenMockMVC_thenStatusOK_andReturnsWithUserResponseDTO() throws Exception {
    RegisterRequestDTO testRegistrationData = new RegisterRequestDTO(
        "RegisterDummyUser", "password");
    mockMvc.perform(post("/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(testRegistrationData)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("username", is("RegisterDummyUser")))
        .andDo(print());
  }

  @Test
  public void givenLoginURL_whenMockMVC_thenStatusOK_andReturnsWithUserLoginDTO() throws Exception {
    String encodedPassword = PasswordSecurity.getInstance().encode("LoginPassword");
    userRepository.save(new User("LoginDummyUser",
        encodedPassword));

    AuthenticationRequest validUser = new AuthenticationRequest("LoginDummyUser", "LoginPassword");

    mockMvc.perform(post("/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(validUser)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("status", is("ok")))
        .andDo(print());
  }

  @Test
  public void givenRegisterAndLoginURL_whenMockMVC_thenCorrectPassword() throws Exception {
    String username = "PasswordDummyUser";
    String password = "password";
    RegisterRequestDTO testRegistrationData = new RegisterRequestDTO(username, password);
    mockMvc.perform(post("/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(testRegistrationData)))
        .andDo(print());

    AuthenticationRequest validUser = new AuthenticationRequest(username, password);

    mockMvc.perform(post("/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(validUser)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("status", is("ok")))
        .andDo(print());
  }
}
