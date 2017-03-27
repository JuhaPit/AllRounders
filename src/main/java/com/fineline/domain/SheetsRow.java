package com.fineline.domain;

public class SheetsRow {
	
	private String timeStamp;
	private String kuljettaja;
	private String auto;
	private String aloitusKm;
	private String aloitusAika;
	private String lopetusAika;
	private String tauot;
	private String lopetusKm;
	private String reitti;
	private String pJaot;
	private String pNoudot;
	private String pUnknown;
	private String pYhteensa;
	private String bJaot;
	private String bNoudot;
	private String bDhl;
	private String bYhteensa;
	private String iKollit;
	private String iStopit;
	private String lisatiedot;
	
	
	public SheetsRow() {
		super();
	}


	public SheetsRow(String timeStamp, String kuljettaja, String auto,
			String aloitusKm, String aloitusAika, String lopetusAika,
			String tauot, String lopetusKm, String reitti, String pJaot,
			String pNoudot, String pUnknown, String pYhteensa, String bJaot,
			String bNoudot, String bDhl, String bYhteensa, String iKollit,
			String iStopit, String lisatiedot) {
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


	public String getAloitusKm() {
		return aloitusKm;
	}


	public void setAloitusKm(String aloitusKm) {
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


	public String getLopetusKm() {
		return lopetusKm;
	}


	public void setLopetusKm(String lopetusKm) {
		this.lopetusKm = lopetusKm;
	}


	public String getReitti() {
		return reitti;
	}


	public void setReitti(String reitti) {
		this.reitti = reitti;
	}


	public String getpJaot() {
		return pJaot;
	}


	public void setpJaot(String pJaot) {
		this.pJaot = pJaot;
	}


	public String getpNoudot() {
		return pNoudot;
	}


	public void setpNoudot(String pNoudot) {
		this.pNoudot = pNoudot;
	}


	public String getpUnknown() {
		return pUnknown;
	}


	public void setpUnknown(String pUnknown) {
		this.pUnknown = pUnknown;
	}


	public String getpYhteensa() {
		return pYhteensa;
	}


	public void setpYhteensa(String pYhteensa) {
		this.pYhteensa = pYhteensa;
	}


	public String getbJaot() {
		return bJaot;
	}


	public void setbJaot(String bJaot) {
		this.bJaot = bJaot;
	}


	public String getbNoudot() {
		return bNoudot;
	}


	public void setbNoudot(String bNoudot) {
		this.bNoudot = bNoudot;
	}


	public String getbDhl() {
		return bDhl;
	}


	public void setbDhl(String bDhl) {
		this.bDhl = bDhl;
	}


	public String getbYhteensa() {
		return bYhteensa;
	}


	public void setbYhteensa(String bYhteensa) {
		this.bYhteensa = bYhteensa;
	}


	public String getiKollit() {
		return iKollit;
	}


	public void setiKollit(String iKollit) {
		this.iKollit = iKollit;
	}


	public String getiStopit() {
		return iStopit;
	}


	public void setiStopit(String iStopit) {
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
