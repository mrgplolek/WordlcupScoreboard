package com.scoreboard.core.port.out;

import com.scoreboard.core.domain.FootballMatch;

import java.util.List;

@FunctionalInterface
public interface GetMatchesSummaryPort {

    List<FootballMatch> getMatchesSummary();
}
