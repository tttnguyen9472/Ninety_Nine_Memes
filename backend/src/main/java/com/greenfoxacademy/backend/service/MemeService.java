package com.greenfoxacademy.backend.service;

import com.greenfoxacademy.backend.exception.memeException.InvalidMemeIdException;
import com.greenfoxacademy.backend.exception.reactionException.InvalidReactionValueException;
import com.greenfoxacademy.backend.exception.memeException.MissingParameterException;
import com.greenfoxacademy.backend.exception.reactionException.NoSuchReactionException;
import com.greenfoxacademy.backend.model.comment.Comment;
import com.greenfoxacademy.backend.model.comment.CommentResponseDTO;
import com.greenfoxacademy.backend.model.meme.Meme;
import com.greenfoxacademy.backend.model.meme.MemeDTO;
import com.greenfoxacademy.backend.model.meme.MemeResponseDTO;
import com.greenfoxacademy.backend.model.reaction.Reaction;
import com.greenfoxacademy.backend.model.reaction.ReactionResponseDTO;
import com.greenfoxacademy.backend.model.user.User;
import com.greenfoxacademy.backend.repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemeService {

  private MemeRepository memeRepository;
  private ReactionService reactionService;

  @Autowired
  public MemeService(MemeRepository memeRepository, ReactionService reactionService) {
    this.memeRepository = memeRepository;
    this.reactionService = reactionService;
  }

  public MemeDTO postMeme(User user, MemeDTO memeDTO) throws MissingParameterException {
    if (memeDTO == null) {
      throw new MissingParameterException(Arrays.asList("caption", "url"));
    }
    checkForMissingMemeParameters(memeDTO);
    Meme newMeme = new Meme(memeDTO.getUrl(), memeDTO.getCaption(), user);
    reactionService.addReactionToNewMeme(newMeme);
    memeRepository.save(newMeme);
    return new MemeDTO(newMeme.getCaption(), newMeme.getUrl());
  }

  private void checkForMissingMemeParameters(MemeDTO memeDTO) throws MissingParameterException {
    List<String> missingParameterList = new ArrayList<>();


    checkIfNullOrEmptyField(memeDTO.getCaption(), "caption", missingParameterList);
    checkIfNullOrEmptyField(memeDTO.getUrl(), "url", missingParameterList);
    if (missingParameterList.size() > 0) {
      throw new MissingParameterException(missingParameterList);
    }
  }

  private void checkIfNullOrEmptyField(String inputField, String fieldName, List<String> missingParameterList) {
    if (inputField == null || inputField.equals("")) {
      missingParameterList.add(fieldName);
    }
  }

  public List<MemeResponseDTO> memeToResponseDTO(List<Meme> memeList) {
    return memeList.stream()
        .map(m -> new MemeResponseDTO(m.getId(), m.getTimestamp(), reactionToDTO(m.getMetaData()),
            commentToDTO(m.getComment()), m.getUrl()))
        .collect(Collectors.toList());
  }

  public List<CommentResponseDTO> commentToDTO(List<Comment> commentList) {
    return commentList.stream()
        .map(c -> new CommentResponseDTO(c.getComment()))
        .collect(Collectors.toList());
  }

  public List<MemeResponseDTO> getAllMemes(User user) {
    return memeToResponseDTO((List<Meme>) memeRepository.findAll());
  }

  public Meme findMemeById(Long id) throws InvalidMemeIdException {
    if (memeRepository.findById(id).isPresent()) {
      return memeRepository.findById(id).get();
    } else {
      throw new InvalidMemeIdException(id);
    }
  }

  public List<ReactionResponseDTO> reactionToDTO(List<Reaction> reactionList) {
    return reactionList.stream()
        .map(r -> new ReactionResponseDTO(r.getType(), r.getValue()))
        .collect(Collectors.toList());
  }

  public void postReaction(User user, String reaction, Long id, Integer value)
      throws InvalidMemeIdException, NoSuchReactionException, InvalidReactionValueException {
    if (value<0 || value>10){
      throw new InvalidReactionValueException();
    }
    Meme actualMeme = findMemeById(id);
    actualMeme.setMetaData(reactionService.increaseReaction(actualMeme.getMetaData(), reaction, value));
    memeRepository.save(actualMeme);
  }

}