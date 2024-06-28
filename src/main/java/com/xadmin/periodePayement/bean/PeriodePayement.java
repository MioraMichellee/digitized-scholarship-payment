package com.xadmin.periodePayement.bean;

public class PeriodePayement {
	private int idPeriode;
	private String tranche;
	private String dateDebut;
	private String dateFin;
	
	public PeriodePayement(int idPeriode, String tranche, String dateDebut, String dateFin) {
		this.idPeriode = idPeriode;
		this.tranche = tranche;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	public PeriodePayement(String tranche, String dateDebut, String dateFin) {
		this.tranche = tranche;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	public int getIdPeriode() {
		return idPeriode;
	}
	public void setIdPeriode(int idPeriode) {
		this.idPeriode = idPeriode;
	}
	public String getTranche() {
		return tranche;
	}
	public void setTranche(String tranche) {
		this.tranche = tranche;
	}
	public String getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getDateFin() {
		return dateFin;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	
}
