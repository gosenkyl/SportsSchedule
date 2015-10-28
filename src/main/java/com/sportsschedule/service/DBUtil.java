package com.sportsschedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class DBUtil {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource myDataSource){
        this.jdbcTemplate = new JdbcTemplate(myDataSource);
    }

    public List<Map<String, Object>> getList(String query, Object[] params){
        return this.jdbcTemplate.queryForList(query, params);
    }

    public <T> List<T> getListRowMapper(String query, Class<T> type){
        return this.jdbcTemplate.query(query, new BeanPropertyRowMapper<T>(type));
    }

}
