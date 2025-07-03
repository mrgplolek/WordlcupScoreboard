package com.scoreboard.adapter.outdb.entity;

import java.time.Instant;

public class FootballMatchEntity {

    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private Instant startedAt;
    private Instant finishedAt;
    private Instant homeTeamLastScore;
    private Instant awayTeamLastScore;

    public FootballMatchEntity (String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore, Instant startedAt,
                         Instant finishedAt, Instant homeTeamLastScore,  Instant awayTeamLastScore) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.homeTeamLastScore = homeTeamLastScore;
        this.awayTeamLastScore = awayTeamLastScore;
    }

    public static FootballMatchEntity createNewMatchEntity(String homeTeam, String awayTeam){
        return new FootballMatchEntity(homeTeam, awayTeam, 0, 0, Instant.now(), null, null, null);
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }

    public Instant getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Instant finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Instant getHomeTeamLastScore() {
        return homeTeamLastScore;
    }

    public void setHomeTeamLastScore(Instant homeTeamLastScore) {
        this.homeTeamLastScore = homeTeamLastScore;
    }

    public Instant getAwayTeamLastScore() {
        return awayTeamLastScore;
    }

    public void setAwayTeamLastScore(Instant awayTeamLastScore) {
        this.awayTeamLastScore = awayTeamLastScore;
    }
}
