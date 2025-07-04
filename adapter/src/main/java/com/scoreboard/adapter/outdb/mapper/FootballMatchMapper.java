package com.scoreboard.adapter.outdb.mapper;

import com.scoreboard.adapter.outdb.entity.FootballMatchEntity;
import com.scoreboard.core.domain.FootballMatch;

public class FootballMatchMapper {

    public FootballMatch map(FootballMatchEntity matchEntity) {
        FootballMatch match = new FootballMatch(matchEntity.getHomeTeam(), matchEntity.getAwayTeam(), matchEntity.getHomeTeamScore(),
                matchEntity.getAwayTeamScore(), matchEntity.getStartedAt(), matchEntity.getFinishedAt());
        return match;
    }
}
