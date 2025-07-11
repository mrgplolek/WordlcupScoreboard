package com.scoreboard.adapter.outdb.repository;

import com.scoreboard.adapter.outdb.entity.FootballMatchEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryDatabaseMock {
    private List<FootballMatchEntity> databaseMock = new ArrayList<>();

    public Optional<FootballMatchEntity> findRunningMatchByContestants(String homeTeam, String awayTeam) {
        return databaseMock.stream()
                .filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam) && match.getFinishedAt() == null)
                .findFirst();
    }

    public Optional<FootballMatchEntity> findRunningMatchByContestant(String contestant) {
        return databaseMock.stream()
                .filter(match -> (match.getHomeTeam().equals(contestant) || match.getAwayTeam().equals(contestant)) && match.getFinishedAt() == null)
                .findFirst();
    }

    public FootballMatchEntity startNewMatch(String homeTeam, String awayTeam) {
        FootballMatchEntity newMatch = FootballMatchEntity.createNewMatchEntity(homeTeam, awayTeam);
        databaseMock.add(newMatch);
        return newMatch;
    }

    public Optional<FootballMatchEntity> updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore){
        return databaseMock.stream()
                .filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                .map(matchEntity -> updateEntityWithNewScore(matchEntity, homeTeamScore, awayTeamScore))
                .findFirst();
    }

    public Boolean finishMatch(String homeTeam, String awayTeam){
        return databaseMock.stream()
                .filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam) && match.getFinishedAt() == null)
                .map(this::updateEndedMatch)
                .findFirst().isPresent();
    }

    public List<FootballMatchEntity> getSummary() { return databaseMock; }

    public void cleanUpDb() {
        this.databaseMock = new ArrayList<>();
    }

    void setDatabaseMock(List<FootballMatchEntity> databaseMock) {
        this.databaseMock = databaseMock;
    }

    private FootballMatchEntity updateEntityWithNewScore(FootballMatchEntity matchToBeUpdated, int homeTeamScore, int awayTeamScore){
        if (matchToBeUpdated.getHomeTeamScore() != homeTeamScore){
            matchToBeUpdated.setHomeTeamLastScore(Instant.now());
        }
        if (matchToBeUpdated.getAwayTeamScore() != awayTeamScore){
            matchToBeUpdated.setAwayTeamLastScore(Instant.now());
        }
        matchToBeUpdated.setAwayTeamScore(awayTeamScore);
        matchToBeUpdated.setHomeTeamScore(homeTeamScore);
        return matchToBeUpdated;
    }
    private FootballMatchEntity updateEndedMatch(FootballMatchEntity matchToBeFinished) {
        matchToBeFinished.setFinishedAt(Instant.now());
        return matchToBeFinished;
    }
}
