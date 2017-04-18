package com.fineline.domain;

public class Topten {
	
	private String name;
	private double eff;
	private String date;
	private String route;
	
	public Topten() {
		super();
	}

	public Topten(String name, double eff, String date, String route) {
		super();
		this.name = name;
		this.eff = eff;
		this.date = date;
		this.route = route;
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

	@Override
	public String toString() {
		return "Topten [name=" + name + ", eff=" + eff + ", date=" + date
				+ ", route=" + route + "]";
	}

}
