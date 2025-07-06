package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.core.domain.FootballMatch;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GetMatchesSummaryAdapterTest extends BaseAdapterTest {

    private static GetMatchesSummaryAdapter adapterUnderTest;

    @BeforeAll
    static void setUpDb() {
        adapterUnderTest = GetMatchesSummaryAdapter.getInstance();
    }

    @Test
    void shouldGetAllMatchesAsASummary() {
        //given
        //when
        List<FootballMatch> result = adapterUnderTest.apply();
        //then
        assertThat(result).isNotNull().isNotEmpty();
        assertThat(result.size()).isEqualTo(4);
        assertThat(result.getLast().getHomeTeam()).isEqualTo("England");
        assertThat(result.getLast().getAwayTeam()).isEqualTo("Portugal");
        assertThat(result.getLast().getAwayTeamScore()).isEqualTo(0);
        assertThat(result.getLast().getHomeTeamScore()).isEqualTo(0);
        assertThat(result.getLast().getFinishedAt()).isNotNull();
        assertThat(result.getLast().getStartedAt()).isNotNull();
    }
}
