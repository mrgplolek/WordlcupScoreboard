package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.core.domain.FootballMatch;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class StartMatchAdapterTest extends BaseAdapterTest {

    private static StartMatchAdapter adapterUnderTest;

    @BeforeAll
    static void setUpDb() {
        adapterUnderTest = StartMatchAdapter.getInstance();
    }

    @Test
    void shouldStartNewMatch() {
        //given
        String homeTeam = "Greece";
        String awayTeam = "Brazil";
        //when
        FootballMatch footballMatch = adapterUnderTest.apply(homeTeam, awayTeam);
        //then

        assertThat(footballMatch).isNotNull();
        assertThat(footballMatch.getHomeTeam()).matches("Greece");
        assertThat(footballMatch.getAwayTeam()).matches("Brazil");
        assertThat(footballMatch.getAwayTeamScore()).isEqualTo(0);
        assertThat(footballMatch.getHomeTeamScore()).isEqualTo(0);
        assertThat(footballMatch.getStartedAt()).isNotNull();
        assertThat(footballMatch.getFinishedAt()).isNull();

    }

}