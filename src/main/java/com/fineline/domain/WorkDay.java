package com.fineline.domain;

import java.sql.Date;

public class WorkDay {
	
	private int day_id;
	private Users user_id;
	private Postnord pn_id;
	private Bring b_id;
	private Innight in_id;
	private Date day_date;
	private String day_route;
	private String day_carUser;
	private int day_startKm;
	private String day_startTime;
	private String day_breaksMin;
	private String day_endTime;
	private int day_endKm;
	private String day_addInfo;
	
	public WorkDay(){
		
		super();
	}

	public WorkDay(int day_id, Users user_id, Date day_date, String day_route,
			String day_carUser, int day_startKm, String day_startTime) {
		super();
		this.day_id = day_id;
		this.user_id = user_id;
		this.day_date = day_date;
		this.day_route = day_route;
		this.day_carUser = day_carUser;
		this.day_startKm = day_startKm;
		this.day_startTime = day_startTime;
	}

	public int getDay_id() {
		return day_id;
	}

	public void setDay_id(int day_id) {
		this.day_id = day_id;
	}

	public Users getUser_id() {
		return user_id;
	}

	public void setUser_id(Users user_id) {
		this.user_id = user_id;
	}

	public Postnord getPn_id() {
		return pn_id;
	}

	public void setPn_id(Postnord pn_id) {
		this.pn_id = pn_id;
	}

	public Bring getB_id() {
		return b_id;
	}

	public void setB_id(Bring b_id) {
		this.b_id = b_id;
	}

	public Innight getIn_id() {
		return in_id;
	}

	public void setIn_id(Innight in_id) {
		this.in_id = in_id;
	}

	public Date getDay_date() {
		return day_date;
	}

	public void setDay_date(Date day_date) {
		this.day_date = day_date;
	}

	public String getDay_route() {
		return day_route;
	}

	public void setDay_route(String day_route) {
		this.day_route = day_route;
	}

	public String getDay_carUser() {
		return day_carUser;
	}

	public void setDay_carUser(String day_carUser) {
		this.day_carUser = day_carUser;
	}

	public int getDay_startKm() {
		return day_startKm;
	}

	public void setDay_startKm(int day_startKm) {
		this.day_startKm = day_startKm;
	}

	public String getDay_startTime() {
		return day_startTime;
	}

	public void setDay_startTime(String day_startTime) {
		this.day_startTime = day_startTime;
	}

	public String getDay_breaksMin() {
		return day_breaksMin;
	}

	public void setDay_breaksMin(String day_breaksMin) {
		this.day_breaksMin = day_breaksMin;
	}

	public String getDay_endTime() {
		return day_endTime;
	}

	public void setDay_endTime(String day_endTime) {
		this.day_endTime = day_endTime;
	}

	public int getDay_endKm() {
		return day_endKm;
	}

	public void setDay_endKm(int day_endKm) {
		this.day_endKm = day_endKm;
	}

	public String getDay_addInfo() {
		return day_addInfo;
	}

	public void setDay_addInfo(String day_addInfo) {
		this.day_addInfo = day_addInfo;
	}

	@Override
	public String toString() {
		return "WorkDay [day_id=" + day_id + ", user_id=" + user_id
				+ ", pn_id=" + pn_id + ", b_id=" + b_id + ", in_id=" + in_id
				+ ", day_date=" + day_date + ", day_route=" + day_route
				+ ", day_carUser=" + day_carUser + ", day_startKm="
				+ day_startKm + ", day_startTime=" + day_startTime
				+ ", day_breaksMin=" + day_breaksMin + ", day_endTime="
				+ day_endTime + ", day_endKm=" + day_endKm + ", day_addInfo="
				+ day_addInfo + "]";
	}
	
	

	
	
	
	
	

}
