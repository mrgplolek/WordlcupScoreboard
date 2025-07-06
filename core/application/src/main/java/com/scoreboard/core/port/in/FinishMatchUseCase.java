package com.scoreboard.core.port.in;

@FunctionalInterface
public interface FinishMatchUseCase {

    boolean apply(String homeTeam, String awayTeam);
}
