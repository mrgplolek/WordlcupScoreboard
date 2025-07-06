package com.scoreboard.adapter.outdb.repository;


import com.scoreboard.adapter.outdb.entity.FootballMatchEntity;
import com.scoreboard.core.domain.FootballMatch;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class ScoreboardRepositoryTest {

    static ScoreboardRepository scoreboardRepository = ScoreboardRepository.getInstance();

    @BeforeAll
    static void setUpDatabaseMock() {
        scoreboardRepository.databaseMock = new InMemoryDatabaseMock();
        scoreboardRepository.setupTestData();
    }

    @AfterAll
    static void cleanUp() {
        scoreboardRepository.cleanUpDb();
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
    void shouldReturnRunningMatchByContestant() {
        //given
        String contestant = "Spain";
        //when
        FootballMatchEntity matchEntity = scoreboardRepository.findRunningMatchByContestant(contestant);
        //then
        assertThat(matchEntity).isNotNull();
        assertThat(matchEntity.getHomeTeam()).contains("Spain");
        assertThat(matchEntity.getAwayTeam()).contains("Poland");
        assertThat(matchEntity.getFinishedAt()).isNull();
    }

    @Test
    void shouldReturnNullWhenGivenFinishedMatchContestant() {
        //given
        String contestant = "Sweden";
        //when
        FootballMatchEntity matchEntity = scoreboardRepository.findRunningMatchByContestant(contestant);
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
