package com.scoreboard.adapter.outdb.repository;


import com.scoreboard.adapter.outdb.adapter.BaseAdapterTest;
import com.scoreboard.adapter.outdb.entity.FootballMatchEntity;
import com.scoreboard.core.domain.FootballMatch;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreboardRepositoryTest extends BaseAdapterTest {

    @Test
    void shouldReturnRunningMatchByContestants() {
        //given
        String homeTeam = "Spain";
        String awayTeam = "Poland";
        //when
        Optional<FootballMatchEntity> matchEntity = scoreboardRepository.findRunningMatchByContestants(homeTeam, awayTeam);
        //then
        assertThat(matchEntity).isNotNull();
        assertThat(matchEntity).isPresent();
        assertThat(matchEntity.get().getHomeTeam()).contains("Spain");
        assertThat(matchEntity.get().getAwayTeam()).contains("Poland");
        assertThat(matchEntity.get().getFinishedAt()).isNull();
    }

    @Test
    void shouldReturnOptionalEmptyWhenGivenFinishedMatchContestants() {
        //given
        String homeTeam = "England";
        String awayTeam = "Portugal";
        //when
        Optional<FootballMatchEntity> matchEntity = scoreboardRepository.findRunningMatchByContestants(homeTeam, awayTeam);
        //then
        assertThat(matchEntity).isEmpty();
    }

    @Test
    void shouldReturnRunningMatchByContestant() {
        //given
        String contestant = "Spain";
        //when
        Optional<FootballMatchEntity> matchEntity = scoreboardRepository.findRunningMatchByContestant(contestant);
        //then
        assertThat(matchEntity).isNotNull();
        assertThat(matchEntity).isPresent();
        assertThat(matchEntity.get().getHomeTeam()).contains("Spain");
        assertThat(matchEntity.get().getAwayTeam()).contains("Poland");
        assertThat(matchEntity.get().getFinishedAt()).isNull();
    }

    @Test
    void shouldReturnOptionalEmptyWhenGivenFinishedMatchContestant() {
        //given
        String contestant = "Sweden";
        //when
        Optional<FootballMatchEntity> matchEntity = scoreboardRepository.findRunningMatchByContestant(contestant);
        //then
        assertThat(matchEntity).isEmpty();
    }

    @Test
    void shouldStartNewMatch() {
        //given
        String homeTeam = "Ukraine";
        String awayTeam = "Italy";
        //when
        FootballMatchEntity matchEntity = scoreboardRepository.startNewMatch(homeTeam, awayTeam);
        //then
        assertThat(matchEntity).isNotNull();
        assertThat(matchEntity.getHomeTeam()).isEqualTo("Ukraine");
        assertThat(matchEntity.getAwayTeam()).isEqualTo("Italy");
        assertThat(matchEntity.getHomeTeamScore()).isEqualTo(0);
        assertThat(matchEntity.getAwayTeamScore()).isEqualTo(0);
        assertThat(matchEntity.getStartedAt()).isNotNull();
        assertThat(matchEntity.getFinishedAt()).isNull();
    }

    @Test
    void shouldUpdateMatch() {
        //given
        FootballMatch footballMatch = new FootballMatch("Spain", "Poland", 0, 1);
        //when
        Optional<FootballMatchEntity> matchEntity = scoreboardRepository.updateScore(footballMatch);
        //then
        assertThat(matchEntity).isNotNull();
        assertThat(matchEntity).isPresent();
        assertThat(matchEntity.get().getHomeTeamScore()).isEqualTo(0);
        assertThat(matchEntity.get().getAwayTeamScore()).isEqualTo(1);
        assertThat(matchEntity.get().getHomeTeamLastScore()).isNull();
        assertThat(matchEntity.get().getAwayTeamLastScore()).isNotNull();
    }

    @Test
    void shouldFinishMatch() {
        //given
        FootballMatch footballMatch = new FootballMatch("Germany", "France", 3, 0);
        //when
        Boolean result = scoreboardRepository.finishMatch(footballMatch);
        //then
        assertThat(result).isTrue();
        FootballMatchEntity finishedMatch = scoreboardRepository.databaseMock.getSummary().stream()
                .filter(match -> match.getHomeTeam().equals("Germany") && match.getAwayTeam().equals("France"))
                .findFirst()
                .orElse(null);
        assertThat(finishedMatch).isNotNull();
        assertThat(finishedMatch.getHomeTeam()).isEqualTo("Germany");
        assertThat(finishedMatch.getAwayTeam()).isEqualTo("France");
        assertThat(finishedMatch.getFinishedAt()).isNotNull();
    }

}
