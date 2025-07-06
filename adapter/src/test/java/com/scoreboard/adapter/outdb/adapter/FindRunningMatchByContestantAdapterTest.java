package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.core.domain.FootballMatch;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class FindRunningMatchByContestantAdapterTest extends BaseAdapterTest {

    private static FindRunningMatchByContestantAdapter adapterUnderTest;

    @BeforeAll
    static void setUpDb() {
        adapterUnderTest = FindRunningMatchByContestantAdapter.getInstance();
    }

    @Test
    void shouldFindRunningMatchByContestant1() {
        //given
        String homeTeam = "Germany";
        String awayTeam = "France";
        //when
        Optional<FootballMatch> result = adapterUnderTest.apply(homeTeam);
        //then
        assertThat(result).isNotNull();
        assertThat(result).isPresent();
        assertThat(result.get().getHomeTeam()).isEqualTo(homeTeam);
        assertThat(result.get().getAwayTeam()).isEqualTo(awayTeam);
        assertThat(result.get().getHomeTeamScore()).isEqualTo(3);
        assertThat(result.get().getAwayTeamScore()).isEqualTo(0);

    }

    @Test
    void shouldFindRunningMatchByContestant2() {
        //given
        String homeTeam = "Germany";
        String awayTeam = "France";
        //when
        Optional<FootballMatch> result = adapterUnderTest.apply(awayTeam);
        //then
        assertThat(result).isNotNull();
        assertThat(result).isPresent();
        assertThat(result.get().getHomeTeam()).isEqualTo(homeTeam);
        assertThat(result.get().getAwayTeam()).isEqualTo(awayTeam);
        assertThat(result.get().getHomeTeamScore()).isEqualTo(3);
        assertThat(result.get().getAwayTeamScore()).isEqualTo(0);

    }

    @Test
    void shouldNotFindRunningMatchByContestant() {
        //given
        String contestant = "Chile";
        //when
        Optional<FootballMatch> result = adapterUnderTest.apply(contestant);
        //then
        assertThat(result).isEmpty();
    }
}
