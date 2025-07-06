package com.scoreboard.core.service;

import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.domain.exception.MatchNotFoundException;
import com.scoreboard.core.port.in.FinishMatchUseCase;
import com.scoreboard.core.port.out.FindRunningMatchByContestantsPort;
import com.scoreboard.core.port.out.FinishMatchPort;

public class FinishMatchService implements FinishMatchUseCase {

    private final FinishMatchPort finishMatchPort;
    private final FindRunningMatchByContestantsPort findRunningMatchByContestantsPort;

    public FinishMatchService(FinishMatchPort finishMatchPort, FindRunningMatchByContestantsPort findRunningMatchByContestantsPort) {
        this.finishMatchPort = finishMatchPort;
        this.findRunningMatchByContestantsPort = findRunningMatchByContestantsPort;
    }

    @Override
    public boolean apply(String homeTeam, String awayTeam) {
        FootballMatch match = findRunningMatchByContestantsPort.apply(homeTeam, awayTeam);
        if (match == null) {
            throw new MatchNotFoundException("There is no currently running match between %s and %s.".formatted(homeTeam, awayTeam));
        }
        return finishMatchPort.apply(match);
    }
}
