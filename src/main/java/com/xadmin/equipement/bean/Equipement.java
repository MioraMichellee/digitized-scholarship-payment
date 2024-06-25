package com.xadmin.equipement.bean;

public class Equipement {
	private int idEq;
	private int montantEq;
	
	public Equipement(int idEq,  int montantEq) {
		
		this.idEq = idEq;
		this.montantEq = montantEq;
	}

	public Equipement(int montantEq) {
		this.montantEq = montantEq;
	}

	public int getIdEq() {
		return idEq;
	}

	public void setIdEq(int idEq) {
		this.idEq = idEq;
	}

	public int getMontantEq() {
		return montantEq;
	}

	public void setMontantEq(int montantEq) {
		this.montantEq = montantEq;
	}
	
}
