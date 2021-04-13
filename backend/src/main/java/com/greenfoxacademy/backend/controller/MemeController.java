package com.greenfoxacademy.backend.controller;

import com.greenfoxacademy.backend.exception.InvalidMemeIdException;
import com.greenfoxacademy.backend.exception.MissingParameterException;
import com.greenfoxacademy.backend.exception.NoSuchUserException;
import com.greenfoxacademy.backend.model.comment.CommentDTO;
import com.greenfoxacademy.backend.model.meme.MemeDTO;
import com.greenfoxacademy.backend.model.meme.MemeResponseDTO;
import com.greenfoxacademy.backend.service.CommentService;
import com.greenfoxacademy.backend.service.MemeService;
import com.greenfoxacademy.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemeController {

  private MemeService memeService;
  private UserService userService;
  private CommentService commentService;

  public MemeController(MemeService memeService, UserService userService, CommentService commentService) {
    this.memeService = memeService;
    this.userService = userService;
    this.commentService = commentService;
  }

  @GetMapping("/meme")
  public ResponseEntity<List<MemeResponseDTO>> getMemes(@RequestHeader(value = "Ninety-Nine-Gag-Token") String token)
      throws NoSuchUserException {
    return ResponseEntity.ok(memeService.getAllMemes(userService.getUserByToken(token)));
  }

  @PostMapping("/meme")
  public ResponseEntity<MemeDTO> postMeme(@RequestBody(required = false) MemeDTO memeDTO,
                                                      @RequestHeader(value = "Ninety-Nine-Gag-Token") String token)
      throws MissingParameterException, NoSuchUserException {
    return new ResponseEntity<>(memeService.postMeme(userService.getUserByToken(token), memeDTO),
        HttpStatus.CREATED);
  }

  @PostMapping("/comment")
  public ResponseEntity<CommentDTO> postComment(@RequestParam Long id, @RequestBody(required = false) CommentDTO commentDTO,
                                       @RequestHeader(value = "Ninety-Nine-Gag-Token", required = false) String token)
      throws NoSuchUserException, InvalidMemeIdException, MissingParameterException {
    return new ResponseEntity<>(commentService.postComment(userService.getUserByToken(token), commentDTO, id),
        HttpStatus.CREATED);
  }

}
