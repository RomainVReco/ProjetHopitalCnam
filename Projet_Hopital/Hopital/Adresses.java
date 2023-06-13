package Hopital;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Adresses {
	private int idAdresse;
	private String numero;
	private String adresse1;
	private String adresse2;
	private String codePostal;
	private String ville;
	private String pays;
	private String lastModified;

	public Adresses(int id) {
		this.idAdresse=id;
		this.setLastModified();
	}
	
	public Adresses(String numero, String nomRue, String adresseComplementaire, String codePostal, String ville,
			String pays) {
		super();
		this.setIdAdresse(new Random().nextInt(2500)+7);
		this.numero = numero;
		this.adresse1 = nomRue;
		this.adresse2 = adresseComplementaire;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
		this.setLastModified();
		
	}
	
	public Adresses(String nomRue, String adresseComplementaire, String codePostal, String ville,
			String pays) {
		super();
		this.setIdAdresse(new Random().nextInt(2500)+7);
		this.adresse1 = nomRue;
		this.adresse2 = adresseComplementaire;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
		this.setLastModified();
	}
	
	public Adresses(String nomRue, String codePostal, String ville,
			String pays) {
		super();
		this.setIdAdresse(new Random().nextInt(2500)+7);
		this.adresse1 = nomRue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
		this.setLastModified();
	}
	
	public Adresses(int idAdresse, String numero, String adresse1, String adresse2, String codePostal, String ville,
			String pays, String lastModified) {
		super();
		this.idAdresse = idAdresse;
		this.numero = numero;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
		this.lastModified = lastModified;
	}

	public Adresses() {
		// TODO Auto-generated constructor stub
	}

	public int getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the adresse1
	 */
	public String getAdresse1() {
		return adresse1;
	}

	/**
	 * @param adresse1 the adresse1 to set
	 */
	public void setAdresse1(String nomRue) {
		this.adresse1 = nomRue;
	}

	/**
	 * @return the adresse2
	 */
	public String getAdresse2() {
		return adresse2;
	}

	/**
	 * @param adresse2 the adresse2 to set
	 */
	public void setAdresse2(String adresseComplementaire) {
		this.adresse2 = adresseComplementaire;
	}

	/**
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * @return the pays
	 */
	public String getPays() {
		return pays;
	}

	/**
	 * @param pays the pays to set
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getDateCreationAdresse() {
		return lastModified;
	}

	public void setLastModified() {
		DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateCreation = LocalDateTime.now() ;
		this.lastModified = dateCreation.format(CUSTOM_FORMATTER);
	}
	
	public void setDateCreationAdresse(String dateCreation) {
		this.lastModified=dateCreation;
	}
	
	public void adressUpdateDate (String UpdateDate) {
		this.lastModified = UpdateDate;
	}

}