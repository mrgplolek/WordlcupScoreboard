package com.scoreboard.adapter.inweb;


import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.in.FinishMatchUseCase;
import com.scoreboard.core.port.in.GetMatchesSummaryUseCase;
import com.scoreboard.core.port.in.StartMatchUseCase;
import com.scoreboard.core.port.in.UpdateMatchUseCase;

import java.util.List;

public class ScoreboardController {

    private StartMatchUseCase startMatchUseCase;
    private FinishMatchUseCase finishMatchUseCase;
    private UpdateMatchUseCase updateMatchUseCase;
    private GetMatchesSummaryUseCase getMatchesSummaryUseCase;


    public void startMatch (String homeTeam, String awayTeam) {
        startMatchUseCase.apply(homeTeam, awayTeam);
    }

    public void finishMatch (String homeTeam, String awayTeam) {
        finishMatchUseCase.apply(homeTeam, awayTeam);
    }

    public void updateMatch (String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
        FootballMatch match = new FootballMatch(homeTeam, awayTeam, homeTeamScore, awayTeamScore);
        updateMatchUseCase.apply(match);
    }

    public void getSummary() {
        List<FootballMatch> matches = getMatchesSummaryUseCase.apply();
        createSimpleSummaryUI(matches);
    }

    private void createSimpleSummaryUI(List<FootballMatch> matches) {
        int index = 1;
        for (FootballMatch match : matches) {
            System.out.printf("%d. %s %d - %s %d%n",
                    index++,
                    match.getHomeTeam(),
                    match.getHomeTeamScore(),
                    match.getAwayTeam(),
                    match.getAwayTeamScore()
            );
        }
    }
}
