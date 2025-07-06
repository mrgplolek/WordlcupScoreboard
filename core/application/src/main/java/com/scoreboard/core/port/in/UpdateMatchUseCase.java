package com.scoreboard.core.port.in;


import com.scoreboard.core.domain.FootballMatch;

@FunctionalInterface
public interface UpdateMatchUseCase {
    FootballMatch apply(FootballMatch match);
}
