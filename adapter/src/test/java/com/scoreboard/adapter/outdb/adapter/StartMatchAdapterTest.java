package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.mapper.FootballMatchMapper;
import com.scoreboard.adapter.outdb.repository.ScoreboardRepository;
import com.scoreboard.core.domain.FootballMatch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


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
        String contestants = "Germany-Brazil";

        //when
        FootballMatch footballMatch = adapterUnderTest.apply(contestants);
        //then
        verify(scoreboardRepository, times(1)).findMatchByContestants(contestants);
        verify(scoreboardRepository, times(1)).startMatchByContestants(contestants);
        verify(footballMatchMapper, times(1)).map(null);

        assertThat(footballMatch).isNotNull();
        assertThat(footballMatch.homeTeam).matches("Germany");
        assertThat(footballMatch.awayTeam).matches("Brazil");
        assertThat(footballMatch.awayTeamScore).isEqualTo(0);
        assertThat(footballMatch.homeTeamScore).isEqualTo(0);

    }

}