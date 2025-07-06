package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.entity.FootballMatchEntity;
import com.scoreboard.core.domain.FootballMatch;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;

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
        FootballMatchEntity mockedEntity = provideEntityMock(homeTeam, awayTeam);

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

    private FootballMatchEntity provideEntityMock(String homeTeam, String awayTeam){
        FootballMatchEntity entity = FootballMatchEntity.createNewMatchEntity(homeTeam, awayTeam);
        entity.setStartedAt(Instant.parse("2025-07-01T13:09:15Z"));
        return entity;
    }

}