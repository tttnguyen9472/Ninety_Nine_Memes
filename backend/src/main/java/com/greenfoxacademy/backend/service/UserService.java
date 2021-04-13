package com.greenfoxacademy.backend.service;

import com.greenfoxacademy.backend.exception.MissingParameterException;
import com.greenfoxacademy.backend.exception.NoSuchUserException;
import com.greenfoxacademy.backend.exception.ReservedUsernameException;
import com.greenfoxacademy.backend.exception.UserException;
import com.greenfoxacademy.backend.model.user.RegisterRequestDTO;
import com.greenfoxacademy.backend.model.user.RegisterResponseDTO;
import com.greenfoxacademy.backend.model.user.User;
import com.greenfoxacademy.backend.model.user.UserLoginDTO;
import com.greenfoxacademy.backend.repository.UserRepository;
import com.greenfoxacademy.backend.security.AuthenticationRequest;
import com.greenfoxacademy.backend.security.AuthenticationResponse;
import com.greenfoxacademy.backend.security.JwtUtil;
import com.greenfoxacademy.backend.security.MyUserDetailsService;
import com.greenfoxacademy.backend.security.PasswordSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

  private final UserRepository userRepository;
  private JwtUtil jwtTokenUtil;
  private MyUserDetailsService userDetailsService;
  private AuthenticationManager authenticationManager;

  @Autowired
  public UserService(UserRepository userRepository, JwtUtil jwtTokenUtil,
                     MyUserDetailsService myUserDetailsService,
                     AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.jwtTokenUtil = jwtTokenUtil;
    this.userDetailsService = myUserDetailsService;
    this.authenticationManager = authenticationManager;
  }

  public RegisterResponseDTO createUser(RegisterRequestDTO registrationData)
      throws UserException {
    if (registrationData == null) {
      throw new MissingParameterException(Arrays.asList("username", "password"));
    }
    checkForMissingRegisterParameters(registrationData);
    if (isUsernameOccupied(registrationData.getUsername())) {
      throw new ReservedUsernameException(registrationData.getUsername());
    }
    User user = saveUser(
        new User(registrationData.getUsername()));
    saveEncodedPassword(user, registrationData.getPassword());

    return new RegisterResponseDTO(user.getId(),user.getUsername());
  }

  public User saveUser(User user) {

    return userRepository.save(user);
  }

  private void checkForMissingRegisterParameters(RegisterRequestDTO registerData) throws MissingParameterException {
    List<String> missingParameterList = new ArrayList<>();
    checkIfNullOrEmptyField(registerData.getUsername(), "username", missingParameterList);
    checkIfNullOrEmptyField(registerData.getPassword(), "password", missingParameterList);
     if (missingParameterList.size() > 0) {
      throw new MissingParameterException(missingParameterList);
    }
  }

  private void checkIfNullOrEmptyField(String inputField, String fieldName, List<String> missingParameterList) {
    if (inputField == null || inputField.equals("")) {
      missingParameterList.add(fieldName);
    }
  }

  private boolean isUsernameOccupied(String username) {
    return userRepository.findAllUsername().contains(username);
  }


  private void saveEncodedPassword(User user, String password) {
    PasswordSecurity passwordSecurity = PasswordSecurity.getInstance();
    user.setPassword(passwordSecurity.encode(password));
    userRepository.save(user);
  }

  public UserLoginDTO loginUser(AuthenticationRequest loginRequest) throws UserException {
    if (loginRequest == null || isUsernameMissing(loginRequest) && isPasswordMissing(loginRequest)) {
      throw new MissingParameterException(Arrays.asList("username", "password"));
    }
    checkForMissingLoginParameters(loginRequest);
    final UserDetails userDetails;
    userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
            loginRequest.getPassword()));
    final String jwt = jwtTokenUtil.generateToken(userDetails);
    return new UserLoginDTO(new AuthenticationResponse(jwt));
  }

  public Boolean isUsernameMissing(AuthenticationRequest loginRequest) {
    return (loginRequest.getUsername() == null || loginRequest.getUsername().equals(""));
  }

  public Boolean isPasswordMissing(AuthenticationRequest loginRequest) {
    return (loginRequest.getPassword() == null || loginRequest.getPassword().equals(""));
  }

  private void checkForMissingLoginParameters(AuthenticationRequest loginData) throws MissingParameterException {
    List<String> missingParameterList = new ArrayList<>();
    checkIfNullOrEmptyField(loginData.getUsername(), "username", missingParameterList);
    checkIfNullOrEmptyField(loginData.getPassword(), "password", missingParameterList);
    if (missingParameterList.size() > 0) {
      throw new MissingParameterException(missingParameterList);
    }
  }

  public User getUserByToken(String token) throws NoSuchUserException {
    String username = jwtTokenUtil.extractUsername(token);
    return userRepository.findUserByUsername(username).orElseThrow(NoSuchUserException::new);
  }
}
