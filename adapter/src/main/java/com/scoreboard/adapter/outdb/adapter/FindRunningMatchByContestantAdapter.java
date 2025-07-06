package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.mapper.FootballMatchMapper;
import com.scoreboard.adapter.outdb.repository.ScoreboardRepository;
import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.out.FindRunningMatchByContestantPort;

public class FindRunningMatchByContestantAdapter implements FindRunningMatchByContestantPort {

    private final ScoreboardRepository scoreboardRepository = ScoreboardRepository.getInstance();

    @Override
    public FootballMatch apply(String contestant) {
        return FootballMatchMapper.map(scoreboardRepository.findRunningMatchByContestant(contestant));
    }
}
