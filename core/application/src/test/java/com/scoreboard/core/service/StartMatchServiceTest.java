package com.scoreboard.core.service;


import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.domain.exception.ContestantIsAlreadyInPlayException;
import com.scoreboard.core.domain.exception.ContestantsDuplicatedException;
import com.scoreboard.core.port.out.FindRunningMatchByContestantPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.scoreboard.core.port.out.StartMatchPort;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StartMatchServiceTest {

    @Mock
    private StartMatchPort startMatchPort;

    @Mock
    private FindRunningMatchByContestantPort findRunningMatchByContestantPort;

    @InjectMocks
    private StartMatchService serviceUnderTest;

    @Test
    void shouldCallPortWithCorrectMatchData(){
        //given
        String homeTeam = "Germany";
        String awayTeam = "Brazil";
        when(startMatchPort.apply(homeTeam, awayTeam))
                .thenReturn(new FootballMatch(homeTeam, awayTeam, 0, 0, Instant.parse("2025-07-01T14:49:15Z"), null));
        when(findRunningMatchByContestantPort.apply(homeTeam)).thenReturn(Optional.empty());
        when(findRunningMatchByContestantPort.apply(awayTeam)).thenReturn(Optional.empty());
        //when
        FootballMatch result = serviceUnderTest.apply(homeTeam,awayTeam);
        //then
        verify(startMatchPort, times(1)).apply(homeTeam, awayTeam);
        assertThat(result).isNotNull();
        assertThat(result.getHomeTeam()).isEqualTo(homeTeam);
        assertThat(result.getAwayTeam()).isEqualTo(awayTeam);
        assertThat(result.getHomeTeamScore()).isEqualTo(0);
        assertThat(result.getAwayTeamScore()).isEqualTo(0);
        assertThat(result.getFinishedAt()).isNull();
        assertThat(result.getStartedAt()).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenHomeAndAwayTeamAreTheSame() {
        //given
        String homeTeam = "Poland";
        String awayTeam = "Poland";
        //when
        Throwable thrownException = catchThrowable(() -> serviceUnderTest.apply(homeTeam,awayTeam));
        //then
        assertThat(thrownException).isInstanceOf(ContestantsDuplicatedException.class)
                .hasMessage(homeTeam + " national team is duplicated.");
    }

    @Test
    void shouldThrowAnExceptionWhenMatchIsAlreadyRunningForOneOfTheContestants() {
        //given
        String homeTeam = "Germany";
        String awayTeam = "Brazil";
        when(findRunningMatchByContestantPort.apply(homeTeam))
                .thenReturn(Optional.of(new FootballMatch("Belgium", homeTeam, 5, 4)));
        //when
        Throwable thrownException = catchThrowable(() -> serviceUnderTest.apply(homeTeam,awayTeam));
        //then
        assertThat(thrownException).isInstanceOf(ContestantIsAlreadyInPlayException.class)
                .hasMessage(homeTeam + " national team is already involved in running match.");
    }
}