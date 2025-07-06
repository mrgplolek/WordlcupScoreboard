package com.scoreboard.core.service;

import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.domain.exception.IncorrectUpdateMatchDataException;
import com.scoreboard.core.domain.exception.MatchNotFoundException;
import com.scoreboard.core.port.in.UpdateMatchUseCase;
import com.scoreboard.core.port.out.FindRunningMatchByContestantsPort;
import com.scoreboard.core.port.out.UpdateMatchPort;

import java.util.Optional;

public class UpdateMatchService implements UpdateMatchUseCase {

    private static UpdateMatchService instance;

    private final FindRunningMatchByContestantsPort findRunningMatchByContestantsPort;
    private final UpdateMatchPort updateMatchPort;

    private UpdateMatchService(FindRunningMatchByContestantsPort findRunningMatchByContestantsPort,
                               UpdateMatchPort updateMatchPort) {
        this.findRunningMatchByContestantsPort = findRunningMatchByContestantsPort;
        this.updateMatchPort = updateMatchPort;
    }

    public static UpdateMatchService getInstance(FindRunningMatchByContestantsPort findRunningMatchByContestantsPort,
                                                 UpdateMatchPort updateMatchPort) {
        if (instance == null) {
            instance = new UpdateMatchService(findRunningMatchByContestantsPort, updateMatchPort);
        }
        return instance;
    }
    @Override
    public FootballMatch apply(FootballMatch match) {
        Optional<FootballMatch> matchToBeUpdated = findRunningMatchByContestantsPort.apply(match.getHomeTeam(), match.getAwayTeam());
        if (matchToBeUpdated.isEmpty()) {
            throw new MatchNotFoundException("There is no currently running match between %s and %s."
                    .formatted(match.getHomeTeam(), match.getAwayTeam()));
        }
        if (match.getAwayTeamScore() < 0 || match.getHomeTeamScore() < 0){
            throw new IncorrectUpdateMatchDataException("Goal data cannot contain negative numbers.");
        }
        if (isUpdatingMoreThanOneGoal(match, matchToBeUpdated.get())){
            throw new IncorrectUpdateMatchDataException("Cannot update more than one goal.");
        }
        Optional<FootballMatch> updatedFootballMatch = updateMatchPort.apply(match);

        if (updatedFootballMatch.isEmpty()){
            throw new RuntimeException("Something went wrong with updating the match. Please contact server administration.");
        }

        return updatedFootballMatch.get();
    }

    private boolean isUpdatingMoreThanOneGoal(FootballMatch match, FootballMatch matchToBeUpdated) {
        return (match.getHomeTeamScore() != matchToBeUpdated.getHomeTeamScore() && match.getAwayTeamScore() != matchToBeUpdated.getAwayTeamScore())
                || Math.abs(matchToBeUpdated.getHomeTeamScore() - match.getHomeTeamScore()) > 1 ||  Math.abs(matchToBeUpdated.getAwayTeamScore() - match.getAwayTeamScore()) > 1;
    }
}
