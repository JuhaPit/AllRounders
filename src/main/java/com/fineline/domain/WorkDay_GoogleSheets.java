package com.fineline.domain;

public class WorkDay_GoogleSheets {
	// Index number
	private String date; // 0
	private String name; // 1
	private String car_number; // 2
	private String start_km; // 3
	private String start_time; // 4
	private String end_time; // 5
	private String breaks; // 6
	private String end_km; // 7
	private String evening_hours; // 8
	private String night_hours; // 9
	private String hours_total; // 10
	private double evening_hours_decimal; // 11
	private double night_hours_decimal; // 12
	private double hours_total_decimal; // 13
	private String km_total; // 14
	private String basic_hours; // 15
	private String route; // 16
	private String postnord_deliveries; // 17
	private String postnord_pickups; // 18
	private String postnord_unknown; // 19
	private String postnord_total; // 20
	private String bring_deliveries; // 21
	private String bring_pickups; // 22
	private String bring_dhl_returns; // 23
	private String bring_total; // 24
	private String innight_deliveries; // 25
	private String innight_stops; // 26
	private String extra_info; // 27
	private String total_deliveries; // 28
	private String total_pickups; // 29
	private double effiency; // 30

	public WorkDay_GoogleSheets() {
		super();
	}

	public WorkDay_GoogleSheets(String date, String name, String car_number,
			String start_km, String start_time, String end_time, String breaks,
			String end_km, String evening_hours, String night_hours,
			String hours_total, double evening_hours_decimal,
			double night_hours_decimal, double hours_total_decimal,
			String km_total, String basic_hours, String route,
			String postnord_deliveries, String postnord_pickups,
			String postnord_unknown, String postnord_total,
			String bring_deliveries, String bring_pickups,
			String bring_dhl_returns, String bring_total,
			String innight_deliveries, String innight_stops, String extra_info,
			String total_deliveries, String total_pickups, double effiency) {
		this.date = date;
		this.name = name;
		this.car_number = car_number;
		this.start_km = start_km;
		this.start_time = start_time;
		this.end_time = end_time;
		this.breaks = breaks;
		this.end_km = end_km;
		this.evening_hours = evening_hours;
		this.night_hours = night_hours;
		this.hours_total = hours_total;
		this.evening_hours_decimal = evening_hours_decimal;
		this.night_hours_decimal = night_hours_decimal;
		this.hours_total_decimal = hours_total_decimal;
		this.km_total = km_total;
		this.basic_hours = basic_hours;
		this.route = route;
		this.postnord_deliveries = postnord_deliveries;
		this.postnord_pickups = postnord_pickups;
		this.postnord_unknown = postnord_unknown;
		this.postnord_total = postnord_total;
		this.bring_deliveries = bring_deliveries;
		this.bring_pickups = bring_pickups;
		this.bring_dhl_returns = bring_dhl_returns;
		this.bring_total = bring_total;
		this.innight_deliveries = innight_deliveries;
		this.innight_stops = innight_stops;
		this.extra_info = extra_info;
		this.total_deliveries = total_deliveries;
		this.total_pickups = total_pickups;
		this.effiency = effiency;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCar_number() {
		return car_number;
	}

	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}

	public String getStart_km() {
		return start_km;
	}

