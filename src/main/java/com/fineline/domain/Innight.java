package com.fineline.domain;

public class Innight {
	
	private int in_id;
	private int in_kollit;
	private int in_stopit;
	
	public Innight(){
		
		super();
	}

	public Innight(int in_id, int in_kollit, int in_stopit) {
		super();
		this.in_id = in_id;
		this.in_kollit = in_kollit;
		this.in_stopit = in_stopit;
	}

	public int getIn_id() {
		return in_id;
	}

	public void setIn_id(int in_id) {
		this.in_id = in_id;
	}

	public int getIn_kollit() {
		return in_kollit;
	}

	public void setIn_kollit(int in_kollit) {
		this.in_kollit = in_kollit;
	}

	public int getIn_stopit() {
		return in_stopit;
	}

	public void setIn_stopit(int in_stopit) {
		this.in_stopit = in_stopit;
	}

	@Override
	public String toString() {
		return "Innight [in_id=" + in_id + ", in_kollit=" + in_kollit
				+ ", in_stopit=" + in_stopit + "]";
	}
	
	

}
