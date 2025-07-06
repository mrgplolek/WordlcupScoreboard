package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.mapper.FootballMatchMapper;
import com.scoreboard.adapter.outdb.repository.ScoreboardRepository;
import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.out.UpdateMatchPort;

import java.util.Optional;

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
    public Optional<FootballMatch> apply(FootballMatch footballMatch) {
        return scoreboardRepository.updateScore(footballMatch).map(FootballMatchMapper::map);
    }
}
