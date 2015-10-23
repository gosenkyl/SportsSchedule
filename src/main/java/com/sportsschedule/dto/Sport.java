package com.sportsschedule.dto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="SPORT")
public class Sport {

    @Id
    @Column(name="sport_id")
    @GeneratedValue
    private Integer sportId;

    @Column(name="sport_code")
    private String sportCode;

    @Column(name="sport_name")
    private String sportName;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="sport_id")
    private List<Team> teamList;

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

    public String getSportCode() {
        return sportCode;
    }

    public void setSportCode(String sportCode) {
        this.sportCode = sportCode;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }
}
