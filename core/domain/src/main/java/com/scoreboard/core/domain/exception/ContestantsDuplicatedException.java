package com.scoreboard.core.domain.exception;

public class ContestantsDuplicatedException extends RuntimeException {
  public ContestantsDuplicatedException(String message) {
    super(message);
  }
}
