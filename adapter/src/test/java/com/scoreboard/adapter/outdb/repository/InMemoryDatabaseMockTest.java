package com.scoreboard.adapter.outdb.repository;

import com.scoreboard.adapter.outdb.entity.FootballMatchEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryDatabaseMockTest {

    InMemoryDatabaseMock databaseMock = new InMemoryDatabaseMock();

    @BeforeEach
    void setUpDb() {
        String homeTeam = "Germany";
        String awayTeam = "Poland";
        FootballMatchEntity footballMatchEntity1 = FootballMatchEntity.createNewMatchEntity(homeTeam, awayTeam);
        footballMatchEntity1.setStartedAt(Instant.parse("2025-07-02T13:09:15Z"));
        FootballMatchEntity footballMatchEntity2 = FootballMatchEntity.createNewMatchEntity(homeTeam, awayTeam);
        footballMatchEntity2.setStartedAt(Instant.parse("2025-07-03T13:09:15Z"));
        footballMatchEntity2.setFinishedAt(Instant.parse("2025-07-04T13:09:15Z"));
        databaseMock.setDatabaseMock(new ArrayList<>(List.of(footballMatchEntity1, footballMatchEntity2)));
    }

    @AfterEach
    void cleanUpDb() {
        databaseMock.cleanUpDb();
    }

    @Test
    void shouldFindRunningMatchByContestants() {
        // given
        String homeTeam = "Germany";
        String awayTeam = "Poland";
        // when
        Optional<FootballMatchEntity> result = databaseMock.findRunningMatchByContestants(homeTeam, awayTeam);
        // then
        assertThat(result.isPresent()).isTrue();
        assertNewMatchEntity(result.get(), homeTeam, awayTeam);
    }

    @Test
    void shouldFindRunningMatchByContestant() {
        // given
        String contestant = "Poland";
        // when
        Optional<FootballMatchEntity> result = databaseMock.findRunningMatchByContestant(contestant);
        // then
        assertThat(result.isPresent()).isTrue();
        assertNewMatchEntity(result.get(), "Germany", contestant);
    }

    @Test
    void shouldStartNewMatch() {
        // given
        String homeTeam = "Spain";
        String awayTeam = "England";
        // when
        FootballMatchEntity result = databaseMock.startNewMatch(homeTeam, awayTeam);
        // then
        assertNewMatchEntity(result, homeTeam, awayTeam);
        assertThat(databaseMock.getSummary().size()).isEqualTo(3);
    }

    @Test
    void shouldUpdateScore() {
        // given
        String homeTeam = "Germany";
        String awayTeam = "Poland";
        // when
        Optional<FootballMatchEntity> result = databaseMock.updateScore(homeTeam, awayTeam, 1, 0);
        // then
        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(databaseMock.getSummary().getFirst());
        assertThat(result.get().getHomeTeamScore()).isEqualTo(1);
        assertThat(result.get().getAwayTeamScore()).isEqualTo(0);
        assertThat(result.get().getHomeTeamLastScore()).isNotNull();
        assertThat(result.get().getAwayTeamLastScore()).isNull();
    }

    @Test
    void shouldNotUpdateScoreOfNonExistingMatch() {
        // given
        String homeTeam = "Croatia";
        String awayTeam = "Argentina";
        // when
        Optional<FootballMatchEntity> result = databaseMock.updateScore(homeTeam, awayTeam, 1, 0);
        // then
        assertThat(result).isEmpty();
    }

    @Test
    void shouldFinishMatch() {
        // given
        String homeTeam = "Germany";
        String awayTeam = "Poland";
        // when
        Boolean result = databaseMock.finishMatch(homeTeam, awayTeam);
        // then
        assertThat(result).isTrue();
        assertThat(databaseMock.getSummary().getFirst().getFinishedAt()).isNotNull();
    }

    private void assertNewMatchEntity(FootballMatchEntity result, String homeTeam, String awayTeam) {
        assertThat(result).isNotNull();
        assertThat(result.getHomeTeam()).isEqualTo(homeTeam);
        assertThat(result.getAwayTeam()).isEqualTo(awayTeam);
        assertThat(result.getHomeTeamScore()).isEqualTo(0);
        assertThat(result.getAwayTeamScore()).isEqualTo(0);
        assertThat(result.getStartedAt()).isNotNull();
        assertThat(result.getFinishedAt()).isNull();
        assertThat(result.getAwayTeamLastScore()).isNull();
        assertThat(result.getHomeTeamLastScore()).isNull();
    }
}
