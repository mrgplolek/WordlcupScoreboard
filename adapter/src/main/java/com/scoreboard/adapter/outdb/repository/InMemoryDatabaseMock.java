package com.scoreboard.adapter.outdb.repository;

import com.scoreboard.adapter.outdb.entity.FootballMatchEntity;
import com.scoreboard.core.domain.FootballMatch;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabaseMock {
    private List<FootballMatchEntity> databaseMock = new ArrayList<>();

    public FootballMatchEntity findMatchByContestants(String homeTeam, String awayTeam) {
        return null;
    }

    public FootballMatchEntity startNewMatch(String homeTeam, String awayTeam) {
        return null;
    }

    public FootballMatchEntity updateScore(FootballMatchEntity matchEntity){ return null; }
    public FootballMatchEntity finishMatch(FootballMatchEntity matchEntity){ return null; }
    public List<FootballMatchEntity> getSummary() { return null; }
    public void cleanUpDb() {}

    public void setDatabaseMock(List<FootballMatchEntity> databaseMock) {
        this.databaseMock = databaseMock;
    }
}
