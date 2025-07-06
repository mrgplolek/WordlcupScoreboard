package com.scoreboard.core.service;


import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.domain.exception.ContestantIsAlreadyInPlayException;
import com.scoreboard.core.port.out.FindRunningMatchByContestantPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.scoreboard.core.port.out.StartMatchPort;

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
    FindRunningMatchByContestantPort findRunningMatchByContestantPort;

    @InjectMocks
    private StartMatchService serviceUnderTest;

    @Test
    void shouldCallPortWithCorrectMatchData(){
        //given
        String homeTeam = "Germany";
        String awayTeam = "Brazil";
        when(startMatchPort.apply(homeTeam, awayTeam)).thenReturn(new FootballMatch(homeTeam, awayTeam, 0, 0));
        //when
        serviceUnderTest.apply(homeTeam,awayTeam);
        //then
        verify(startMatchPort, times(1)).apply(homeTeam, awayTeam);
    }

    @Test
    void shouldThrowAnExceptionWhenMatchIsAlreadyRunningForOneOfTheContestants() {
        //given
        String homeTeam = "Germany";
        String awayTeam = "Brazil";
        when(findRunningMatchByContestantPort.apply(homeTeam)).thenReturn(new FootballMatch("Belgium", homeTeam, 5, 4));
        //when
        Throwable thrownException = catchThrowable(() -> {
            serviceUnderTest.apply(homeTeam,awayTeam);
        });
        //then
        assertThat(thrownException).isInstanceOf(ContestantIsAlreadyInPlayException.class).hasMessage(homeTeam + " national team is already involved in running match.");
    }
}