package Hopital;

import java.time.LocalDateTime;

/**
 * 
 */
public class MaterielMedical {

	private int idMateriel;
	private LocalDateTime horodatageEmprunt;
	private LocalDateTime horodatageRetour;
	private StatutMateriel statut;
	private String nomMateriel;
	private String descriptionMateriel;

	public MaterielMedical() {
	}

	/**
	 * @param idMateriel
	 * @param statut
	 * @param nomMateriel
	 * @param descriptionMateriel
	 */
	public MaterielMedical(int idMateriel, StatutMateriel statut, String nomMateriel, String descriptionMateriel) {
		super();
		this.idMateriel = idMateriel;
		this.statut = statut;
		this.nomMateriel = nomMateriel;
		this.descriptionMateriel = descriptionMateriel;
	}

	public MaterielMedical(int i) {
		this.idMateriel=i;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the horodatageEmprunt
	 */
	public LocalDateTime getHorodatageEmprunt() {
		return horodatageEmprunt;
	}

	/**
	 * @param horodatageEmprunt the horodatageEmprunt to set
	 */
	public void setHorodatageEmprunt(LocalDateTime horodatageEmprunt) {
		this.horodatageEmprunt = horodatageEmprunt;
	}

	/**
	 * @return the horodatageRetour
	 */
	public LocalDateTime getHorodatageRetour() {
		return horodatageRetour;
	}

	/**
	 * @param horodatageRetour the horodatageRetour to set
	 */
	public void setHorodatageRetour(LocalDateTime horodatageRetour) {
		this.horodatageRetour = horodatageRetour;
	}
	/**
	 * @return the statut
	 */
	public StatutMateriel getStatut() {
		return statut;
	}

	public void setStatut(StatutMateriel statut) {
		this.statut = statut;
	}
	
	public int getIdMateriel() {
		return idMateriel;
	}
	
	public void setIdMateriel(int id) {
		this.idMateriel=id;
	}
	
	public String getNomMateriel() {
		return nomMateriel;
	}

	public void setNomMateriel(String nomMateriel) {
		this.nomMateriel = nomMateriel;
	}

}