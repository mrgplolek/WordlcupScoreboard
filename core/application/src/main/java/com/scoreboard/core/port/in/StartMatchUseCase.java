package com.scoreboard.core.port.in;

import com.scoreboard.core.domain.FootballMatch;

@FunctionalInterface
public interface StartMatchUseCase {

    FootballMatch apply(String homeTeam, String awayTeam);
}
