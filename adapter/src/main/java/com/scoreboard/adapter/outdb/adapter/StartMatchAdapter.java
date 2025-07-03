package com.scoreboard.adapter.outdb.adapter;


import com.scoreboard.adapter.outdb.repository.ScoreboardRepository;
import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.out.StartMatchPort;

public class StartMatchAdapter implements StartMatchPort {

    private final ScoreboardRepository scoreboardRepository;

    public StartMatchAdapter(ScoreboardRepository scoreboardRepository) {
        this.scoreboardRepository = scoreboardRepository;
    }

    @Override
    public  FootballMatch apply(String matchData) {
        return null;
    }
}
