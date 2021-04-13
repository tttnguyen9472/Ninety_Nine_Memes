package com.greenfoxacademy.backend.exception.handler;


import com.greenfoxacademy.backend.exception.InvalidMemeIdException;
import com.greenfoxacademy.backend.exception.MissingParameterException;
import com.greenfoxacademy.backend.exception.ReservedUsernameException;
import com.greenfoxacademy.backend.model.meme.MemeErrorDTO;
import com.greenfoxacademy.backend.model.user.RegisterResponseDTO;
import com.greenfoxacademy.backend.model.user.UserLoginDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ReservedUsernameException.class)
  public ResponseEntity<RegisterResponseDTO> reservedUsernameExceptionHandling(ReservedUsernameException ex) {
    return new ResponseEntity<>(new RegisterResponseDTO("Username already taken, please choose an other one."),
        HttpStatus.CONFLICT);
  }

  @ExceptionHandler(MissingParameterException.class)
  public ResponseEntity<RegisterResponseDTO> missingParameterExceptionHandling(MissingParameterException ex) {
    String message = "Missing parameter(s): " + String.join(", ", ex.getMissingParameterList());
    return new ResponseEntity<>(new RegisterResponseDTO(message), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<UserLoginDTO> usernameNotFoundExceptionHandling() {
    return new ResponseEntity<>(new UserLoginDTO("No such user can be found!"), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<UserLoginDTO> badCredentialsExceptionHandling() {
    return new ResponseEntity<>(new UserLoginDTO("Wrong password!"), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(InvalidMemeIdException.class)
  public ResponseEntity<MemeErrorDTO> invalidMemeIdExceptionHandling(InvalidMemeIdException ex) {
        return new ResponseEntity<>(new MemeErrorDTO("The specified Meme ID is invalid."),
        HttpStatus.BAD_REQUEST);
  }

}