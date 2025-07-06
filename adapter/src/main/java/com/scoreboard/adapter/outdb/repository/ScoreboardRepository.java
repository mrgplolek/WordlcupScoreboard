package com.scoreboard.adapter.outdb.repository;


import com.scoreboard.adapter.outdb.entity.FootballMatchEntity;
import com.scoreboard.core.domain.FootballMatch;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScoreboardRepository {

    private static ScoreboardRepository instance;

    InMemoryDatabaseMock databaseMock;

    private ScoreboardRepository() {
        databaseMock = new InMemoryDatabaseMock();
    }

    public static ScoreboardRepository getInstance() {
        if (instance == null) {
            instance = new ScoreboardRepository();
        }
        return instance;
    }

    public Optional<FootballMatchEntity> findRunningMatchByContestants(String homeTeam, String awayTeam) {
        return databaseMock.findRunningMatchByContestants(homeTeam, awayTeam);
    }

    public Optional<FootballMatchEntity> findRunningMatchByContestant(String contestant) {
        return databaseMock.findRunningMatchByContestant(contestant);
    }

    public FootballMatchEntity startNewMatch(String homeTeam, String awayTeam) {
        return databaseMock.startNewMatch(homeTeam, awayTeam);
    }

    public Optional<FootballMatchEntity> updateScore(FootballMatch match){
        return databaseMock.updateScore(match.getHomeTeam(), match.getAwayTeam(), match.getHomeTeamScore(), match.getAwayTeamScore());
    }

    public Boolean finishMatch(FootballMatch match){
        return databaseMock.finishMatch(match.getHomeTeam(), match.getAwayTeam());
    }

    public List<FootballMatchEntity> getSummary() {
        return databaseMock.getSummary();
    }

    // This class should only be used for testing and showcasing purposes therefore I will skip tests for this one
    public void setupTestData() {
        FootballMatchEntity footballMatchEntity1 = FootballMatchEntity.createNewMatchEntity("Spain", "Poland");
        footballMatchEntity1.setStartedAt(Instant.parse("2025-07-03T13:09:15Z"));
        FootballMatchEntity footballMatchEntity2 = FootballMatchEntity.createNewMatchEntity("Germany", "France");
        footballMatchEntity2.setHomeTeamScore(3);
        footballMatchEntity2.setHomeTeamLastScore(Instant.parse("2025-07-07T13:34:15Z"));
        footballMatchEntity2.setStartedAt(Instant.parse("2025-07-07T13:09:15Z"));
        FootballMatchEntity footballMatchEntity3 = FootballMatchEntity.createNewMatchEntity("Spain", "Poland");
        footballMatchEntity3.setStartedAt(Instant.parse("2025-07-01T13:09:15Z"));
        footballMatchEntity3.setFinishedAt(Instant.parse("2025-07-01T14:00:15Z"));
        FootballMatchEntity footballMatchEntity4 = FootballMatchEntity.createNewMatchEntity("England", "Portugal");
        footballMatchEntity4.setStartedAt(Instant.parse("2025-07-01T15:09:15Z"));
        footballMatchEntity4.setFinishedAt(Instant.parse("2025-07-01T16:00:15Z"));
        databaseMock.setDatabaseMock(new ArrayList<>(List.of(footballMatchEntity1, footballMatchEntity2, footballMatchEntity3, footballMatchEntity4)));
    }

    // This class should only be used for testing and showcasing purposes therefore I will skip tests for this one
    public  void cleanUpDb() {
        databaseMock.cleanUpDb();
    }

}
