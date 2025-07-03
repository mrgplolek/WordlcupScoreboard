package com.scoreboard.core.port.out;

import com.scoreboard.core.domain.FootballMatch;

@FunctionalInterface
public interface StartMatchPort {

        FootballMatch apply(String matchData);
}
