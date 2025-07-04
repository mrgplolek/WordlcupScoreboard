package com.scoreboard.adapter.outdb.repository;


import com.scoreboard.adapter.outdb.entity.FootballMatchEntity;
import com.scoreboard.core.domain.FootballMatch;

import java.util.List;

public class ScoreboardRepository {

    InMemoryDatabaseMock databaseMock = new InMemoryDatabaseMock();

    public FootballMatchEntity findRunningMatchByContestants(String homeTeam, String awayTeam) {
        return databaseMock.findRunningMatchByContestants(homeTeam, awayTeam);
    }

    public FootballMatchEntity startNewMatch(String homeTeam, String awayTeam) {
        return databaseMock.startNewMatch(homeTeam, awayTeam);
    }

    public Boolean updateScore(FootballMatch match){
        return databaseMock.updateScore(match.getHomeTeam(), match.getAwayTeam(), match.getHomeTeamScore(), match.getAwayTeamScore());
    }

    public Boolean finishMatch(FootballMatch match){
        return databaseMock.finishMatch(match.getHomeTeam(), match.getAwayTeam());
    }

    public List<FootballMatchEntity> getSummary() {
        return databaseMock.getSummary();
    }

}
