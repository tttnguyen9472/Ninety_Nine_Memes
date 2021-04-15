package com.greenfoxacademy.backend.services;


import com.greenfoxacademy.backend.exception.memeException.MissingParameterException;
import com.greenfoxacademy.backend.exception.userException.NoSuchUserException;
import com.greenfoxacademy.backend.exception.userException.ReservedUsernameException;
import com.greenfoxacademy.backend.exception.userException.UserException;
import com.greenfoxacademy.backend.model.user.RegisterRequestDTO;
import com.greenfoxacademy.backend.model.user.RegisterResponseDTO;
import com.greenfoxacademy.backend.model.user.User;
import com.greenfoxacademy.backend.repository.UserRepository;
import com.greenfoxacademy.backend.security.JwtUtil;
import com.greenfoxacademy.backend.security.MyUserDetailsService;
import com.greenfoxacademy.backend.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

  private UserService userService;
  private UserRepository mockUserRepository;
  private JwtUtil jwtTokenUtil;
  private MyUserDetailsService userDetailsService;
  private AuthenticationManager mockAuthenticationManager;

  @Before
  public void setUp() {
    mockUserRepository = Mockito.mock(UserRepository.class);
    mockAuthenticationManager = Mockito.mock(AuthenticationManager.class);
    userDetailsService = Mockito.mock(MyUserDetailsService.class);
    jwtTokenUtil = Mockito.mock(JwtUtil.class);
    userService =
        new UserService(mockUserRepository,
            jwtTokenUtil,
            userDetailsService, mockAuthenticationManager);
  }

  @Test
  public void createUser_HappyCase()
      throws UserException {
    String username = "DummyUser";
    String password = "password";
    User mockUser = new User(username, password);
    mockUser.setId(1L);
    RegisterRequestDTO registrationData = new RegisterRequestDTO(username, password);

    when(mockUserRepository.findAllUsername()).thenReturn(Arrays.asList("TestUser", "TestUser1"));
    when(mockUserRepository.save(any(User.class))).thenReturn(mockUser);

    RegisterResponseDTO mockResponse = new RegisterResponseDTO(1L, username);
    RegisterResponseDTO response = userService.createUser(registrationData);
    assertEquals(mockResponse, response);
  }

  @Test(expected = ReservedUsernameException.class)
  public void createUser_DuplicateUsername_ReservedUsernameExceptionExpected()
      throws UserException {

    String username = "TestUser";
    String password = "password";
    RegisterRequestDTO registrationData = new RegisterRequestDTO(username, password);

    when(mockUserRepository.findAllUsername()).thenReturn(Arrays.asList("TestUser", "TestUser1"));

    userService.createUser(registrationData);
  }



  @Test(expected = MissingParameterException.class)
  public void createUser_MissingParameterExceptionExpected()
      throws UserException {
    String username = "DummyUser";
    RegisterRequestDTO registrationData = new RegisterRequestDTO(username);

    when(mockUserRepository.findAllUsername()).thenReturn(Arrays.asList("TestUser", "TestUser1"));

    userService.createUser(registrationData);
  }

  @Test(expected = MissingParameterException.class)
  public void createUser_EmptyStringAsParameter_MissingParameterExceptionExpected()
      throws UserException {
    String username = "DummyUser";
    RegisterRequestDTO registrationData = new RegisterRequestDTO(username);

    when(mockUserRepository.findAllUsername()).thenReturn(Arrays.asList("TestUser", "TestUser1"));

    userService.createUser(registrationData);
  }

  @Test(expected = MissingParameterException.class)
  public void createUser_whenNullRegisterData_MissingParameterExceptionExpected()
      throws UserException {
    RegisterRequestDTO registrationData = null;

    userService.createUser(registrationData);
  }

  @Test
  public void getUserByToken() throws NoSuchUserException {
    String mockToken = "asd.asd.asd";
    String mockUsername = "DummyUser1";
    User mockUser = new User(mockUsername);

    when(jwtTokenUtil.extractUsername(mockToken)).thenReturn(mockUsername);
    when(mockUserRepository.findUserByUsername(mockUsername)).thenReturn(Optional.of(mockUser));

    assertEquals(mockUsername, userService.getUserByToken(mockToken).getUsername());
  }

}
