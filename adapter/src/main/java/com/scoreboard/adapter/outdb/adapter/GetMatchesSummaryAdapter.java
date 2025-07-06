package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.mapper.FootballMatchMapper;
import com.scoreboard.adapter.outdb.repository.ScoreboardRepository;
import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.out.GetMatchesSummaryPort;

import java.util.List;

public class GetMatchesSummaryAdapter implements GetMatchesSummaryPort {

    private static GetMatchesSummaryAdapter instance;
    private final ScoreboardRepository scoreboardRepository = ScoreboardRepository.getInstance();

    private GetMatchesSummaryAdapter() {}

    public static GetMatchesSummaryAdapter getInstance() {
        if (instance == null) {
            instance = new GetMatchesSummaryAdapter();
        }
        return instance;
    }

    @Override
    public List<FootballMatch> apply() {
        return scoreboardRepository.getSummary().stream().map(FootballMatchMapper::map).toList();
    }
}
