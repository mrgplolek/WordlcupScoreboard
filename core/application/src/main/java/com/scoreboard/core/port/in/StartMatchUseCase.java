package com.scoreboard.core.port.in;

@FunctionalInterface
public interface StartMatchUseCase {

    void apply(String matchData);
}
