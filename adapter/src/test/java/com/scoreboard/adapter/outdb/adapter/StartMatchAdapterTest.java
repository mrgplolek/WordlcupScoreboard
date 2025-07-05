package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.entity.FootballMatchEntity;
import com.scoreboard.adapter.outdb.mapper.FootballMatchMapper;
import com.scoreboard.adapter.outdb.repository.ScoreboardRepository;
import com.scoreboard.core.domain.FootballMatch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class StartMatchAdapterTest {

    @Mock
    ScoreboardRepository scoreboardRepository;

    @Mock
    FootballMatchMapper footballMatchMapper;

    @InjectMocks
    private StartMatchAdapter adapterUnderTest;

    @Test
    void shouldStartNewMatch() {
        //given
        String homeTeam = "Germany";
        String awayTeam = "Brazil";
        FootballMatchEntity mockedEntity = provideEntityMock(homeTeam, awayTeam);
        when(scoreboardRepository.startNewMatch(homeTeam, awayTeam)).thenReturn(mockedEntity);
        when(footballMatchMapper.map(mockedEntity))
                .thenReturn(new FootballMatch(homeTeam, awayTeam, 0, 0, Instant.parse("2025-07-01T13:09:15Z"), null));

        //when
        FootballMatch footballMatch = adapterUnderTest.apply(homeTeam, awayTeam);
        //then
        verify(scoreboardRepository, times(1)).findRunningMatchByContestants(homeTeam, awayTeam);
        verify(scoreboardRepository, times(1)).startNewMatch(homeTeam, awayTeam);
        verify(footballMatchMapper, times(1)).map(mockedEntity);

        assertThat(footballMatch).isNotNull();
        assertThat(footballMatch.getHomeTeam()).matches("Germany");
        assertThat(footballMatch.getAwayTeam()).matches("Brazil");
        assertThat(footballMatch.getAwayTeamScore()).isEqualTo(0);
        assertThat(footballMatch.getHomeTeamScore()).isEqualTo(0);
        assertThat(footballMatch.getStartedAt()).isEqualTo(Instant.parse("2025-07-01T13:09:15Z"));
        assertThat(footballMatch.getFinishedAt()).isNull();

    }

    private FootballMatchEntity provideEntityMock(String homeTeam, String awayTeam){
        FootballMatchEntity entity = FootballMatchEntity.createNewMatchEntity(homeTeam, awayTeam);
        entity.setStartedAt(Instant.parse("2025-07-01T13:09:15Z"));
        return entity;
    }

}