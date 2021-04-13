package com.greenfoxacademy.backend.controller;

import com.greenfoxacademy.backend.exception.MissingParameterException;
import com.greenfoxacademy.backend.model.meme.Meme;
import com.greenfoxacademy.backend.model.meme.MemeDTO;
import com.greenfoxacademy.backend.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class MemeController {

  private MemeService memeService;

  @Autowired
  public MemeController(MemeService memeService) {
    this.memeService = memeService;
  }

  @GetMapping("/meme")
  public ResponseEntity<List<MemeDTO>> getMemes() {
    return ResponseEntity.ok(memeService.getAllMemes());
  }

  @PostMapping("/meme")
  public ResponseEntity<MemeDTO> postMeme(@RequestBody (required = false) MemeDTO memeDTO) throws MissingParameterException {
    return new ResponseEntity<MemeDTO>(memeService.postMeme(memeDTO), HttpStatus.CREATED);
  }
}
