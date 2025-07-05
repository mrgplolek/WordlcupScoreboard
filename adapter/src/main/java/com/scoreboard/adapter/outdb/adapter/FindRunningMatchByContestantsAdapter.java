package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.out.FindRunningMatchByContestantsPort;

public class FindRunningMatchByContestantsAdapter implements FindRunningMatchByContestantsPort {

    @Override
    public FootballMatch apply(String awayTeam, String homeTeam) {
        return null;
    }
}
