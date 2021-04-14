package com.greenfoxacademy.backend.model.reaction;

public enum ReactionType {
  ANGRY {
    public String toString() {
      return "Angry";
    }
  },
  CRINGE {
    public String toString() {
      return "Cringe";
    }
  },
  FUNNY {
    public String toString() {
      return "Funny";
    }
  },
  HORNY {
    public String toString() {
      return "Horny";
    }
  },
  SAD {
    public String toString() {
      return "Sad";
    }
  },
  SCARY {
    public String toString() {
      return "Scary";
    }
  }
}
