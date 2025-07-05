package com.scoreboard.adapter.outdb.mapper;

import com.scoreboard.adapter.outdb.entity.FootballMatchEntity;
import com.scoreboard.core.domain.FootballMatch;

public class FootballMatchMapper {

    public static FootballMatch map(FootballMatchEntity matchEntity) {
        return new FootballMatch(matchEntity.getHomeTeam(), matchEntity.getAwayTeam(), matchEntity.getHomeTeamScore(),
                matchEntity.getAwayTeamScore(), matchEntity.getStartedAt(), matchEntity.getFinishedAt());
    }
}
