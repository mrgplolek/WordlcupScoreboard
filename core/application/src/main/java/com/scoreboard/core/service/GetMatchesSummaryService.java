package com.scoreboard.core.service;

import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.in.GetMatchesSummaryUseCase;
import com.scoreboard.core.port.out.GetMatchesSummaryPort;

import java.util.Comparator;
import java.util.List;

public class GetMatchesSummaryService implements GetMatchesSummaryUseCase {

    private static GetMatchesSummaryService instance;

    private final GetMatchesSummaryPort getMatchesSummaryPort;

    private GetMatchesSummaryService(GetMatchesSummaryPort getMatchesSummaryPort) {
        this.getMatchesSummaryPort = getMatchesSummaryPort;
    }

    public static GetMatchesSummaryService getInstance(GetMatchesSummaryPort getMatchesSummaryPort) {
        if (instance == null) {
            instance = new GetMatchesSummaryService(getMatchesSummaryPort);
        }
        return instance;
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
