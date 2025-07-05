package com.scoreboard.adapter.outdb.adapter;


import com.scoreboard.adapter.outdb.mapper.FootballMatchMapper;
import com.scoreboard.adapter.outdb.repository.ScoreboardRepository;
import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.out.StartMatchPort;

public class StartMatchAdapter implements StartMatchPort {

    private final ScoreboardRepository scoreboardRepository = ScoreboardRepository.getInstance();


    @Override
    public  FootballMatch apply(String homeTeam, String awayTeam) {
        return FootballMatchMapper.map(scoreboardRepository.startNewMatch(homeTeam, awayTeam));
    }
}
