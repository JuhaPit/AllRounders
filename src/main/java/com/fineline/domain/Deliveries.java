package com.fineline.domain;

public class Deliveries {

	private int postnord_total;
	private int postnord_pickups;
	private int postnord_deliveries;
	private int postnord_unknown;

	private int bring_deliveries;
	private int bring_total;
	private int bring_pickups;
	private int dhl_returns;

	private int innight_deliveries;
	private int innight_stops;

	private int total;
	private int total_pickups;

	public Deliveries() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Deliveries(int postnord_total, int postnord_pickups,
			int postnord_deliveries, int postnord_unknown,
			int bring_deliveries, int bring_total, int bring_pickups,
			int dhl_returns, int innight_deliveries, int innight_stops,
			int total, int total_pickups) {
		super();
		this.postnord_total = postnord_total;
		this.postnord_pickups = postnord_pickups;
		this.postnord_deliveries = postnord_deliveries;
		this.postnord_unknown = postnord_unknown;
		this.bring_deliveries = bring_deliveries;
		this.bring_total = bring_total;
		this.bring_pickups = bring_pickups;
		this.dhl_returns = dhl_returns;
		this.innight_deliveries = innight_deliveries;
		this.innight_stops = innight_stops;
		this.total = total;
		this.total_pickups = total_pickups;
	}

	public int getPostnord_total() {
		return postnord_total;
	}

	public void setPostnord_total(int postnord_total) {
		this.postnord_total = postnord_total;
	}

	public int getPostnord_pickups() {
		return postnord_pickups;
	}

	public void setPostnord_pickups(int postnord_pickups) {
		this.postnord_pickups = postnord_pickups;
	}

	public int getPostnord_deliveries() {
		return postnord_deliveries;
	}

	public void setPostnord_deliveries(int postnord_deliveries) {
		this.postnord_deliveries = postnord_deliveries;
	}

	public int getPostnord_unknown() {
		return postnord_unknown;
	}

	public void setPostnord_unknown(int postnord_unknown) {
		this.postnord_unknown = postnord_unknown;
	}

	public int getBring_deliveries() {
		return bring_deliveries;
	}

	public void setBring_deliveries(int bring_deliveries) {
		this.bring_deliveries = bring_deliveries;
	}

	public int getBring_total() {
		return bring_total;
	}

	public void setBring_total(int bring_total) {
		this.bring_total = bring_total;
	}

	public int getBring_pickups() {
		return bring_pickups;
	}

	public void setBring_pickups(int bring_pickups) {
		this.bring_pickups = bring_pickups;
	}

	public int getDhl_returns() {
		return dhl_returns;
	}

	public void setDhl_returns(int dhl_returns) {
		this.dhl_returns = dhl_returns;
	}

	public int getInnight_deliveries() {
		return innight_deliveries;
	}

	public void setInnight_deliveries(int innight_deliveries) {
		this.innight_deliveries = innight_deliveries;
	}

	public int getInnight_stops() {
		return innight_stops;
	}

	public void setInnight_stops(int innight_stops) {
		this.innight_stops = innight_stops;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotal_pickups() {
		return total_pickups;
	}

	public void setTotal_pickups(int total_pickups) {
		this.total_pickups = total_pickups;
	}

	@Override
	public String toString() {
		return "Deliveries [postnord_total=" + postnord_total
				+ ", postnord_pickups=" + postnord_pickups
				+ ", postnord_deliveries=" + postnord_deliveries
				+ ", postnord_unknown=" + postnord_unknown
				+ ", bring_deliveries=" + bring_deliveries + ", bring_total="
				+ bring_total + ", bring_pickups=" + bring_pickups
				+ ", dhl_returns=" + dhl_returns + ", innight_deliveries="
				+ innight_deliveries + ", innight_stops=" + innight_stops
				+ ", total=" + total + ", total_pickups=" + total_pickups + "]";
	}

}
