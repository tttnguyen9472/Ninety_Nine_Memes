package com.greenfoxacademy.backend.service;

import com.greenfoxacademy.backend.exception.MissingParameterException;
import com.greenfoxacademy.backend.exception.ReservedUsernameException;
import com.greenfoxacademy.backend.exception.UserException;
import com.greenfoxacademy.backend.model.RegisterRequestDTO;
import com.greenfoxacademy.backend.model.RegisterResponseDTO;
import com.greenfoxacademy.backend.model.User;
import com.greenfoxacademy.backend.repository.UserRepository;
import com.greenfoxacademy.backend.security.PasswordSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
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
}
