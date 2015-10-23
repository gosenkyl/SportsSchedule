package com.sportsschedule.controller;

import com.sportsschedule.service.DBUtil;
import com.sportsschedule.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
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

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String init(){
		return "teams";
	}

	@RequestMapping(value="/getAllTeams", method = RequestMethod.GET)
	public ResponseEntity<List<Map<String, Object>>> getAllTeams() {

		List<Map<String, Object>> result = dbUtil.getList(sqlProperties.getProperty("GET_TEAMS_BY_SPORT"));

		return new ResponseEntity<List<Map<String, Object>>>(result, HttpStatus.OK);
	}
}