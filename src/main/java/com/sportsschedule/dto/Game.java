package com.sportsschedule.dto;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="GAME")
public class Game {

    @Id
    @GeneratedValue
    @Column(name="game_id")
    private Integer gameId;

    @Column(name="year_code")
    private Integer yearCode;

    @Column(name="game_increment")
    private Integer gameIncrement;

    @Column(name="home_team_id")
    private Integer homeTeamId;

    @Column(name="away_team_id")
    private Integer awayTeamId;

    @Column(name="game_time")
    private Time gameTime;

    @Column(name="game_day")
    private String gameDay;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getYearCode() {
        return yearCode;
    }

    public void setYearCode(Integer yearCode) {
        this.yearCode = yearCode;
    }

    public Integer getGameIncrement() {
        return gameIncrement;
    }

    public void setGameIncrement(Integer gameIncrement) {
        this.gameIncrement = gameIncrement;
    }

    public Integer getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Integer homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Integer getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Integer awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public Time getGameTime() {
        return gameTime;
    }

    public void setGameTime(Time gameTime) {
        this.gameTime = gameTime;
    }

    public String getGameDay() {
        return gameDay;
    }

    public void setGameDay(String gameDay) {
        this.gameDay = gameDay;
    }
}
