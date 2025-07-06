package com.scoreboard.core.service;

import com.scoreboard.core.port.in.FinishMatchUseCase;

public class FinishMatchService implements FinishMatchUseCase {

    @Override
    public boolean apply(String homeTeam, String awayTeam) {
        return false;
    }
}
