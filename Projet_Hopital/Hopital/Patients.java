package Hopital;

import java.time.LocalDateTime;
import java.util.*;

import idGenerator.IdGenPatients;

/**
 * Représente les patients venant à l'hopital pour une consultation
 * 
 * Ils disposent d'un nom, prénom, d'une adresse représentée par un objet Adresses, d'une date de création
 * et d'un numéro de Sécurité Sociale
 * 
 * Ils sont administrés par un agent d'accueil.
 * 
 * @author Romain
 *
 */
public class Patients {

	private int idPatient;
	private String nom;
	private String prenom;
	private Adresses adressePatient;
	private String dateCreation;
	private String numeroSS;
		
	public Patients () {
	}
	
	public Patients (int id) {
		this.idPatient=id;
	}

	public Patients(String nom, String prenom, Adresses adressePatient) {
		this.setIdPatient(IdGenPatients.genId());
		this.nom = nom;
		this.prenom = prenom;
		this.adressePatient = adressePatient;
		this.setDateCreation();
	}

	public Patients(String nom, String prenom, Adresses adressePatient, 
			String dateCreation, String numSS) {
		this.setIdPatient(IdGenPatients.genId());
		this.nom = nom;
		this.prenom = prenom;
		this.adressePatient = adressePatient;
		this.dateCreation = dateCreation;
		this.numeroSS=numSS;
	}
	
	public Patients(int idPatient, String nom, String prenom, String numSS) {
		this.idPatient=idPatient;
		this.nom=nom;
		this.prenom=prenom;
		this.numeroSS=numSS;
	}
	
	public Patients(int idPatient, String nom, String prenom, String numSS, String dateCreation, Adresses adresse) {
		this.idPatient=idPatient;
		this.nom=nom;
		this.prenom=prenom;
		this.numeroSS=numSS;
		this.dateCreation=dateCreation;
		this.adressePatient=adresse;
	}

	public Patients(int id, String nom, String prenom, Adresses nouvelleAdresse) {
		this.idPatient = id;
		this.nom=nom;
		this.prenom=prenom;
		this.adressePatient=nouvelleAdresse;
		this.setDateCreation();
	}

	public Patients(int id, String nom, String prenom, Adresses adresse, String dateCreation, String numSS) {
		this.idPatient=id;
		this.nom=nom;
		this.prenom=prenom;
		this.adressePatient=adresse;
		this.dateCreation=dateCreation;
		this.numeroSS=numSS;
	}

	public String getNumeroSS() {
		return numeroSS;
	}

	public void setNumeroSS(String numeroSS) {
		this.numeroSS = numeroSS;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the adressePatient
	 */
	public Adresses getAdressePatient() {
		return adressePatient;
	}

	/**
	 * @param adressePatient the adressePatient to set
	 */
	public void setAdressePatient(Adresses adressePatient) {
		this.adressePatient = adressePatient;
	}

	/**
	 * @return the dateCreation
	 */
	public String getDateCreation() {
		return this.dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation() {
		this.dateCreation = LocalDateTime.now().toString();
	}
	
	public void updateDateCreation (String nouvelleDateCreation) {
		this.dateCreation=nouvelleDateCreation;
	}

	/**
	 * @return the idPatient
	 */
	public int getIdPatient() {
		return idPatient;
	}
	
	public void setIdPatient(int ID) {
		this.idPatient=ID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateCreation, idPatient, nom, prenom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patients other = (Patients) obj;
		return  idPatient == other.idPatient
				&& Objects.equals(nom, other.nom)
				&& Objects.equals(prenom, other.prenom)
				&& Objects.equals(numeroSS,other.numeroSS);
	}

	@Override
	public String toString() {
		return "Patients [idPatient=" + idPatient + ", nom=" + nom + ", prenom=" + prenom + ", dateCreation="
				+ dateCreation + ", numeroSS=" + numeroSS + "]";
	}


	
	

}