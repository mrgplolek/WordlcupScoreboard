package com.scoreboard.core.service;

import com.scoreboard.core.domain.FootballMatch;
import com.scoreboard.core.port.out.GetMatchesSummaryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetMatchesSummaryServiceTest {

    @Mock
    private GetMatchesSummaryPort getMatchesSummaryPort;


    @InjectMocks
    private GetMatchesSummaryService serviceUnderTest;

    @Test
    void shouldGetMatchesSummaryInCorrectOrder() {
        //given
        when(getMatchesSummaryPort.apply()).thenReturn(prepareMockedData());
        //when
        List<FootballMatch> result = serviceUnderTest.apply();
        //then
        assertThat(result.size()).isEqualTo(5);
        assertThat(result).usingRecursiveComparison().isEqualTo(prepareExpectedResult());
    }

    @Test
    void shouldReturnEmptyListWhenNoRunningMatches() {
        //given
        when(getMatchesSummaryPort.apply()).thenReturn(List.of(new FootballMatch("Poland", "Georgia", 4, 3,
                Instant.parse("2025-07-01T13:09:15Z"), Instant.parse("2025-07-01T14:49:15Z"))));
        //when
        List<FootballMatch> result = serviceUnderTest.apply();
        //then
        assertThat(result).isEmpty();
    }

    private static List<FootballMatch> prepareMockedData() {
        FootballMatch match1 = new FootballMatch("Poland", "Georgia", 4, 3,
                Instant.parse("2025-07-01T13:09:15Z"), Instant.parse("2025-07-01T14:49:15Z"));
        FootballMatch match2 = new FootballMatch("Mexico", "Canada", 0, 5,
                Instant.parse("2025-07-03T13:09:15Z"), null);
        FootballMatch match3 = new FootballMatch("Spain", "Brazil", 10, 2,
                Instant.parse("2025-07-03T15:09:15Z"), null);
        FootballMatch match4 = new FootballMatch("Germany", "France", 2, 2,
                Instant.parse("2025-07-03T15:21:15Z"), null);
        FootballMatch match5 = new FootballMatch("Uruguay", "Italy", 6, 6,
                Instant.parse("2025-07-03T16:01:15Z"), null);
        FootballMatch match6 = new FootballMatch("Argentina", "Australia", 3, 1,
                Instant.parse("2025-07-03T16:45:15Z"), null);
        return List.of(match1, match2, match3, match4, match5, match6);
    }

    private static List<FootballMatch> prepareExpectedResult() {
        FootballMatch match1 = new FootballMatch("Mexico", "Canada", 0, 5,
                Instant.parse("2025-07-03T13:09:15Z"), null);
        FootballMatch match2 = new FootballMatch("Spain", "Brazil", 10, 2,
                Instant.parse("2025-07-03T15:09:15Z"), null);
        FootballMatch match3 = new FootballMatch("Germany", "France", 2, 2,
                Instant.parse("2025-07-03T15:21:15Z"), null);
        FootballMatch match4 = new FootballMatch("Uruguay", "Italy", 6, 6,
                Instant.parse("2025-07-03T16:01:15Z"), null);
        FootballMatch match5 = new FootballMatch("Argentina", "Australia", 3, 1,
                Instant.parse("2025-07-03T16:45:15Z"), null);
        return List.of(match4, match2, match1, match5, match3);
    }
}
