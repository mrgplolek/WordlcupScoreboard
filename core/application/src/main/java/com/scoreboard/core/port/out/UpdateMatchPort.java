package com.scoreboard.core.port.out;

import com.scoreboard.core.domain.FootballMatch;

import java.util.Optional;

@FunctionalInterface
public interface UpdateMatchPort {

    Optional<FootballMatch> apply(FootballMatch footballMatch);

}
