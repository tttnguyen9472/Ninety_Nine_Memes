package com.greenfoxacademy.backend.service;

import com.greenfoxacademy.backend.exception.MissingParameterException;
import com.greenfoxacademy.backend.model.RegisterRequestDTO;
import com.greenfoxacademy.backend.model.meme.Meme;
import com.greenfoxacademy.backend.model.meme.MemeDTO;
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

  public MemeDTO postMeme(MemeDTO memeDTO) throws MissingParameterException {
    if(memeDTO == null) {
      throw new MissingParameterException(Arrays.asList("caption", "url"));
    }
    checkForMissingMemeParameters(memeDTO);
    Meme newMeme = new Meme(memeDTO.getUrl(), memeDTO.getCaption());
    memeRepository.save(newMeme);
    return new MemeDTO(newMeme.getUrl(), newMeme.getCaption());
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

  public List<MemeDTO> memeToDTO(List<Meme> memeList) {
    return memeList.stream()
            .map(m -> new MemeDTO(m.getCaption(), m.getUrl()))
            .collect(Collectors.toList());
  }

  public List<MemeDTO> getAllMemes() {
    return memeToDTO((List<Meme>) memeRepository.findAll());
  }
}
