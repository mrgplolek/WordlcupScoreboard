package com.scoreboard.core.port.out;

import com.scoreboard.core.domain.FootballMatch;

@FunctionalInterface
public interface FindRunningMatchByContestantsPort {

    FootballMatch apply(String homeTeam, String awayTeam);
}
