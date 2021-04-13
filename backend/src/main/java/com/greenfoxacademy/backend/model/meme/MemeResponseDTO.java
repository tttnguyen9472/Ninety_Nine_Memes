package com.greenfoxacademy.backend.model.meme;

import com.greenfoxacademy.backend.model.comment.CommentDTO;
import com.greenfoxacademy.backend.model.reaction.Reaction;
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
  private List<Reaction> reaction;
  private List<CommentDTO> comment;
  private String url;
}
