package com.sportsschedule.service;

import com.sportsschedule.dao.GenericDAO;
import com.sportsschedule.dto.Sport;
import com.sportsschedule.dto.Team;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional(rollbackOn=Exception.class)
public class TeamServiceImpl implements TeamService {

    @Autowired
    private GenericDAO genericDAO;

    @Override
    public List<Team> getAllTeams() {
        List<Team> teamList = new ArrayList<Team>();



        //teamList = sport.getTeamList();

        /*TeamObj teamA = new TeamObj();
        teamA.setId(1L);
        teamA.setCity("Arizona");
        teamA.setMascot("Cardinals");
        teamA.setPrimaryColor("#97233F"); // Red
        teamA.setSecondaryColor("#FFFFFF"); // White
        teamA.setLogoLink("http://a.espncdn.com/combiner/i?img=/i/teamlogos/nfl/500/ari.png&h=150&w=150");

        TeamObj teamB = new TeamObj();
        teamB.setId(2L);
        teamB.setCity("Atlanta");
        teamB.setMascot("Falcons");
        teamB.setPrimaryColor("#000000"); // Black
        teamB.setSecondaryColor("#A71930"); // Red
        teamB.setLogoLink("http://a.espncdn.com/combiner/i?img=/i/teamlogos/nfl/500/atl.png&h=150&w=150");

        TeamObj teamC = new TeamObj();
        teamC.setId(3L);
        teamC.setCity("Baltimore");
        teamC.setMascot("Ravens");
        teamC.setPrimaryColor("#241773"); // Purple
        teamC.setSecondaryColor("#000000"); // Black
        teamC.setLogoLink("http://a.espncdn.com/combiner/i?img=/i/teamlogos/nfl/500/bal.png&h=150&w=150");

        TeamObj teamD = new TeamObj();
        teamD.setId(4L);
        teamD.setCity("Buffalo");
        teamD.setMascot("Bills");
        teamD.setPrimaryColor("#00338D"); // Blue
        teamD.setSecondaryColor("#C60C30"); // Red
        teamD.setLogoLink("http://a.espncdn.com/combiner/i?img=/i/teamlogos/nfl/500/buf.png&h=150&w=150");

        TeamObj teamE = new TeamObj();
        teamE.setId(5L);
        teamE.setCity("Carolina");
        teamE.setMascot("Panthers");
        teamE.setPrimaryColor("#0085CA"); // Blue
        teamE.setSecondaryColor("#000000"); // Black
        teamE.setLogoLink("http://a.espncdn.com/combiner/i?img=/i/teamlogos/nfl/500/car.png&h=150&w=150");

        teamList.add(teamA);
        teamList.add(teamB);
        teamList.add(teamC);
        teamList.add(teamD);
        teamList.add(teamE);*/

        return teamList;
    }
}
