package com.greenfoxacademy.backend.service;

import com.greenfoxacademy.backend.exception.InvalidMemeIdException;
import com.greenfoxacademy.backend.exception.MissingParameterException;
import com.greenfoxacademy.backend.model.comment.Comment;
import com.greenfoxacademy.backend.model.comment.CommentDTO;
import com.greenfoxacademy.backend.model.meme.Meme;
import com.greenfoxacademy.backend.model.meme.MemeDTO;
import com.greenfoxacademy.backend.model.user.User;
import com.greenfoxacademy.backend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
  CommentRepository commentRepository;
  MemeService memeService;

  @Autowired
  public CommentService(CommentRepository commentRepository,MemeService memeService) {
    this.commentRepository = commentRepository;
    this.memeService = memeService;
  }

  public CommentDTO postComment(User user, CommentDTO commentDTO, Long memeId)
      throws MissingParameterException, InvalidMemeIdException {
    if(commentDTO == null || commentDTO.getComment()==null || commentDTO.getComment().equals("") ) {
      throw new MissingParameterException(Arrays.asList("comment"));
    }
    Meme actual = memeService.findMemeById(memeId);
   Comment newComment = new Comment(commentDTO.getComment(),user,actual);
    commentRepository.save(newComment);
    return new CommentDTO(newComment.getComment());
  }

}
