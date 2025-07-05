package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.mapper.FootballMatchMapper;
import com.scoreboard.adapter.outdb.repository.ScoreboardRepository;
import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.out.FindRunningMatchByContestantsPort;

public class FindRunningMatchByContestantsAdapter implements FindRunningMatchByContestantsPort {

    private final ScoreboardRepository scoreboardRepository = ScoreboardRepository.getInstance();

    @Override
    public FootballMatch apply(String homeTeam, String awayTeam) {
        return FootballMatchMapper.map(scoreboardRepository.findRunningMatchByContestants(homeTeam, awayTeam));
    }
}
