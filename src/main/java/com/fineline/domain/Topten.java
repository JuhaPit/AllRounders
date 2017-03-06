package com.fineline.domain;

public class Topten {
	
	private String name;
	private double eff;
	private String date;
	
	public Topten() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Topten(String name, double eff, String date) {
		super();
		this.name = name;
		this.eff = eff;
		this.date = date;
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
	
	
	@Override
	public String toString() {
		return "Topten [name=" + name + ", eff=" + eff + ", date=" + date + "]";
	}
	

}
