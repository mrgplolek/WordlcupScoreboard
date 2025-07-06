package com.scoreboard.core.port.out;

import com.scoreboard.core.domain.FootballMatch;

@FunctionalInterface
public interface FinishMatchPort {

    boolean apply(FootballMatch footballMatch);
}
