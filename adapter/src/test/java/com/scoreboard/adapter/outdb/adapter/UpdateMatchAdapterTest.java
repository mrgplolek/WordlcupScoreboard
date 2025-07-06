package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.entity.FootballMatchEntity;
import com.scoreboard.core.domain.FootballMatch;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateMatchAdapterTest extends BaseAdapterTest {

    private static UpdateMatchAdapter adapterUnderTest;

    @BeforeAll
    static void setUpDb() {
        adapterUnderTest = UpdateMatchAdapter.getInstance();
    }

    @Test
    void shouldUpdateMatch() {
        //given
        String homeTeam = "Germany";
        String awayTeam = "France";
        FootballMatch footballMatch = new FootballMatch(homeTeam, awayTeam, 3, 1);
        //when
        adapterUnderTest.apply(footballMatch);
        //then
        FootballMatchEntity result = scoreboardRepository.getSummary().stream()
                .filter(matchEntity -> matchEntity.getHomeTeam().equals(homeTeam) && matchEntity.getAwayTeam().equals(awayTeam))
                .findFirst()
                .orElse(null);

        assertThat(result).isNotNull();
        assertThat(result.getHomeTeamScore()).isEqualTo(3);
        assertThat(result.getAwayTeamScore()).isEqualTo(1);
        assertThat(result.getAwayTeamLastScore()).isNotNull();
    }
}
