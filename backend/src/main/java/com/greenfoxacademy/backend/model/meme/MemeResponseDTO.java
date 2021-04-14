package com.greenfoxacademy.backend.model.meme;

import com.greenfoxacademy.backend.model.comment.CommentResponseDTO;
import com.greenfoxacademy.backend.model.reaction.ReactionResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemeResponseDTO {
  private Long id;
  private Timestamp timestamp;
  //TODO ReactionDTO
  private List<ReactionResponseDTO> metaData;
  private List<CommentResponseDTO> comment;
  private String url;
}
