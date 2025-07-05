package com.scoreboard.adapter.outdb.repository;


import com.scoreboard.adapter.outdb.entity.FootballMatchEntity;
import com.scoreboard.core.domain.FootballMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreboardRepositoryTest {

    ScoreboardRepository scoreboardRepository = ScoreboardRepository.getInstance();

    @BeforeEach
    void setUpDatabaseMock() {
        scoreboardRepository.databaseMock = new InMemoryDatabaseMock();
        FootballMatchEntity footballMatchEntity1 = FootballMatchEntity.createNewMatchEntity("Spain", "Poland");
        footballMatchEntity1.setStartedAt(Instant.parse("2025-07-03T13:09:15Z"));
        FootballMatchEntity footballMatchEntity2 = FootballMatchEntity.createNewMatchEntity("Germany", "France");
        footballMatchEntity2.setHomeTeamScore(3);
        footballMatchEntity2.setHomeTeamLastScore(Instant.parse("2025-07-07T13:34:15Z"));
        footballMatchEntity2.setStartedAt(Instant.parse("2025-07-07T13:09:15Z"));
        FootballMatchEntity footballMatchEntity3 = FootballMatchEntity.createNewMatchEntity("Spain", "Poland");
        footballMatchEntity3.setStartedAt(Instant.parse("2025-07-01T13:09:15Z"));
        footballMatchEntity3.setFinishedAt(Instant.parse("2025-07-01T14:00:15Z"));
        FootballMatchEntity footballMatchEntity4 = FootballMatchEntity.createNewMatchEntity("England", "Portugal");
        footballMatchEntity4.setStartedAt(Instant.parse("2025-07-01T15:09:15Z"));
        footballMatchEntity4.setFinishedAt(Instant.parse("2025-07-01T16:00:15Z"));
        scoreboardRepository.databaseMock.setDatabaseMock(new ArrayList<>(List.of(footballMatchEntity1, footballMatchEntity2, footballMatchEntity3, footballMatchEntity4)));
    }

    @Test
    void shouldReturnRunningMatchByContestants() {
        //given
        String homeTeam = "Spain";
        String awayTeam = "Poland";
        //when
        FootballMatchEntity matchEntity = scoreboardRepository.findRunningMatchByContestants(homeTeam, awayTeam);
        //then
        assertThat(matchEntity).isNotNull();
        assertThat(matchEntity.getHomeTeam()).contains("Spain");
        assertThat(matchEntity.getAwayTeam()).contains("Poland");
        assertThat(matchEntity.getFinishedAt()).isNull();
    }

    @Test
    void shouldReturnNullWhenGivenFinishedMatchContestants() {
        //given
        String homeTeam = "England";
        String awayTeam = "Portugal";
        //when
        FootballMatchEntity matchEntity = scoreboardRepository.findRunningMatchByContestants(homeTeam, awayTeam);
        //then
        assertThat(matchEntity).isNull();
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
        Boolean result = scoreboardRepository.updateScore(footballMatch);
        //then
        assertThat(result).isTrue();
        FootballMatchEntity updatedMatch = scoreboardRepository.databaseMock.findRunningMatchByContestants("Spain", "Poland");
        assertThat(updatedMatch).isNotNull();
        assertThat(updatedMatch.getHomeTeamScore()).isEqualTo(0);
        assertThat(updatedMatch.getAwayTeamScore()).isEqualTo(1);
        assertThat(updatedMatch.getHomeTeamLastScore()).isNull();
        assertThat(updatedMatch.getAwayTeamLastScore()).isNotNull();
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
