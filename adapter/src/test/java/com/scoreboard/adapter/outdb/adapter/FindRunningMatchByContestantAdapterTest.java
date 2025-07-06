package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.repository.ScoreboardRepository;
import com.scoreboard.core.domain.FootballMatch;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FindRunningMatchByContestantAdapterTest {

    static ScoreboardRepository scoreboardRepository;

    private static FindRunningMatchByContestantAdapter adapterUnderTest;

    @BeforeAll
    static void setUpDb() {
        adapterUnderTest = FindRunningMatchByContestantAdapter.getInstance();
        scoreboardRepository = ScoreboardRepository.getInstance();
        scoreboardRepository.setupTestData();
    }

    @AfterAll
    static void cleanUp() {
        scoreboardRepository.cleanUpDb();
    }

    @Test
    void shouldFindRunningMatchByContestant1() {
        //given
        String homeTeam = "Germany";
        String awayTeam = "France";
        //when
        FootballMatch result = adapterUnderTest.apply(homeTeam);
        //then
        assertThat(result).isNotNull();
        assertThat(result.getHomeTeam()).isEqualTo(homeTeam);
        assertThat(result.getAwayTeam()).isEqualTo(awayTeam);
        assertThat(result.getHomeTeamScore()).isEqualTo(3);
        assertThat(result.getAwayTeamScore()).isEqualTo(0);

    }

    @Test
    void shouldFindRunningMatchByContestant2() {
        //given
        String homeTeam = "Germany";
        String awayTeam = "France";
        //when
        FootballMatch result = adapterUnderTest.apply(awayTeam);
        //then
        assertThat(result).isNotNull();
        assertThat(result.getHomeTeam()).isEqualTo(homeTeam);
        assertThat(result.getAwayTeam()).isEqualTo(awayTeam);
        assertThat(result.getHomeTeamScore()).isEqualTo(3);
        assertThat(result.getAwayTeamScore()).isEqualTo(0);

    }

    @Test
    void shouldNotFindRunningMatchByContestant() {
        //given
        String contestant = "Chile";
        //when
        FootballMatch result = adapterUnderTest.apply(contestant);
        //then
        assertThat(result).isNull();
    }
}
