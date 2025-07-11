package com.scoreboard.adapter.outdb.adapter;

import com.scoreboard.adapter.outdb.entity.FootballMatchEntity;
import com.scoreboard.core.domain.FootballMatch;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinishMatchAdapterTest extends BaseAdapterTest {

    private static FinishMatchAdapter adapterUnderTest;

    @BeforeAll
    static void setUpDb() {
        adapterUnderTest = FinishMatchAdapter.getInstance();
    }

    @Test
    void shouldFinishMatch(){
        //given
        String homeTeam = "Germany";
        String awayTeam = "France";
        FootballMatch footballMatch = new FootballMatch(homeTeam, awayTeam, 3, 0);
        //when
        boolean status = adapterUnderTest.apply(footballMatch);
        //then
        assertThat(status).isTrue();
        FootballMatchEntity result = scoreboardRepository.getSummary().stream()
                .filter(matchEntity -> matchEntity.getHomeTeam().equals(homeTeam) && matchEntity.getAwayTeam().equals(awayTeam))
                .findFirst()
                .orElse(null);

        assertThat(result).isNotNull();
        assertThat(result.getFinishedAt()).isNotNull();
    }

    @Test
    void shouldNotFinishNonExistingMatch(){
        //given
        String homeTeam = "Mexico";
        String awayTeam = "Canada";
        FootballMatch footballMatch = new FootballMatch(homeTeam, awayTeam, 3, 0);
        //when
        boolean status = adapterUnderTest.apply(footballMatch);
        //then
        assertThat(status).isFalse();
    }

}
