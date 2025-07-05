package com.scoreboard.core.service;

import com.scoreboard.core.port.in.StartMatchUseCase;
import com.scoreboard.core.port.out.StartMatchPort;

public class StartMatchService implements StartMatchUseCase {

    private final StartMatchPort startMatchPort;

    public StartMatchService(StartMatchPort startMatchPort) {
        this.startMatchPort = startMatchPort;
    }

    @Override
    public void apply(String homeTeam, String awayTeam) {
        startMatchPort.apply(homeTeam, awayTeam);
    }
}

