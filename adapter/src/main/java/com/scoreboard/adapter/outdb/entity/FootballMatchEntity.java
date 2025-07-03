package com.scoreboard.adapter.outdb.entity;

import java.time.Instant;

public class FootballMatchEntity {

    public String homeTeam;
    public String awayTeam;
    public int homeTeamScore;
    public int awayTeamScore;
    public Instant createdAt;
    public Instant lastUpdatedAt;
    public Instant homeTeamLastScore;
    public Instant awayTeamLastScore;
}
