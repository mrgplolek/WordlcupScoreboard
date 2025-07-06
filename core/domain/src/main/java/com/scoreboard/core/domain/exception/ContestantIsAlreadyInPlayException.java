package com.scoreboard.core.domain.exception;

public class ContestantIsAlreadyInPlayException extends RuntimeException {
    public ContestantIsAlreadyInPlayException(String message) {
        super(message);
    }
}
