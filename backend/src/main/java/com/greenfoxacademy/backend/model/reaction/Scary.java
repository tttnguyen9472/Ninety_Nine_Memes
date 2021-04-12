package com.greenfoxacademy.backend.model.reaction;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Scary extends Reaction{

  public Scary(){super();}

  @Override
  public String getType() {
    return "Scary";
  }
}
