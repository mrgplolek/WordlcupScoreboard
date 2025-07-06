package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.core.domain.FootballMatch;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class FindRunningMatchByContestantsAdapterTest extends BaseAdapterTest {

    private static FindRunningMatchByContestantsAdapter adapterUnderTest;

    @BeforeAll
    static void setUpDb() {
        adapterUnderTest = FindRunningMatchByContestantsAdapter.getInstance();
    }

    @Test
    void shouldFindRunningMatchByContestants() {
        //given
        String homeTeam = "Germany";
        String awayTeam = "France";
        //when
        Optional<FootballMatch> result = adapterUnderTest.apply(homeTeam, awayTeam);
        //then
        assertThat(result).isNotNull();
        assertThat(result).isPresent();
        assertThat(result.get().getHomeTeam()).isEqualTo(homeTeam);
        assertThat(result.get().getAwayTeam()).isEqualTo(awayTeam);
        assertThat(result.get().getHomeTeamScore()).isEqualTo(3);
        assertThat(result.get().getAwayTeamScore()).isEqualTo(0);
    }
}
