package com.greenfoxacademy.backend.service;

import com.greenfoxacademy.backend.exception.InvalidMemeIdException;
import com.greenfoxacademy.backend.exception.MissingParameterException;
import com.greenfoxacademy.backend.model.comment.Comment;
import com.greenfoxacademy.backend.model.comment.CommentDTO;
import com.greenfoxacademy.backend.model.meme.Meme;
import com.greenfoxacademy.backend.model.meme.MemeDTO;
import com.greenfoxacademy.backend.model.meme.MemeResponseDTO;
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

  @Autowired
  public MemeService(MemeRepository memeRepository) {
    this.memeRepository = memeRepository;
  }

  public MemeDTO postMeme(User user, MemeDTO memeDTO) throws MissingParameterException {
    if (memeDTO == null) {
      throw new MissingParameterException(Arrays.asList("caption", "url"));
    }
    checkForMissingMemeParameters(memeDTO);
    Meme newMeme = new Meme(memeDTO.getUrl(), memeDTO.getCaption(), user);
    memeRepository.save(newMeme);
    return new MemeDTO(newMeme.getUrl(), newMeme.getCaption());
   // return new MemeResponseDTO(newMeme.getId(),newMeme.getTimestamp(),newMeme.getReaction(),commentToDTO(newMeme.getComment()),newMeme.getUrl());
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

//  public List<MemeDTO> memeToDTO(List<Meme> memeList) {
//    return memeList.stream()
//        .map(m -> new MemeDTO(m.getCaption(), m.getUrl(),commentToDTO(m.getComment())))
//        .collect(Collectors.toList());
//  }

//    private Long id;
//  private Timestamp timestamp;
//  //TODO ReactionDTO
//  private List<Reaction> reaction;
//  private List<CommentDTO> comment;
//  private String url;
  public List<MemeResponseDTO> memeToResponseDTO(List<Meme> memeList) {
    return memeList.stream()
        .map(m -> new MemeResponseDTO(m.getId(),m.getTimestamp(),m.getReaction(),commentToDTO(m.getComment()),m.getUrl()))
        .collect(Collectors.toList());
  }

  public List<CommentDTO> commentToDTO(List<Comment> commentList) {
    return commentList.stream()
        .map(c -> new CommentDTO(c.getComment()))
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

}