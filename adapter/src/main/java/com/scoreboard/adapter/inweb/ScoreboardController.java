package com.scoreboard.adapter.inweb;


import com.scoreboard.core.port.in.StartMatchUseCase;

public class ScoreboardController {

    private final StartMatchUseCase startMatchUseCase;

    public ScoreboardController(StartMatchUseCase startMatchUseCase) {
        this.startMatchUseCase = startMatchUseCase;
    }

    public void startMatch () {
        startMatchUseCase.apply(null);
    }
}
