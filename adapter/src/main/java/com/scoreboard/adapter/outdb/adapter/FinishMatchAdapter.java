package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.repository.ScoreboardRepository;
import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.out.FinishMatchPort;

public class FinishMatchAdapter implements FinishMatchPort {

    private static FinishMatchAdapter instance;
    private final ScoreboardRepository scoreboardRepository = ScoreboardRepository.getInstance();

    private FinishMatchAdapter() {}

    public static FinishMatchAdapter getInstance() {
        if (instance == null) {
            instance = new FinishMatchAdapter();
        }
        return instance;
    }

    @Override
    public boolean apply(FootballMatch footballMatch) {

        return scoreboardRepository.finishMatch(footballMatch);
    }
}
