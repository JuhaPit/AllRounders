package com.fineline.domain;

public class SheetsRow {
	
	private String timeStamp;
	private String kuljettaja;
	private String auto;
	private int aloitusKm;
	private String aloitusAika;
	private String lopetusAika;
	private String tauot;
	private int lopetusKm;
	private String reitti;
	private int pJaot;
	private int pNoudot;
	private int pUnknown;
	private int pYhteensa;
	private int bJaot;
	private int bNoudot;
	private int bDhl;
	private int bYhteensa;
	private int iKollit;
	private int iStopit;
	private String lisatiedot;
	
	
	public SheetsRow() {
		super();
	}


	public SheetsRow(String timeStamp, String kuljettaja, String auto,
			int aloitusKm, String aloitusAika, String lopetusAika,
			String tauot, int lopetusKm, String reitti, int pJaot, int pNoudot,
			int pUnknown, int pYhteensa, int bJaot, int bNoudot, int bDhl,
			int bYhteensa, int iKollit, int iStopit, String lisatiedot) {
		super();
		this.timeStamp = timeStamp;
		this.kuljettaja = kuljettaja;
		this.auto = auto;
		this.aloitusKm = aloitusKm;
		this.aloitusAika = aloitusAika;
		this.lopetusAika = lopetusAika;
		this.tauot = tauot;
		this.lopetusKm = lopetusKm;
		this.reitti = reitti;
		this.pJaot = pJaot;
		this.pNoudot = pNoudot;
		this.pUnknown = pUnknown;
		this.pYhteensa = pYhteensa;
		this.bJaot = bJaot;
		this.bNoudot = bNoudot;
		this.bDhl = bDhl;
		this.bYhteensa = bYhteensa;
		this.iKollit = iKollit;
		this.iStopit = iStopit;
		this.lisatiedot = lisatiedot;
	}


	public String getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}


	public String getKuljettaja() {
		return kuljettaja;
	}


	public void setKuljettaja(String kuljettaja) {
		this.kuljettaja = kuljettaja;
	}


	public String getAuto() {
		return auto;
	}


	public void setAuto(String auto) {
		this.auto = auto;
	}


	public int getAloitusKm() {
		return aloitusKm;
	}


	public void setAloitusKm(int aloitusKm) {
		this.aloitusKm = aloitusKm;
	}


	public String getAloitusAika() {
		return aloitusAika;
	}


	public void setAloitusAika(String aloitusAika) {
		this.aloitusAika = aloitusAika;
	}


	public String getLopetusAika() {
		return lopetusAika;
	}


	public void setLopetusAika(String lopetusAika) {
		this.lopetusAika = lopetusAika;
	}


	public String getTauot() {
		return tauot;
	}


	public void setTauot(String tauot) {
		this.tauot = tauot;
	}


	public int getLopetusKm() {
		return lopetusKm;
	}


	public void setLopetusKm(int lopetusKm) {
		this.lopetusKm = lopetusKm;
	}


	public String getReitti() {
		return reitti;
	}


	public void setReitti(String reitti) {
		this.reitti = reitti;
	}


	public int getpJaot() {
		return pJaot;
	}


	public void setpJaot(int pJaot) {
		this.pJaot = pJaot;
	}


	public int getpNoudot() {
		return pNoudot;
	}


	public void setpNoudot(int pNoudot) {
		this.pNoudot = pNoudot;
	}


	public int getpUnknown() {
		return pUnknown;
	}


	public void setpUnknown(int pUnknown) {
		this.pUnknown = pUnknown;
	}


	public int getpYhteensa() {
		return pYhteensa;
	}


	public void setpYhteensa(int pYhteensa) {
		this.pYhteensa = pYhteensa;
	}


	public int getbJaot() {
		return bJaot;
	}


	public void setbJaot(int bJaot) {
		this.bJaot = bJaot;
	}


	public int getbNoudot() {
		return bNoudot;
	}


	public void setbNoudot(int bNoudot) {
		this.bNoudot = bNoudot;
	}


	public int getbDhl() {
		return bDhl;
	}


	public void setbDhl(int bDhl) {
		this.bDhl = bDhl;
	}


	public int getbYhteensa() {
		return bYhteensa;
	}


	public void setbYhteensa(int bYhteensa) {
		this.bYhteensa = bYhteensa;
	}


	public int getiKollit() {
		return iKollit;
	}


	public void setiKollit(int iKollit) {
		this.iKollit = iKollit;
	}


	public int getiStopit() {
		return iStopit;
	}


	public void setiStopit(int iStopit) {
		this.iStopit = iStopit;
	}


	public String getLisatiedot() {
		return lisatiedot;
	}


	public void setLisatiedot(String lisatiedot) {
		this.lisatiedot = lisatiedot;
	}


	@Override
	public String toString() {
		return "SheetsRow [timeStamp=" + timeStamp + ", kuljettaja="
				+ kuljettaja + ", auto=" + auto + ", aloitusKm=" + aloitusKm
				+ ", aloitusAika=" + aloitusAika + ", lopetusAika="
				+ lopetusAika + ", tauot=" + tauot + ", lopetusKm=" + lopetusKm
				+ ", reitti=" + reitti + ", pJaot=" + pJaot + ", pNoudot="
				+ pNoudot + ", pUnknown=" + pUnknown + ", pYhteensa="
				+ pYhteensa + ", bJaot=" + bJaot + ", bNoudot=" + bNoudot
				+ ", bDhl=" + bDhl + ", bYhteensa=" + bYhteensa + ", iKollit="
				+ iKollit + ", iStopit=" + iStopit + ", lisatiedot="
				+ lisatiedot + "]";
	}
	
	
}
