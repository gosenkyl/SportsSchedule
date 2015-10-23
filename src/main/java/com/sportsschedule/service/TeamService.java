package com.sportsschedule.service;

import com.sportsschedule.dto.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeamService {

    List<Team> getAllTeams();
}
