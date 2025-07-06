package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.repository.ScoreboardRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseAdapterTest {

    protected static ScoreboardRepository scoreboardRepository;

    @BeforeAll
    public static void globalSetUp() {
        scoreboardRepository = ScoreboardRepository.getInstance();
        scoreboardRepository.setupTestData();
    }

    @AfterAll
    public static void globalCleanUp() {
        scoreboardRepository.cleanUpDb();
    }
}
