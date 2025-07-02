package service;

import port.in.StartMatchUseCase;
import port.out.StartMatchPort;

public class StartMatchService implements StartMatchUseCase {

    private final StartMatchPort startMatchPort;

    public StartMatchService(StartMatchPort startMatchPort) {
        this.startMatchPort = startMatchPort;
    }

    @Override
    public void apply(String matchData) {
        startMatchPort.apply(matchData);
    }
}

