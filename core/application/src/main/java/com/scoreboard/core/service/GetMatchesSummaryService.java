package com.scoreboard.core.service;

import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.in.GetMatchesSummaryUseCase;

import java.util.List;

public class GetMatchesSummaryService implements GetMatchesSummaryUseCase {

    @Override
    public List<FootballMatch> apply() {
        return List.of();
    }
}
