package Hopital;

/**
 * Repr�sente les sp�cialit�s exerc�s par des m�decins
 * 
 * Les sp�cialit�s disposent d'un nom
 * @author Romain
 *
 */
public class Specialites {
	
	private int idSpecialite;
	private String nomSpecialite;
	
	public Specialites() {
	}
	
	public Specialites(int idSpecialite, String nomSpecialite) {
		super();
		this.idSpecialite = idSpecialite;
		this.nomSpecialite = nomSpecialite;
	}

	public int getIdSpecialite() {
		return idSpecialite;
	}

	public void setIdSpecialite(int idSpecialite) {
		this.idSpecialite = idSpecialite;
	}

	public String getNomSpecialite() {
		return nomSpecialite;
	}

	public void setNomSpecialite(String nomSpecialite) {
		this.nomSpecialite = nomSpecialite;
	}
	
}
