package Hopital;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

/**
 * Classe abstraite repr�sentant les caract�ristiques communes du personnel de l'hopital
 * 
 * Chaque personnel dispose d'un nom, pr�nom, date d'entr�e, date de sortie (initialis�e � null � la cr�ation)
 * d'une anciennet� (qui est calcul� via une m�thode d�di�e), d'une r�mun�ration fixe et variable,
 * ainsi qu'un service d'affectation, o� il exerce son activit�, quel que soit le corps de m�tier.
 * 
 */
public abstract class Personnels {

	private int idSalarie;
	private String nom;
	private String prenom;
	private LocalDate dateEntree;
	private LocalDate dateSortie;
	private int anciennete;
	private float remunerationFixe;
	private float remunerationVariable;
	private Services serviceAffectation;

	public Personnels() {
	} 

	/**
	 * @param idSalarie
	 * @param nom
	 * @param prenom
	 * @param dateEntree
	 * @param remunerationFixe
	 * @param remunerationVariable
	 */
	public Personnels(int idSalarie, String nom, String prenom, LocalDate dateEntree, 
			float remunerationFixe,	float remunerationVariable) {
		super();
		this.idSalarie = idSalarie;
		this.nom = nom;
		this.prenom = prenom;
		this.dateEntree = dateEntree;
		this.remunerationFixe = remunerationFixe;
		this.remunerationVariable = remunerationVariable;
	}
	
	public int getIdSalarie() {
		return idSalarie;
	}
	
	public void setIdSalarie(int ID) {
		this.idSalarie=ID;
	}
	
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
	 * @return the dateEntree
	 */
	public LocalDate getDateEntree() {
		return dateEntree;
	}

	/**
	 * @param dateEntree the dateEntree to set
	 */
	public void setDateEntree(LocalDate dateEntree) {
		this.dateEntree = dateEntree;
	}
	
	/**
	 * @param dateEntree the dateEntree to set
	 */
	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree.toLocalDate();
	}

	/**
	 * @return the dateSortie
	 */
	public LocalDate getDateSortie() {
		return dateSortie;
	}

	/**
	 * @param dateSortie the dateSortie to set
	 */
	public void setDateSortie(LocalDate dateSortie) {
		this.dateSortie = dateSortie;
	}
	
	/**
	 * @param dateSortie the dateSortie to set
	 */
	public void setDateSortie(Date dateSortie) {
		if (dateSortie==null) this.dateSortie=null;
		else this.dateSortie = dateSortie.toLocalDate();
	}

	/**
	 * @return the anciennete
	 */
	public int getAnciennete() {
		this.anciennete=this.calculAnciennete();
		return anciennete;
	}

	/**
	 * @param anciennete the anciennete to set
	 */
	public void setAnciennete(int anciennete) {
		this.anciennete = anciennete;
	}

	/**
	 * @return the remunerationFixe
	 */
	public float getRemunerationFixe() {
		return remunerationFixe;
	}

	/**
	 * @param remunerationFixe the remunerationFixe to set
	 */
	public void setRemunerationFixe(float remunerationFixe) {
		this.remunerationFixe = remunerationFixe;
	}

	/**
	 * @return the remunerationVariable
	 */
	public float getRemunerationVariable() {
		return remunerationVariable;
	}

	/**
	 * @param remunerationVariable the remunerationVariable to set
	 */
	public void setRemunerationVariable(float remunerationVariable) {
		this.remunerationVariable = remunerationVariable;
	}
	
	/**
	 * A partir de la date de cr�ation en BDD et l'instant pr�sent, calcul en mois l'anciennt� du salari�
	 * 
	 * @return un entier repr�sentant le nombre de mois d'anciennet�
	 */
	public int calculAnciennete() {
		LocalDate dateJour= LocalDate.now();
		Period temp = Period.between(this.getDateEntree(), dateJour);
		return (int) temp.toTotalMonths();
	}


	public Services getServiceAffectation() {
		return serviceAffectation;
	}

	public void setServiceAffectation(Services serviceAffectation) {
		this.serviceAffectation = serviceAffectation;
	}

	@Override
	public String toString() {
		return "Personnels [idSalarie=" + idSalarie + ", nom=" + nom + ", prenom=" + prenom + ", dateEntree="
				+ dateEntree + "]";
	}
	

	
}