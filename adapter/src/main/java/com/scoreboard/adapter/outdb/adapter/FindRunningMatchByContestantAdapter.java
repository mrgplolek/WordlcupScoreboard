package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.mapper.FootballMatchMapper;
import com.scoreboard.adapter.outdb.repository.ScoreboardRepository;
import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.out.FindRunningMatchByContestantPort;

public class FindRunningMatchByContestantAdapter implements FindRunningMatchByContestantPort {

    private static FindRunningMatchByContestantAdapter instance;
    private final ScoreboardRepository scoreboardRepository = ScoreboardRepository.getInstance();

    private FindRunningMatchByContestantAdapter() {}

    public static FindRunningMatchByContestantAdapter getInstance() {
        if (instance == null) {
            instance = new FindRunningMatchByContestantAdapter();
        }
        return instance;
    }
    @Override
    public FootballMatch apply(String contestant) {
        return FootballMatchMapper.map(scoreboardRepository.findRunningMatchByContestant(contestant));
    }
}
