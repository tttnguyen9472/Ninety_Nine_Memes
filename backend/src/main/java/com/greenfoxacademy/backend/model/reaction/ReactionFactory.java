package com.greenfoxacademy.backend.model.reaction;

import org.springframework.stereotype.Service;

@Service
public class ReactionFactory {

  public Reaction createReaction(ReactionType reactionType) {
    if (reactionType.equals(ReactionType.ANGRY)) {
      return new Angry(0);
    } else if (reactionType.equals(ReactionType.CRINGE)) {
      return new Cringe(0);
    } else if (reactionType.equals(ReactionType.FUNNY)) {
      return new Funny(0);
    } else if (reactionType.equals(ReactionType.HORNY)) {
      return new Horny(0);
    } else if (reactionType.equals(ReactionType.SAD)) {
      return new Sad(0);
    } else if (reactionType.equals(ReactionType.SCARY)) {
      return new Scary(0);
    }
    return null;
  }
}