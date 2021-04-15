package com.greenfoxacademy.backend.exception.handler;


import com.greenfoxacademy.backend.exception.memeException.InvalidMemeIdException;
import com.greenfoxacademy.backend.exception.reactionException.InvalidReactionValueException;
import com.greenfoxacademy.backend.exception.memeException.MissingParameterException;
import com.greenfoxacademy.backend.exception.reactionException.NoSuchReactionException;
import com.greenfoxacademy.backend.exception.userException.ReservedUsernameException;
import com.greenfoxacademy.backend.model.ErrorDTO;
import com.greenfoxacademy.backend.model.meme.MemeErrorDTO;
import com.greenfoxacademy.backend.model.reaction.ReactionErrorDTO;
import com.greenfoxacademy.backend.model.user.RegisterResponseDTO;
import com.greenfoxacademy.backend.model.user.UserLoginDTO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(ReservedUsernameException.class)
  public ResponseEntity<RegisterResponseDTO> reservedUsernameExceptionHandling(ReservedUsernameException ex) {
    logger.warn("ReservedUsernameException: " + ex.getRequestedName());
    return new ResponseEntity<>(new RegisterResponseDTO("Username already taken, please choose an other one."),
        HttpStatus.CONFLICT);
  }

  @ExceptionHandler(MissingParameterException.class)
  public ResponseEntity<RegisterResponseDTO> missingParameterExceptionHandling(MissingParameterException ex) {
    logger.warn("MissingParameterException: " + ex.getMissingParameterList());
    String message = "Missing parameter(s): " + String.join(", ", ex.getMissingParameterList());
    return new ResponseEntity<>(new RegisterResponseDTO(message), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<UserLoginDTO> usernameNotFoundExceptionHandling() {
    logger.warn("UsernameNotFoundException");
    return new ResponseEntity<>(new UserLoginDTO("No such user can be found!"), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<UserLoginDTO> badCredentialsExceptionHandling() {
    logger.warn("BadCredentialsException - wrong password");
    return new ResponseEntity<>(new UserLoginDTO("Wrong password!"), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(InvalidMemeIdException.class)
  public ResponseEntity<MemeErrorDTO> invalidMemeIdExceptionHandling(InvalidMemeIdException ex) {
    logger.warn("InvalidMemeIdException - the specified Meme ID is invalid");
        return new ResponseEntity<>(new MemeErrorDTO("The specified Meme ID is invalid."),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoSuchReactionException.class)
  public ResponseEntity<ReactionErrorDTO> noSuchReactionExceptionHandling(NoSuchReactionException ex) {
    logger.warn("NoSuchReactionException - you can't react like that bro");
    return new ResponseEntity<>(new ReactionErrorDTO("You can't react like that bro."),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidReactionValueException.class)
  public ResponseEntity<ReactionErrorDTO> invalidReactionValueExceptionHandling(InvalidReactionValueException ex) {
    logger.warn("InvalidReactionValueException - you can't react like that bro");
    return new ResponseEntity<>(new ReactionErrorDTO("You can't react like that bro."),
        HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorDTO> httpMessageNotReadableExceptionHandling(HttpMessageNotReadableException ex) {
    logger.warn("HttpMessageNotReadableException - the message is not readable");
    return new ResponseEntity<>(new ErrorDTO("The message is not readable."),
        HttpStatus.BAD_REQUEST);
  }

}