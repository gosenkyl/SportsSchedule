package com.sportsschedule.util;

import com.sportsschedule.service.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class TeamHolder {

    // Our main maps of teams
    private LinkedHashMap<String, TeamObj> nflMap;
    private LinkedHashMap<String, TeamObj> mlbMap;
    private LinkedHashMap<String, TeamObj> nbaMap;
    private LinkedHashMap<String, TeamObj> nhlMap;

    @Autowired
    @Resource(name="sqlProperties")
    private Properties sqlProperties;

    @Autowired
    DBUtil dbUtil;

    @Autowired
    public void setTeamMap(){
        constructMap("NFL");
        constructMap("MLB");
        //constructMap("NBA");
        //constructMap("NHL");
    }

    private void constructMap(String league){
        List<Map<String, Object>> teamList = dbUtil.getList(sqlProperties.getProperty("GET_TEAMS_BY_SPORT"), new Object[]{league});

        LinkedHashMap<String, TeamObj> teams = new LinkedHashMap<String, TeamObj>();

        for(Map map : teamList){
            TeamObj team = new TeamObj();

            team.setTeamId((Integer) map.get("teamId"));
            team.setCity((String) map.get("city"));
            team.setMascot((String) map.get("mascot"));
            team.setPrimaryColor((String) map.get("primaryColor"));
            team.setSecondaryColor((String) map.get("secondaryColor"));
            if(map.get("lonLocation") != null){
                team.setLonLocation((Double) map.get("lonLocation"));
            }
            if(map.get("latLocation") != null){
                team.setLatLocation((Double) map.get("latLocation"));
            }

            if(map.get("logoURL") != null){
                String logoURL = (String) map.get("logoURL");

                if("NFL".equals(league)) team.setLogoURL("http://a.espncdn.com/combiner/i?img=/i/teamlogos/nfl/500/"+logoURL+".png&h=150&w=150");
                else if("MLB".equals(league)) team.setLogoURL("http://a.espncdn.com/combiner/i?img=/i/teamlogos/mlb/500/"+logoURL+".png&h=150&w=150");
                else if("NBA".equals(league)) team.setLogoURL("");
                else if("NHL".equals(league)) team.setLogoURL("");
            }

            String teamAbbr = (String) map.get("teamAbbr");
            team.setTeamAbbr(teamAbbr);


            teams.put(teamAbbr, team);
        }

        ScheduleHelper.appendScheduleToTeams(league, teams);

        if("NFL".equals(league)) nflMap = teams;
        else if("MLB".equals(league)) mlbMap = teams;
        else if("NBA".equals(league)) nbaMap = teams;
        else if("NHL".equals(league)) nhlMap = teams;

    }

    public LinkedHashMap<String, TeamObj> getNflMap() {
        return nflMap;
    }

    public LinkedHashMap<String, TeamObj> getMlbMap() {
        return mlbMap;
    }

    public LinkedHashMap<String, TeamObj> getNbaMap() {
        return nbaMap;
    }

    public LinkedHashMap<String, TeamObj> getNhlMap() {
        return nhlMap;
    }
}
