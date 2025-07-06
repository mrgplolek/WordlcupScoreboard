package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.entity.FootballMatchEntity;
import com.scoreboard.adapter.outdb.repository.ScoreboardRepository;
import com.scoreboard.core.domain.FootballMatch;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinishMatchAdapterTest {

    static ScoreboardRepository scoreboardRepository;

    private static FinishMatchAdapter adapterUnderTest;

    @BeforeAll
    static void setUpDb() {
        adapterUnderTest = new FinishMatchAdapter();
        scoreboardRepository = ScoreboardRepository.getInstance();
        scoreboardRepository.setupTestData();
    }

    @AfterAll
    static void cleanUp() {
        scoreboardRepository.cleanUpDb();
    }

    @Test
    void shouldFinishMatch(){
        //given
        String homeTeam = "Germany";
        String awayTeam = "France";
        FootballMatch footballMatch = new FootballMatch(homeTeam, awayTeam, 3, 0);

        //when
        adapterUnderTest.apply(footballMatch);
        //then
        FootballMatchEntity result = scoreboardRepository.getSummary().stream()
                .filter(matchEntity -> matchEntity.getHomeTeam().equals(homeTeam) && matchEntity.getAwayTeam().equals(awayTeam))
                .findFirst()
                .orElse(null);

        assertThat(result).isNotNull();
        assertThat(result.getFinishedAt()).isNotNull();
    }

}
