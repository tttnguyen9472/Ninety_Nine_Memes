package com.greenfoxacademy.backend.controller;

import com.greenfoxacademy.backend.exception.InvalidMemeIdException;
import com.greenfoxacademy.backend.exception.MissingParameterException;
import com.greenfoxacademy.backend.exception.NoSuchReactionException;
import com.greenfoxacademy.backend.exception.NoSuchUserException;
import com.greenfoxacademy.backend.model.comment.CommentResponseDTO;
import com.greenfoxacademy.backend.model.comment.CommentRequestDTO;
import com.greenfoxacademy.backend.model.meme.MemeDTO;
import com.greenfoxacademy.backend.model.meme.MemeResponseDTO;
import com.greenfoxacademy.backend.model.reaction.ReactionRequestDTO;
import com.greenfoxacademy.backend.service.CommentService;
import com.greenfoxacademy.backend.service.MemeService;
import com.greenfoxacademy.backend.service.ReactionService;
import com.greenfoxacademy.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemeController {

  private MemeService memeService;
  private UserService userService;
  private CommentService commentService;
  private ReactionService reactionService;

  @Autowired
  public MemeController(MemeService memeService, UserService userService,
                        CommentService commentService,
                        ReactionService reactionService) {
    this.memeService = memeService;
    this.userService = userService;
    this.commentService = commentService;
    this.reactionService = reactionService;
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
  public ResponseEntity<CommentResponseDTO> postComment(@RequestBody(required = false) CommentRequestDTO commentRequestDTO,
                                                        @RequestHeader(value = "Ninety-Nine-Gag-Token", required = false)
                                                    String token)
      throws NoSuchUserException, InvalidMemeIdException, MissingParameterException {
    return new ResponseEntity<>(commentService.postComment(userService.getUserByToken(token), commentRequestDTO.getComment(),
        commentRequestDTO.getId()),
        HttpStatus.CREATED);
  }

  @PutMapping("/reaction")
  public ResponseEntity postReaction(@RequestBody ReactionRequestDTO reactionRequestDTO,
                                     @RequestHeader(value = "Ninety-Nine-Gag-Token", required = false)
                                                      String token)
      throws NoSuchUserException, InvalidMemeIdException, NoSuchReactionException {
    memeService.postReaction(userService.getUserByToken(token), reactionRequestDTO.getType(), reactionRequestDTO.getId(),
        reactionRequestDTO.getValue());
    return new ResponseEntity(HttpStatus.OK);

  }

}
