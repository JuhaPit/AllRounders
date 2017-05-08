package com.fineline.domain;

public class Topten {

	private String name;
	private double eff;
	private String date;
	private String route;
	private int work_days;
	private double avg_eff;

	public Topten(String name, double eff, String date, String route,
			int work_days, double avg_eff) {
		super();
		this.name = name;
		this.eff = eff;
		this.date = date;
		this.route = route;
		this.work_days = work_days;
		this.avg_eff = avg_eff;
	}

	public Topten() {
		super();

	}

	@Override
	public String toString() {
		return "Topten [name=" + name + ", eff=" + eff + ", date=" + date
				+ ", route=" + route + ", work_days=" + work_days
				+ ", avg_eff=" + avg_eff + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getEff() {
		return eff;
	}

	public void setEff(double eff) {
		this.eff = eff;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public int getWork_days() {
		return work_days;
	}

	public void setWork_days(int work_days) {
		this.work_days = work_days;
	}

	public double getAvg_eff() {
		return avg_eff;
	}

	public void setAvg_eff(double avg_eff) {
		this.avg_eff = avg_eff;
	}

}
