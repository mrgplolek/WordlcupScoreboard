package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.out.GetMatchesSummaryPort;

import java.util.List;

public class GetMatchesSummaryAdapter implements GetMatchesSummaryPort {

    @Override
    public List<FootballMatch> getMatchesSummary() {
        return List.of();
    }
}
