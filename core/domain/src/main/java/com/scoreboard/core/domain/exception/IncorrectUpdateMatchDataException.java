package com.scoreboard.core.domain.exception;

public class IncorrectUpdateMatchDataException extends RuntimeException {
    public IncorrectUpdateMatchDataException(String message) {
        super(message);
    }
}
