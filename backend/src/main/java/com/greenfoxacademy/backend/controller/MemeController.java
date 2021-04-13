package com.greenfoxacademy.backend.controller;

import com.greenfoxacademy.backend.exception.MissingParameterException;
import com.greenfoxacademy.backend.exception.NoSuchUserException;
import com.greenfoxacademy.backend.model.meme.MemeDTO;
import com.greenfoxacademy.backend.service.MemeService;
import com.greenfoxacademy.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Controller
public class MemeController {

  private MemeService memeService;
  private UserService userService;

  public MemeController(MemeService memeService, UserService userService) {
    this.memeService = memeService;
    this.userService = userService;
  }

  @GetMapping("/meme")
  public ResponseEntity<List<MemeDTO>> getMemes(@RequestHeader(value = "Ninety-Nine-Gag-Token") String token)
      throws NoSuchUserException {
    return ResponseEntity.ok(memeService.getAllMemes(userService.getUserByToken(token)));
  }

  @PostMapping("/meme")
  public ResponseEntity<MemeDTO> postMeme(@RequestBody(required = false) MemeDTO memeDTO,
                                          @RequestHeader(value = "Ninety-Nine-Gag-Token") String token)
      throws MissingParameterException, NoSuchUserException {
    return new ResponseEntity<MemeDTO>(memeService.postMeme(userService.getUserByToken(token), memeDTO), HttpStatus.CREATED);
  }
}
