package com.greenfoxacademy.backend.service;

import com.greenfoxacademy.backend.exception.memeException.InvalidMemeIdException;
import com.greenfoxacademy.backend.exception.memeException.MissingParameterException;
import com.greenfoxacademy.backend.model.comment.Comment;
import com.greenfoxacademy.backend.model.comment.CommentResponseDTO;
import com.greenfoxacademy.backend.model.meme.Meme;
import com.greenfoxacademy.backend.model.user.User;
import com.greenfoxacademy.backend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CommentService {
  CommentRepository commentRepository;
  MemeService memeService;

  @Autowired
  public CommentService(CommentRepository commentRepository,MemeService memeService) {
    this.commentRepository = commentRepository;
    this.memeService = memeService;
  }

  public CommentResponseDTO postComment(User user, String comment, Long memeId)
      throws MissingParameterException, InvalidMemeIdException {
    if(comment == null ||  comment.equals("") ) {
      throw new MissingParameterException(Arrays.asList("comment"));
    }
    Meme actual = memeService.findMemeById(memeId);
   Comment newComment = new Comment(comment,user,actual);
    commentRepository.save(newComment);
    return new CommentResponseDTO(newComment.getComment());
  }

}
