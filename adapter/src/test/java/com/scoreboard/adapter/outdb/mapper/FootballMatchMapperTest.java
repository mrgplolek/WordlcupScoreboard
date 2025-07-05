package com.scoreboard.adapter.outdb.mapper;

import com.scoreboard.adapter.outdb.entity.FootballMatchEntity;
import com.scoreboard.core.domain.FootballMatch;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class FootballMatchMapperTest {

    @Test
    void shouldMapEntityIntoDomainObject() {
        //given
        String homeTeam = "Germany";
        String awayTeam = "Portugal";
        FootballMatchEntity entity = FootballMatchEntity.createNewMatchEntity(homeTeam, awayTeam);
        entity.setStartedAt(Instant.parse("2025-07-01T13:09:15Z"));
        entity.setFinishedAt(Instant.parse("2025-07-01T14:01:15Z"));
        entity.setHomeTeamLastScore(Instant.parse("2025-07-01T13:40:15Z"));
        entity.setAwayTeamLastScore(Instant.parse("2025-07-01T13:55:15Z"));
        entity.setHomeTeamScore(3);
        entity.setAwayTeamScore(4);
        //when
        FootballMatch domainObject =  FootballMatchMapper.map(entity);
        //then
        assertThat(domainObject.getHomeTeam()).isEqualTo(homeTeam);
        assertThat(domainObject.getAwayTeam()).isEqualTo(awayTeam);
        assertThat(domainObject.getHomeTeamScore()).isEqualTo(3);
        assertThat(domainObject.getAwayTeamScore()).isEqualTo(4);
        assertThat(domainObject.getFinishedAt()).isEqualTo("2025-07-01T14:01:15Z");
        assertThat(domainObject.getStartedAt()).isEqualTo("2025-07-01T13:09:15Z");
    }
}
