package com.scoreboard.core.service;

import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.domain.exception.MatchNotFoundException;
import com.scoreboard.core.port.in.FinishMatchUseCase;
import com.scoreboard.core.port.out.FindRunningMatchByContestantsPort;
import com.scoreboard.core.port.out.FinishMatchPort;

import java.util.Optional;

public class FinishMatchService implements FinishMatchUseCase {

    private static FinishMatchService instance;

    private final FinishMatchPort finishMatchPort;
    private final FindRunningMatchByContestantsPort findRunningMatchByContestantsPort;

    private FinishMatchService(FinishMatchPort finishMatchPort, FindRunningMatchByContestantsPort findRunningMatchByContestantsPort) {
        this.finishMatchPort = finishMatchPort;
        this.findRunningMatchByContestantsPort = findRunningMatchByContestantsPort;
    }

    public static FinishMatchService getInstance(FinishMatchPort finishMatchPort, FindRunningMatchByContestantsPort findRunningMatchByContestantsPort) {
        if (instance == null) {
            instance = new FinishMatchService(finishMatchPort, findRunningMatchByContestantsPort);
        }
        return instance;
    }

    @Override
    public boolean apply(String homeTeam, String awayTeam) {
        Optional<FootballMatch> match = findRunningMatchByContestantsPort.apply(homeTeam, awayTeam);
        if (match.isEmpty()) {
            throw new MatchNotFoundException("There is no currently running match between %s and %s.".formatted(homeTeam, awayTeam));
        }
        return finishMatchPort.apply(match.get());
    }
}
