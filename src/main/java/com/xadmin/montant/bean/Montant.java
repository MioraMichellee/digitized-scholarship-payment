package com.xadmin.montant.bean;

public class Montant {
	private int idNiv;
	private String niveau;
	private int montant;
	
	public Montant(int idNiv, String niveau, int montant) {
		
		this.idNiv = idNiv;
		this.niveau = niveau;
		this.montant = montant;
	}

	public Montant(String niveau, int montant) {
		this.niveau = niveau;
		this.montant = montant;
	}

	public int getIdNiv() {
		return idNiv;
	}

	public void setIdNiv(int idNiv) {
		this.idNiv = idNiv;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	
	
	
}
