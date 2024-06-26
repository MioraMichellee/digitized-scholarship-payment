package com.xadmin.payer.bean;

import java.sql.Date;

public class Payer {
	private int idPaye;
	private int matricule;
	private String anneeUniv;
	private String date ;
	private int nbMois;
	
	
	public Payer(int idPaye, int matricule, String anneeUniv, String date, int nbMois) {
		this.idPaye = idPaye;
		this.matricule = matricule;
		this.anneeUniv = anneeUniv;
		this.date = date;
		this.nbMois = nbMois;
	}


	public Payer(int matricule, String anneeUniv, String date, int nbMois) {
		this.matricule = matricule;
		this.anneeUniv = anneeUniv;
		this.date = date;
		this.nbMois = nbMois;
	}


	public int getIdPaye() {
		return idPaye;
	}


	public void setIdPaye(int idPaye) {
		this.idPaye = idPaye;
	}


	public int getMatricule() {
		return matricule;
	}


	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}


	public String getAnneeUniv() {
		return anneeUniv;
	}


	public void setAnneeUniv(String anneeUniv) {
		this.anneeUniv = anneeUniv;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getNbMois() {
		return nbMois;
	}


	public void setNbMois(int nbMois) {
		this.nbMois = nbMois;
	}
	
}
