package com.greenfoxacademy.backend.service;

import com.greenfoxacademy.backend.repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemeService {

  private MemeRepository memeRepository;

  @Autowired
  public MemeService(MemeRepository memeRepository) {
    this.memeRepository = memeRepository;
  }
}
