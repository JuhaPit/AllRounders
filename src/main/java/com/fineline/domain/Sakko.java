package com.fineline.domain;

public class Sakko {
	
	String sakko_count;

	public Sakko() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sakko(String sakko_count) {
		super();
		this.sakko_count = sakko_count;
	}

	public String getSakko_count() {
		return sakko_count;
	}

	public void setSakko_count(String sakko_count) {
		this.sakko_count = sakko_count;
	}

	@Override
	public String toString() {
		return "Sakko [sakko_count=" + sakko_count + "]";
	}
	
	

}
