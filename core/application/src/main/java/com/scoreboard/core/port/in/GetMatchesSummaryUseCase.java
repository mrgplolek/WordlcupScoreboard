package com.scoreboard.core.port.in;

import com.scoreboard.core.domain.FootballMatch;

import java.util.List;

public interface GetMatchesSummaryUseCase {

    List<FootballMatch> apply();
}
