package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.entity.FootballMatchEntity;
import com.scoreboard.adapter.outdb.repository.ScoreboardRepository;
import com.scoreboard.core.domain.FootballMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class StartMatchAdapterTest {

    ScoreboardRepository scoreboardRepository;

    private StartMatchAdapter adapterUnderTest;

    @BeforeEach
    void setUpDb() {
        adapterUnderTest = new StartMatchAdapter();
        scoreboardRepository = ScoreboardRepository.getInstance();
    }

    @Test
    void shouldStartNewMatch() {
        //given
        String homeTeam = "Germany";
        String awayTeam = "Brazil";
        FootballMatchEntity mockedEntity = provideEntityMock(homeTeam, awayTeam);

        //when
        FootballMatch footballMatch = adapterUnderTest.apply(homeTeam, awayTeam);
        //then

        assertThat(footballMatch).isNotNull();
        assertThat(footballMatch.getHomeTeam()).matches("Germany");
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