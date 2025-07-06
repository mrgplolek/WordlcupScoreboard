package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.repository.ScoreboardRepository;
import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.out.FinishMatchPort;

public class FinishMatchAdapter implements FinishMatchPort {

    private final ScoreboardRepository scoreboardRepository = ScoreboardRepository.getInstance();


    @Override
    public void apply(FootballMatch footballMatch) {

        scoreboardRepository.finishMatch(footballMatch);

    }
}
