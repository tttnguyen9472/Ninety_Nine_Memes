package com.greenfoxacademy.backend.model.reaction;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Funny extends Reaction {

  public Funny() {
    super();
  }

  @Override
  public String getType() {
    return "Funny";
  }
}
