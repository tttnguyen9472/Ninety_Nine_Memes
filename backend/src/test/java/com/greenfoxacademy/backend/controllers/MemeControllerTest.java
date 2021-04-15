package com.greenfoxacademy.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfoxacademy.backend.controller.MemeController;
import com.greenfoxacademy.backend.controller.UserController;
import com.greenfoxacademy.backend.model.meme.MemeResponseDTO;
import com.greenfoxacademy.backend.service.CommentService;
import com.greenfoxacademy.backend.service.MemeService;
import com.greenfoxacademy.backend.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MemeController.class)
public class MemeControllerTest {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  @MockBean
  private MemeService mockMemeService;
  @Autowired
  @MockBean
  private UserService mockUserService;
  @Autowired
  @MockBean
  private CommentService mockCommentService;
  @Autowired
  ObjectMapper objectMapper;
  ModelMapper modelMapper;
  HttpHeaders httpHeaders;

  @Before
  public void createRequestHeader() {
    httpHeaders = new HttpHeaders();
    httpHeaders.add("Ninety-Nine-Gag-Token", "token.token.token");
  }

  @Test
  public void listAllMemes_HappyCase() throws Exception {
    List<MemeResponseDTO> mockMemeList = new ArrayList<>();
    mockMemeList.add(new MemeResponseDTO());
    when(mockMemeService.getAllMemes(any())).thenReturn(mockMemeList);

    mockMvc.perform(get("/meme")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("utf-8")
        .headers(httpHeaders))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));
  }

}
