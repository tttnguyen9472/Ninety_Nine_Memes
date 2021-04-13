package com.greenfoxacademy.backend.exception;

public class InvalidMemeIdException extends MemeException {
  private Long memeId;

  public InvalidMemeIdException(Long memeId) {
    this.memeId = memeId;
  }

  public InvalidMemeIdException() {
  }

  public Long getMemeId() {
    return memeId;
  }
}
