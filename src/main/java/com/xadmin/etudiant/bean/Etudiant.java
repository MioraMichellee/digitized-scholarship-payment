
package com.xadmin.etudiant.bean;

public class Etudiant {
    private int matricule;
    private String nom ;
    private String sexe;
    private String dateNais ;
    private String institution;
    private String niveau ;
    private String mail ;
    private String anneeUniv ;


    public Etudiant(int matricule, String nom, String sexe, String dateNais, String institution, String niveau, String mail, String anneeUniv) {
        this.matricule = matricule;
        this.nom = nom;
        this.sexe = sexe;
        this.dateNais = dateNais;
        this.institution = institution;
        this.niveau = niveau;
        this.mail = mail;
        this.anneeUniv = anneeUniv;
    }

    public Etudiant(String nom, String sexe, String dateNais, String institution, String niveau, String mail, String anneeUniv) {
        this.nom = nom;
        this.sexe = sexe;
        this.dateNais = dateNais;
        this.institution = institution;
        this.niveau = niveau;
        this.mail = mail;
        this.anneeUniv = anneeUniv;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getDateNais() {
        return dateNais;
    }

    public void setDateNais(String dateNais) {
        this.dateNais = dateNais;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAnneeUniv() {
        return anneeUniv;
    }

    public void setAnneeUniv(String anneeUniv) {
        this.anneeUniv = anneeUniv;
    }
}