	public void setStart_km(String start_km) {
		this.start_km = start_km;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getBreaks() {
		return breaks;
	}

	public void setBreaks(String breaks) {
		this.breaks = breaks;
	}

	public String getEnd_km() {
		return end_km;
	}

	public void setEnd_km(String end_km) {
		this.end_km = end_km;
	}

	public String getEvening_hours() {
		return evening_hours;
	}

	public void setEvening_hours(String evening_hours) {
		this.evening_hours = evening_hours;
	}

	public String getNight_hours() {
		return night_hours;
	}

	public void setNight_hours(String night_hours) {
		this.night_hours = night_hours;
	}

	public String getHours_total() {
		return hours_total;
	}

	public void setHours_total(String hours_total) {
		this.hours_total = hours_total;
	}

	public double getEvening_hours_decimal() {
		return evening_hours_decimal;
	}

	public void setEvening_hours_decimal(double evening_hours_decimal) {
		this.evening_hours_decimal = evening_hours_decimal;
	}

	public double getNight_hours_decimal() {
		return night_hours_decimal;
	}

	public void setNight_hours_decimal(double night_hours_decimal) {
		this.night_hours_decimal = night_hours_decimal;
	}

	public double getHours_total_decimal() {
		return hours_total_decimal;
	}

	public void setHours_total_decimal(double hours_total_decimal) {
		this.hours_total_decimal = hours_total_decimal;
	}

	public String getKm_total() {
		return km_total;
	}

	public void setKm_total(String km_total) {
		this.km_total = km_total;
	}

	public String getBasic_hours() {
		return basic_hours;
	}

	public void setBasic_hours(String basic_hours) {
		this.basic_hours = basic_hours;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getPostnord_deliveries() {
		return postnord_deliveries;
	}

	public void setPostnord_deliveries(String postnord_deliveries) {
		this.postnord_deliveries = postnord_deliveries;
	}

	public String getPostnord_pickups() {
		return postnord_pickups;
	}

	public void setPostnord_pickups(String postnord_pickups) {
		this.postnord_pickups = postnord_pickups;
	}

	public String getPostnord_unknown() {
		return postnord_unknown;
	}

	public void setPostnord_unknown(String postnord_unknown) {
		this.postnord_unknown = postnord_unknown;
	}

	public String getPostnord_total() {
		return postnord_total;
	}

	public void setPostnord_total(String postnord_total) {
		this.postnord_total = postnord_total;
	}

	public String getBring_deliveries() {
		return bring_deliveries;
	}

	public void setBring_deliveries(String bring_deliveries) {
		this.bring_deliveries = bring_deliveries;
	}

	public String getBring_pickups() {
		return bring_pickups;
	}

	public void setBring_pickups(String bring_pickups) {
		this.bring_pickups = bring_pickups;
	}

	public String getBring_dhl_returns() {
		return bring_dhl_returns;
	}

	public void setBring_dhl_returns(String bring_dhl_returns) {
		this.bring_dhl_returns = bring_dhl_returns;
	}

	public String getBring_total() {
		return bring_total;
	}

	public void setBring_total(String bring_total) {
		this.bring_total = bring_total;
	}

	public String getInnight_deliveries() {
		return innight_deliveries;
	}

	public void setInnight_deliveries(String innight_deliveries) {
		this.innight_deliveries = innight_deliveries;
	}

	public String getInnight_stops() {
		return innight_stops;
	}

	public void setInnight_stops(String innight_stops) {
		this.innight_stops = innight_stops;
	}

	public String getExtra_info() {
		return extra_info;
	}

	public void setExtra_info(String extra_info) {
		this.extra_info = extra_info;
	}

	public String getTotal_deliveries() {
		return total_deliveries;
	}

	public void setTotal_deliveries(String total_deliveries) {
		this.total_deliveries = total_deliveries;
	}

	public String getTotal_pickups() {
		return total_pickups;
	}

	public void setTotal_pickups(String total_pickups) {
		this.total_pickups = total_pickups;
	}

	public double getEffiency() {
		return effiency;
	}

	public void setEffiency(double effiency) {
		this.effiency = effiency;
	}

	public String toStringAll() {
		return "WorkDay_GoogleSheets[" + "date='" + date + '\'' + ", name='" + name + '\''
				+ ", car_number='" + car_number + '\'' + ", start_km='"
				+ start_km + '\'' + ", start_time='" + start_time + '\''
				+ ", end_time='" + end_time + '\'' + ", breaks='" + breaks
				+ '\'' + ", end_km='" + end_km + '\'' + ", evening_hours='"
				+ evening_hours + '\'' + ", night_hours='" + night_hours + '\''
				+ ", hours_total='" + hours_total + '\''
				+ ", evening_hours_decimal='" + evening_hours_decimal + '\''
				+ ", night_hours_decimal='" + night_hours_decimal + '\''
				+ ", hours_total_decimal='" + hours_total_decimal + '\''
				+ ", km_total='" + km_total + '\'' + ", basic_hours='"
				+ basic_hours + '\'' + ", route='" + route + '\''
				+ ", postnord_deliveries='" + postnord_deliveries + '\''
				+ ", postnord_pickups='" + postnord_pickups + '\''
				+ ", postnord_unknown='" + postnord_unknown + '\''
				+ ", postnord_total='" + postnord_total + '\''
				+ ", bring_deliveries='" + bring_deliveries + '\''
				+ ", bring_pickups='" + bring_pickups + '\''
				+ ", bring_dhl_returns='" + bring_dhl_returns + '\''
				+ ", bring_total='" + bring_total + '\''
				+ ", innight_deliveries='" + innight_deliveries + '\''
				+ ", innight_stops='" + innight_stops + '\'' + ", extra_info='"
				+ extra_info + '\'' + ", total_deliveries='" + total_deliveries
				+ '\'' + ", total_pickups='" + total_pickups + '\''
				+ ", effiency='" + effiency + '\'' + ']';
	}

	@Override
	public String toString() {
		return "WorkDay_GoogleSheets [date=" + date + ", name=" + name
				+ ", effiency=" + effiency + "]";
	}
	
	
}
