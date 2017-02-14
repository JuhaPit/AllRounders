package com.fineline.domain;

public class Postnord {
	
	private int pn_id;
	private int pn_jako;
	private int pn_nouto;
	private int pn_unknown;
	private int pn_notime;
	
	public Postnord(){
		
		super();
	}

	public Postnord(int pn_id, int pn_jako, int pn_nouto, int pn_unknown,
			int pn_notime) {
		super();
		this.pn_id = pn_id;
		this.pn_jako = pn_jako;
		this.pn_nouto = pn_nouto;
		this.pn_unknown = pn_unknown;
		this.pn_notime = pn_notime;
	}

	public int getPn_id() {
		return pn_id;
	}

	public void setPn_id(int pn_id) {
		this.pn_id = pn_id;
	}

	public int getPn_jako() {
		return pn_jako;
	}

	public void setPn_jako(int pn_jako) {
		this.pn_jako = pn_jako;
	}

	public int getPn_nouto() {
		return pn_nouto;
	}

	public void setPn_nouto(int pn_nouto) {
		this.pn_nouto = pn_nouto;
	}

	public int getPn_unknown() {
		return pn_unknown;
	}

	public void setPn_unknown(int pn_unknown) {
		this.pn_unknown = pn_unknown;
	}

	public int getPn_notime() {
		return pn_notime;
	}

	public void setPn_notime(int pn_notime) {
		this.pn_notime = pn_notime;
	}

	@Override
	public String toString() {
		return "Postnord [pn_id=" + pn_id + ", pn_jako=" + pn_jako
				+ ", pn_nouto=" + pn_nouto + ", pn_unknown=" + pn_unknown
				+ ", pn_notime=" + pn_notime + "]";
	}
	
	

}
