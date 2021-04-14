package com.greenfoxacademy.backend.exception.memeException;

import com.greenfoxacademy.backend.exception.userException.UserException;

import java.util.List;

public class MissingParameterException extends UserException {
  private final List<String> missingParameterList;

  public MissingParameterException(List<String> missingParameterList) {
    this.missingParameterList = missingParameterList;
  }

  public List<String> getMissingParameterList() {
    return missingParameterList;
  }
}
