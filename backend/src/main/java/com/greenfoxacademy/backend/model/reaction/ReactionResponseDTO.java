package com.greenfoxacademy.backend.model.reaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReactionResponseDTO {

  private String type;
  private Integer value;
}
