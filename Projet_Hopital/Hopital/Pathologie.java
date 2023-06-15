package Hopital;

import java.util.*;

/**
 * Représente les pathologies qui peuvent être diagnostiquées au patient
 * 
 *  Elle ont un nom et une liste de traitement
 *  
 *  Non présent dans la version actuelle de l'application
 */
public class Pathologie {

	private int idPathologie;
	private String nom;
	private List<String> traitement;

	public Pathologie() {
	}

	/**
	 * @param idPathologie
	 * @param nom
	 * @param traitement
	 */
	public Pathologie(int idPathologie, String nom, List<String> traitement) {
		super();
		this.idPathologie = idPathologie;
		this.nom = nom;
		this.traitement = traitement;
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
	 * @return the traitement
	 */
	public List<String> getTraitement() {
		return traitement;
	}

	/**
	 * @param traitement the traitement to set
	 */
	public void setTraitement(List<String> traitement) {
		this.traitement = traitement;
	}

	/**
	 * @return the idPathologie
	 */
	public int getIdPathologie() {
		return idPathologie;
	}

}