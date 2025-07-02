package in.web;

import port.in.StartMatchUseCase;

public class ScoreboardController {

    private final StartMatchUseCase startMatchUseCase;

    public ScoreboardController(StartMatchUseCase startMatchUseCase) {
        this.startMatchUseCase = startMatchUseCase;
    }

    public void startMatch () {
        startMatchUseCase.apply(null);
    }
}
