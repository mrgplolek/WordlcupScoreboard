package com.scoreboard.core.service;

import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.domain.exception.IncorrectUpdateMatchDataException;
import com.scoreboard.core.domain.exception.MatchNotFoundException;
import com.scoreboard.core.port.out.FindRunningMatchByContestantsPort;
import com.scoreboard.core.port.out.UpdateMatchPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateMatchServiceTest {

    @Mock
    private FindRunningMatchByContestantsPort findRunningMatchByContestantsPort;

    @Mock
    private UpdateMatchPort updateMatchPort;

    @InjectMocks
    private UpdateMatchService serviceUnderTest;

    @Test
    void shouldUpdateMatch(){
        //given
        FootballMatch matchToBeUpdated = new FootballMatch("Poland", "Germany", 0, 0, Instant.parse("2025-07-01T14:49:15Z"), null);
        FootballMatch matchUpdateData = new FootballMatch("Poland", "Germany", 1, 0);
        FootballMatch matchAfterUpdate = new FootballMatch("Poland", "Germany", 1, 0, Instant.parse("2025-07-01T14:49:15Z"), null);
        when(findRunningMatchByContestantsPort.apply("Poland", "Germany")).thenReturn(matchToBeUpdated);
        when(updateMatchPort.apply(matchUpdateData)).thenReturn(matchAfterUpdate);
        //when
        FootballMatch result = serviceUnderTest.apply(matchUpdateData);
        //then
        assertThat(result).isNotNull().isEqualTo(matchAfterUpdate);
    }

    @Test
    void shouldThrowExceptionWhenMatchNotFound() {
        //given
        FootballMatch matchUpdateData = new FootballMatch("Poland", "Germany", 1, 0);
        when(findRunningMatchByContestantsPort.apply("Poland", "Germany")).thenReturn(null);
        //when
        Throwable throwable = catchThrowable(() -> {
            serviceUnderTest.apply(matchUpdateData);
        });
        //then
        assertThat(throwable).isInstanceOf(MatchNotFoundException.class).hasMessage("There is no currently running match between Poland and Germany.");
    }

    @Test
    void shouldThrowExceptionWhenUpdateContainsMoreThanOneGoal(){
        //given
        FootballMatch matchUpdateData = new FootballMatch("Poland", "Germany", 1, 1);
        FootballMatch matchToBeUpdated = new FootballMatch("Poland", "Germany", 0, 0, Instant.parse("2025-07-01T14:49:15Z"), null);
        when(findRunningMatchByContestantsPort.apply("Poland", "Germany")).thenReturn(matchToBeUpdated);
        //when
        Throwable throwable = catchThrowable(() -> {
            serviceUnderTest.apply(matchUpdateData);
        });
        //then
        assertThat(throwable).isInstanceOf(IncorrectUpdateMatchDataException.class).hasMessage("Cannot update more than one goal.");
    }

    @Test
    void shouldThrowExceptionWhenUpdateContainsNegativeNumber(){
        //given
        FootballMatch matchUpdateData = new FootballMatch("Poland", "Germany", -1, 0);
        FootballMatch matchToBeUpdated = new FootballMatch("Poland", "Germany", 0, 0, Instant.parse("2025-07-01T14:49:15Z"), null);
        when(findRunningMatchByContestantsPort.apply("Poland", "Germany")).thenReturn(matchToBeUpdated);
        //when
        Throwable throwable = catchThrowable(() -> {
            serviceUnderTest.apply(matchUpdateData);
        });
        //then
        assertThat(throwable).isInstanceOf(IncorrectUpdateMatchDataException.class).hasMessage("Goal data cannot contain negative numbers.");
    }

}
