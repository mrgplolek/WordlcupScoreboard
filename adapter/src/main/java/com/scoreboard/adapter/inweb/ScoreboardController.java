package com.scoreboard.adapter.inweb;


import com.scoreboard.adapter.outdb.adapter.FindRunningMatchByContestantAdapter;
import com.scoreboard.adapter.outdb.adapter.FindRunningMatchByContestantsAdapter;
import com.scoreboard.adapter.outdb.adapter.FinishMatchAdapter;
import com.scoreboard.adapter.outdb.adapter.GetMatchesSummaryAdapter;
import com.scoreboard.adapter.outdb.adapter.StartMatchAdapter;
import com.scoreboard.adapter.outdb.adapter.UpdateMatchAdapter;
import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.in.FinishMatchUseCase;
import com.scoreboard.core.port.in.GetMatchesSummaryUseCase;
import com.scoreboard.core.port.in.StartMatchUseCase;
import com.scoreboard.core.port.in.UpdateMatchUseCase;
import com.scoreboard.core.service.FinishMatchService;
import com.scoreboard.core.service.GetMatchesSummaryService;
import com.scoreboard.core.service.StartMatchService;
import com.scoreboard.core.service.UpdateMatchService;

import java.util.List;

public class ScoreboardController {

    private static ScoreboardController instance;

    private final StartMatchUseCase startMatchUseCase;
    private final FinishMatchUseCase finishMatchUseCase;
    private final UpdateMatchUseCase updateMatchUseCase;
    private final GetMatchesSummaryUseCase getMatchesSummaryUseCase;

    private ScoreboardController() {
        this.startMatchUseCase = StartMatchService.getInstance(StartMatchAdapter.getInstance(), FindRunningMatchByContestantAdapter.getInstance());
        this.finishMatchUseCase = FinishMatchService.getInstance(FinishMatchAdapter.getInstance(), FindRunningMatchByContestantsAdapter.getInstance());
        this.updateMatchUseCase = UpdateMatchService.getInstance(FindRunningMatchByContestantsAdapter.getInstance(), UpdateMatchAdapter.getInstance());
        this.getMatchesSummaryUseCase = GetMatchesSummaryService.getInstance(GetMatchesSummaryAdapter.getInstance());
    }

    public static ScoreboardController getInstance() {
        if (instance == null) {
            instance = new ScoreboardController();
        }
        return instance;
    }

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
        if (matches.size() < 1) {
            System.out.println("There are no running matches at the moment.");
        } else {
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
}
