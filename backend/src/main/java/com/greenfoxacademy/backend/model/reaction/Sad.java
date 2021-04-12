package com.greenfoxacademy.backend.model.reaction;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Sad extends Reaction{

  public Sad(){super();}

  @Override
  public String getType() {
    return "Sad";
  }
}
