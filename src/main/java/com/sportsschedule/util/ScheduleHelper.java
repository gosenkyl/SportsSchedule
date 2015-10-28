package com.sportsschedule.util;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.net.URL;
import java.net.URLConnection;


public final class ScheduleHelper {


    private static String PREGAME = "Pregame";
    private static String HALFTIME = "Halftime";
    private static String OVERTIME= "Overtime";
    private static String FINAL = "Final";
    private static String FINAL_OT = "Final Overtime";

    public static void appendScheduleToTeams(String league, LinkedHashMap<String, TeamObj> teams){
        if("NFL".equals(league)){
            loadNFLSchedule(teams);
        }

    }


    private static void loadNFLSchedule(LinkedHashMap<String, TeamObj> teams){
        URL url;
        URLConnection connection;

        try {
            // Current NFL week
            url = new URL("http://www.nfl.com/liveupdate/scorestrip/ss.xml");
            connection = url.openConnection();
            Document curDoc = parseXML(connection.getInputStream());
            NodeList curSchedule = curDoc.getElementsByTagName("gms");

            NamedNodeMap curNodeMap = curSchedule.item(0).getAttributes();

            String curWeek = curNodeMap.getNamedItem("w").getNodeValue();
            String curYear = curNodeMap.getNamedItem("y").getNodeValue();

            System.out.println("Current Week: " + curWeek + " Year: " + curYear);

            // "P" = "Pregame"- "H" = "Halftime" - "5" = "Overtime" - "F" = "Final" - "FO" = "Final Overtime";

            int week = 1;
            while(true){
                url = new URL("http://www.nfl.com/ajax/scorestrip?season=" + curYear + "&seasonType=REG&week=" + week);
                connection = url.openConnection();

                Document doc = parseXML(connection.getInputStream());
                Node schedule = doc.getElementsByTagName("ss").item(0);
                Node games = schedule.getFirstChild();

                if(games == null){
                    // No more weeks left
                    break;
                }

                System.out.println("WEEK #"+week);

                NodeList descNodes = games.getChildNodes();

                // Loop through the games
                for (int i = 0; i < descNodes.getLength(); i++) {
                    Node curNode = descNodes.item(i);
                    NamedNodeMap nodeMap = curNode.getAttributes();

                    // Get home team for this game
                    TeamObj homeTeam = teams.get(nodeMap.getNamedItem("h").getNodeValue());
                    // Get away team for this game
                    TeamObj awayTeam = teams.get(nodeMap.getNamedItem("v").getNodeValue());

                    String day = nodeMap.getNamedItem("d").getNodeValue();
                    String time = nodeMap.getNamedItem("t").getNodeValue();

                    GameObj homeGame = new GameObj();

                    homeGame.setDay(day);
                    homeGame.setTime(time);
                    homeGame.setOpponentCity(awayTeam.getCity());
                    homeGame.setOpponentMascot(awayTeam.getMascot());

                    homeTeam.getSchedule().add(homeGame);

                    GameObj awayGame = new GameObj();

                    awayGame.setDay(day);
                    awayGame.setTime(time);
                    awayGame.setOpponentCity(homeTeam.getCity());
                    awayGame.setOpponentMascot(homeTeam.getMascot());

                    awayTeam.getSchedule().add(awayGame);

                    System.out.println(homeGame.getOpponentMascot() + " vs " + awayGame.getOpponentMascot());
                }

                // If list size isn't the week size, team has a bye week
                for(TeamObj team : teams.values()){
                    if(team.getSchedule().size() < week){
                        GameObj game = new GameObj();
                        game.setOpponentMascot("Bye");
                        team.getSchedule().add(game);
                    }
                }

                week++;
            }




            /*url = new URL("http://www.nfl.com/ajax/scorestrip?season=2015&seasonType=REG&week=1");
            connection = url.openConnection();

            Document doc = parseXML(connection.getInputStream());
            Node schedule = doc.getElementsByTagName("ss").item(0);
            Node games = schedule.getFirstChild();

            NodeList descNodes = games.getChildNodes();

            System.out.println("Found schedule!");

            for (int i = 0; i < descNodes.getLength(); i++) {
                Node curNode = descNodes.item(i);
                NamedNodeMap nodeMap = curNode.getAttributes();
                System.out.println(nodeMap.getNamedItem("h").getNodeValue());
            }*/


        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private static Document parseXML(InputStream stream) throws Exception {

        DocumentBuilderFactory objDocumentBuilderFactory;
        DocumentBuilder objDocumentBuilder;
        Document doc;
        try {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);
        }
        catch(Exception ex) {
            throw ex;
        }

        return doc;
    }

}
