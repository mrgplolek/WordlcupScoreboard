package com.scoreboard.core.service;

import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.domain.exception.ContestantIsAlreadyInPlayException;
import com.scoreboard.core.port.in.StartMatchUseCase;
import com.scoreboard.core.port.out.FindRunningMatchByContestantPort;
import com.scoreboard.core.port.out.StartMatchPort;

public class StartMatchService implements StartMatchUseCase {

    private static StartMatchService instance;

    private final StartMatchPort startMatchPort;
    private final FindRunningMatchByContestantPort findRunningMatchByContestantPort;

    private StartMatchService(StartMatchPort startMatchPort, FindRunningMatchByContestantPort findRunningMatchByContestantPort) {
        this.startMatchPort = startMatchPort;
        this.findRunningMatchByContestantPort = findRunningMatchByContestantPort;
    }

    public static StartMatchService getInstance(StartMatchPort startMatchPort, FindRunningMatchByContestantPort findRunningMatchByContestantPort) {
        if (instance == null) {
            instance = new StartMatchService(startMatchPort, findRunningMatchByContestantPort);
        }
        return instance;
    }

    @Override
    public FootballMatch apply(String homeTeam, String awayTeam) {
        boolean isHomeTeamAlreadyInGame = findRunningMatchByContestantPort.apply(homeTeam) != null;
        boolean isAwayTeamAlreadyInGame = findRunningMatchByContestantPort.apply(awayTeam) != null;
        if (isHomeTeamAlreadyInGame){
            throw new ContestantIsAlreadyInPlayException("%s national team is already involved in running match.".formatted(homeTeam));
        }
        if (isAwayTeamAlreadyInGame){
            throw new ContestantIsAlreadyInPlayException("%s national team is already involved in running match.".formatted(awayTeam));
        }
        return startMatchPort.apply(homeTeam, awayTeam);
    }
}

