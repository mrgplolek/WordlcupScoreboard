package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.mapper.FootballMatchMapper;
import com.scoreboard.adapter.outdb.repository.ScoreboardRepository;
import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.out.UpdateMatchPort;

public class UpdateMatchAdapter implements UpdateMatchPort {

    private static UpdateMatchAdapter instance;
    private final ScoreboardRepository scoreboardRepository = ScoreboardRepository.getInstance();

    private UpdateMatchAdapter() {}

    public static UpdateMatchAdapter getInstance() {
        if (instance == null) {
            instance = new UpdateMatchAdapter();
        }
        return instance;
    }

    @Override
    public FootballMatch apply(FootballMatch footballMatch) {
        return FootballMatchMapper.map(scoreboardRepository.updateScore(footballMatch));
    }
}
