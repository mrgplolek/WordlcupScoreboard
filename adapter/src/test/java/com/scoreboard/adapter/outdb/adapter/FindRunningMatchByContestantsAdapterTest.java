package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.repository.ScoreboardRepository;
import com.scoreboard.core.domain.FootballMatch;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FindRunningMatchByContestantsAdapterTest {

    static ScoreboardRepository scoreboardRepository;

    private static FindRunningMatchByContestantsAdapter adapterUnderTest;

    @BeforeAll
    static void setUpDb() {
        adapterUnderTest = new FindRunningMatchByContestantsAdapter();
        scoreboardRepository = ScoreboardRepository.getInstance();
        scoreboardRepository.setupTestData();
    }

    @AfterAll
    static void cleanUp() {
        scoreboardRepository.cleanUpDb();
    }

    @Test
    void shouldFindRunningMatchByContestants() {
        //given
        String homeTeam = "Germany";
        String awayTeam = "France";
        //when
        FootballMatch result = adapterUnderTest.apply(homeTeam, awayTeam);
        //then
        assertThat(result).isNotNull();
        assertThat(result.getHomeTeam()).isEqualTo(homeTeam);
        assertThat(result.getAwayTeam()).isEqualTo(awayTeam);
        assertThat(result.getHomeTeamScore()).isEqualTo(3);
        assertThat(result.getAwayTeamScore()).isEqualTo(0);

    }
}
