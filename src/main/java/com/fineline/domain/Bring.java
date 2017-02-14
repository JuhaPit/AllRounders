package com.fineline.domain;

public class Bring {
	
	private int b_id;
	private int b_jako;
	private int b_nouto;
	private int b_dhlReturn;
	private int b_notime;
	
	public Bring(){
		
		super();
	}

	public Bring(int b_id, int b_jako, int b_nouto, int b_dhlReturn,
			int b_notime) {
		super();
		this.b_id = b_id;
		this.b_jako = b_jako;
		this.b_nouto = b_nouto;
		this.b_dhlReturn = b_dhlReturn;
		this.b_notime = b_notime;
	}

	public int getB_id() {
		return b_id;
	}

	public void setB_id(int b_id) {
		this.b_id = b_id;
	}

	public int getB_jako() {
		return b_jako;
	}

	public void setB_jako(int b_jako) {
		this.b_jako = b_jako;
	}

	public int getB_nouto() {
		return b_nouto;
	}

	public void setB_nouto(int b_nouto) {
		this.b_nouto = b_nouto;
	}

	public int getB_dhlReturn() {
		return b_dhlReturn;
	}

	public void setB_dhlReturn(int b_dhlReturn) {
		this.b_dhlReturn = b_dhlReturn;
	}

	public int getB_notime() {
		return b_notime;
	}

	public void setB_notime(int b_notime) {
		this.b_notime = b_notime;
	}

	@Override
	public String toString() {
		return "Bring [b_id=" + b_id + ", b_jako=" + b_jako + ", b_nouto="
				+ b_nouto + ", b_dhlReturn=" + b_dhlReturn + ", b_notime="
				+ b_notime + "]";
	}
	
	

}
