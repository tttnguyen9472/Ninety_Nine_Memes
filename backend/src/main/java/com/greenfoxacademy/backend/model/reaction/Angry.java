package com.greenfoxacademy.backend.model.reaction;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Angry extends Reaction{

  public Angry(){super();}

  @Override
  public String getType() {
    return "Angry";
  }
}
