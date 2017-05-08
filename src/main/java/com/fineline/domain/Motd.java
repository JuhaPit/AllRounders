package com.fineline.domain;

public class Motd {

	private String message;
	private String last_updated;

	public Motd() {
		super();
		
	}

	public Motd(String message, String last_updated) {
		super();
		this.message = message;
		this.last_updated = last_updated;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}

	@Override
	public String toString() {
		return "Motd [message=" + message + ", last_updated=" + last_updated
				+ "]";
	}

}
