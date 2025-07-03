package com.scoreboard.core.domain;

public class FootballMatch {

    public String homeTeam;
    public String awayTeam;
    public int homeTeamScore;
    public int awayTeamScore;

    public FootballMatch (String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }
}
