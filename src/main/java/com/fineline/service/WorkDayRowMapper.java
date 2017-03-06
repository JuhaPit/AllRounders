package com.fineline.service;
/*
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;

import com.fineline.domain.Bring;
import com.fineline.domain.Innight;
import com.fineline.domain.Postnord;
import com.fineline.domain.Users;
import com.fineline.domain.WorkDay;

public class WorkDayRowMapper implements RowMapper<WorkDay> {
	
	public WorkDay mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Hours hours = new Hours();
		WorkDay w = new WorkDay();
		Bring b = new Bring();
		Innight i = new Innight();
		Postnord p = new Postnord();
		Users u = new Users();
		
		u.setId(rs.getInt("user_id"));
		
		w.setDay_id(rs.getInt("day_id"));
		w.setUser_id(u);
		w.setDay_date(rs.getDate("day_date"));
		w.setDay_route(rs.getString("day_route"));
		w.setDay_carUser(rs.getString("day_carUser"));
		w.setDay_startKm(rs.getInt("day_startKm"));
		w.setDay_startTime(rs.getString("day_startTime"));
		w.setDay_breaksMin(rs.getString("day_breaksMin"));
		w.setDay_endTime(rs.getString("day_endTime"));
		w.setDay_endKm(rs.getInt("day_endKm"));
		w.setDay_addInfo(rs.getString("day_addInfo"));
		w.setDay_workHours(hours.calculateWorkHours(w.getDay_startTime(), w.getDay_endTime()));
		w.setDay_eveningHours(hours.CheckIfEveningWork(w.getDay_startTime(), w.getDay_endTime()));
		
		
		b.setB_id(rs.getInt("b_id"));
		b.setB_jako(rs.getInt("b_jako"));
		b.setB_nouto(rs.getInt("b_nouto"));
		b.setB_dhlReturn(rs.getInt("b_dhlReturn"));
		b.setB_notime(rs.getInt("b_notime"));
		w.setB_id(b);
		
		i.setIn_id(rs.getInt("in_id"));
		i.setIn_kollit(rs.getInt("in_kollit"));
		i.setIn_stopit(rs.getInt("in_stopit"));
		w.setIn_id(i);
		
		p.setPn_id(rs.getInt("pn_id"));
		p.setPn_jako(rs.getInt("pn_jako"));
		p.setPn_nouto(rs.getInt("pn_nouto"));
		p.setPn_unknown(rs.getInt("pn_unknown"));
		p.setPn_notime(rs.getInt("pn_notime"));
		w.setPn_id(p);
		
		
		return w;
	}
	
}
*/