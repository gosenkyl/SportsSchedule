package com.sportsschedule.controller;

import com.sportsschedule.service.DBUtil;
import com.sportsschedule.service.TeamService;
import com.sportsschedule.util.TeamHolder;
import com.sportsschedule.util.TeamObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Properties;

@Controller
public class MainController {

	@Autowired
	private TeamService teamService;

	@Autowired
	private DBUtil dbUtil;

	@Autowired
	@Resource(name="sqlProperties")
	private Properties sqlProperties;

	@Autowired
	private TeamHolder teamHolder;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String init(){
		return "teams";
	}

	@RequestMapping(value="/getAllTeams", method = RequestMethod.GET)
	public ResponseEntity<LinkedHashMap<String, TeamObj>> getAllTeams(@RequestParam("league") String league) {


		LinkedHashMap<String, TeamObj> map = new LinkedHashMap<String, TeamObj>();

		if(league.equals("NFL")) map = teamHolder.getNflMap();
		else if(league.equals("MLB")) map = teamHolder.getMlbMap();
		else if(league.equals("NBA")) map = teamHolder.getNbaMap();
		else if(league.equals("NHL")) map = teamHolder.getNhlMap();

		return new ResponseEntity<LinkedHashMap<String, TeamObj>>(map, HttpStatus.OK);
	}
}