package com.greenfoxacademy.backend.controller;

import com.greenfoxacademy.backend.exception.UserException;
import com.greenfoxacademy.backend.model.RegisterRequestDTO;
import com.greenfoxacademy.backend.model.RegisterResponseDTO;
import com.greenfoxacademy.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<RegisterResponseDTO> registerUser(
      @RequestBody(required = false) RegisterRequestDTO registrationData)
      throws UserException {
    return ResponseEntity.ok(userService.createUser(registrationData));
  }

}
