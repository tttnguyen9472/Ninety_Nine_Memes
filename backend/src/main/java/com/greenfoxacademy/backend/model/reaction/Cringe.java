package com.greenfoxacademy.backend.model.reaction;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Cringe extends Reaction{

  public Cringe(){super();}

  @Override
  public String getType() {
    return "Cringe";
  }
}
