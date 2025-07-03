package com.scoreboard.adapter.outdb.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FootballMatchEntityTest {

    @Test
    void createNewFootballMatchEntity(){
        //given
        String homeTeam = "Germany";
        String awayTeam = "Portugal";
        //when
        FootballMatchEntity matchEntity = FootballMatchEntity.createNewMatchEntity(homeTeam, awayTeam);
        //then
        assertThat(matchEntity).isNotNull();
        assertThat(matchEntity.getHomeTeam()).isEqualTo(homeTeam);
        assertThat(matchEntity.getAwayTeam()).isEqualTo(awayTeam);
        assertThat(matchEntity.getHomeTeamScore()).isEqualTo(0);
        assertThat(matchEntity.getAwayTeamScore()).isEqualTo(0);
        assertThat(matchEntity.getCreatedAt()).isNotNull();
        assertThat(matchEntity.getFinishedAt()).isNull();
        assertThat(matchEntity.getAwayTeamLastScore()).isNull();
        assertThat(matchEntity.getHomeTeamLastScore()).isNull();
    }
}
