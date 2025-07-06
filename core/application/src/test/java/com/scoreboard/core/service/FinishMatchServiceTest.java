package com.scoreboard.core.service;

import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.domain.exception.MatchNotFoundException;
import com.scoreboard.core.port.out.FindRunningMatchByContestantsPort;
import com.scoreboard.core.port.out.FinishMatchPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FinishMatchServiceTest {

    @Mock
    private FinishMatchPort finishMatchPort;

    @Mock
    private FindRunningMatchByContestantsPort findRunningMatchByContestantsPort;

    @InjectMocks
    private FinishMatchService serviceUnderTest;

    @Test
    void shouldFinishMatch(){
        //given
        String homeTeam = "Argentina";
        String awayTeam = "Mexico";
        Optional<FootballMatch> footballMatch = Optional.of(new FootballMatch(homeTeam, awayTeam, 0, 0));
        when(findRunningMatchByContestantsPort.apply(homeTeam, awayTeam)).thenReturn(footballMatch);
        when(finishMatchPort.apply(footballMatch.get())).thenReturn(true);
        //when
        boolean status = serviceUnderTest.apply(homeTeam, awayTeam);
        //then
        assertThat(status).isTrue();
    }

    @Test
    void shouldNotFinishNotRunningMatch(){
        //given
        String homeTeam = "Argentina";
        String awayTeam = "Mexico";
        when(findRunningMatchByContestantsPort.apply(homeTeam, awayTeam)).thenReturn(Optional.empty());
        //when
        Throwable thrownException = catchThrowable(() -> serviceUnderTest.apply(homeTeam,awayTeam));
        //then
        assertThat(thrownException).isInstanceOf(MatchNotFoundException.class)
                .hasMessage("There is no currently running match between Argentina and Mexico.");
    }
}
