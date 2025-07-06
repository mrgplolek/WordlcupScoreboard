package com.scoreboard.core.service;

import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.in.GetMatchesSummaryUseCase;
import com.scoreboard.core.port.out.GetMatchesSummaryPort;

import java.util.Comparator;
import java.util.List;

public class GetMatchesSummaryService implements GetMatchesSummaryUseCase {

    private final GetMatchesSummaryPort getMatchesSummaryPort;

    public GetMatchesSummaryService(GetMatchesSummaryPort getMatchesSummaryPort) {
        this.getMatchesSummaryPort = getMatchesSummaryPort;
    }

    @Override
    public List<FootballMatch> apply() {

        List<FootballMatch> allFootballMatches = getMatchesSummaryPort.apply();

        return allFootballMatches.stream()
                .filter(footballMatch -> footballMatch.getFinishedAt() == null)
                .sorted(
                        Comparator
                                .comparingInt((FootballMatch m) -> m.getHomeTeamScore() + m.getAwayTeamScore())
                                .reversed()
                                .thenComparing(FootballMatch::getStartedAt, Comparator.reverseOrder())
                )
                .toList();
    }
}
