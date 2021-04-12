package com.greenfoxacademy.backend.controller;

import com.greenfoxacademy.backend.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemeController {

  private MemeService memeService;

  @Autowired
  public MemeController(MemeService memeService) {
    this.memeService = memeService;
  }
}
