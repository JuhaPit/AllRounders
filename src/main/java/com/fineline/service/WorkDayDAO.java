package com.fineline.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fineline.domain.WorkDay;


@Repository
public class WorkDayDAO {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<WorkDay> listAllWorkDays() {

		String sql = "SELECT * FROM WorkDay NATURAL JOIN Postnord NATURAL JOIN Bring NATURAL JOIN Innight;";
		RowMapper<WorkDay> mapper = new WorkDayRowMapper();
		List<WorkDay> workdays = jdbcTemplate.query(sql, mapper);

		return workdays;
	}
}