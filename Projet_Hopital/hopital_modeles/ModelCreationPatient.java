package hopital_modeles;

import java.util.List;
import java.util.Random;

import DAO.AdressesDAO;
import DAO.PatientsDAO;
import Exceptions.ErreurInterrogationBDD;
import Hopital.Adresses;
import Hopital.Patients;
import connexion.SingleConnection;

/**
 * Mod�le de la vue de cr�ation de patient
 * 
 * Permet les op�rations Create et Retrieve pour un patient � cr�er ou tout juste cr��
 * @author Romain
 *
 */
public class ModelCreationPatient {
	PatientsDAO patientDAO;
	AdressesDAO adresseDAO;
	Patients patientCree;
	Adresses adresseCree;

	public ModelCreationPatient() {
	}
	/**
	 * 
	 * @return le patient a cr�� ou modifi�
	 */
	public Patients getPatientCree() {
		return patientCree;
	}

	public void setPatientCree(Patients patientAffiche) {
		this.patientCree = patientAffiche;
	}
	
	/**
	 * Cr�e le patient en base de donn�es, ainsi que son adresse. Le patient n'est cr�� que si son adresse peut l'�tre aussi.
	 * 
	 * R�cup�re les informations personnelles du patient, ainsi que son adresse sous forme de deux listes,
	 * puis cr�� un objet Patients et Adresses destin�s � �tre pass�s au DAO pour enregistrement en base de donn�es
	 * 
	 * @param infosPatient informations personnelles du patient
	 * @param infosAdresse adresse du patient
	 * @return {@code true} si la patient a pu �tre cr�� en BDD
	 */
	public boolean creerPatientModel(List<String> infosPatient, List<String> infosAdresse) {
		patientDAO = new PatientsDAO(SingleConnection.getInstance());
		adresseDAO = new AdressesDAO(SingleConnection.getInstance());
		boolean createPatientStatus=false;
		boolean createAdresseStatus=false;
		
		int i=0;
		patientCree = new Patients();
		patientCree.setNom(infosPatient.get(i++));
		patientCree.setPrenom(infosPatient.get(i++));
		patientCree.setNumeroSS(infosPatient.get(i++));
		patientCree.updateDateCreation(infosPatient.get(i++));
		patientCree.setIdPatient(Integer.valueOf(infosPatient.get(i++)));
		
		int j=0;
		int idAdresse = generateUniqueAdresseId();
		adresseCree = new Adresses();
		adresseCree.setNumero(infosAdresse.get(j++));
		adresseCree.setAdresse1(infosAdresse.get(j++));
		adresseCree.setAdresse2(infosAdresse.get(j++));
		adresseCree.setCodePostal(infosAdresse.get(j++));
		adresseCree.setVille(infosAdresse.get(j++));
		adresseCree.setPays(infosAdresse.get(j++));
		adresseCree.setLastModified();
		adresseCree.setIdAdresse(idAdresse);
		
		try {
			createAdresseStatus = adresseDAO.create(adresseCree);
			System.out.println(createAdresseStatus);
		} catch (ErreurInterrogationBDD e) {
			e.printStackTrace();
		}
		patientCree.setAdressePatient(adresseCree);
		
		if (createAdresseStatus==true) {
			try {
				createPatientStatus = patientDAO.create(patientCree);
			} catch (ErreurInterrogationBDD e) {
				e.printStackTrace();
			}
		} else return false;

		return createPatientStatus;
	}
	
	/**
	 * Permet la mise � jour des informations du patient nouvelle cr��
	 * Ici, la mise � jour du patient en base ne d�pend pas de la bonne mise � jour de l'adresse
	 * 
	 * R�cup�re les informations personnelles du patient, ainsi que son adresse sous forme de deux listes,
	 * puis cr�� un objet Patients et Adresses destin�s � �tre pass�s au DAO pour enregistrement des modifications en base de donn�es
	 * 
	 * @param list informations personnelles du patient nouvellement cr�� et faisant l'objet de modification
	 * @param idAdressePatient id de l'adresse du patient nouvellement cr��e
	 * @return {@code true} si la mise � jour s'est correctement faite
	 */
	public boolean modifierPatient(List<String> list) {
		int i=0;
		patientDAO = new PatientsDAO(SingleConnection.getInstance());
		adresseDAO = new AdressesDAO(SingleConnection.getInstance());
		Patients patientUpdated = patientCree;
		patientUpdated.setNom(list.get(i++));
		patientUpdated.setPrenom(list.get(i++));
		patientUpdated.setNumeroSS(list.get(i++));
		patientUpdated.setIdPatient(Integer.valueOf(list.get(i++)));
		
		Adresses adresseUpdated = adresseCree;
//		adresseUpdated.setIdAdresse(idAdressePatient);
		adresseUpdated.setNumero(list.get(i++));
		adresseUpdated.setAdresse1(list.get(i++));
		adresseUpdated.setAdresse2(list.get(i++));
		adresseUpdated.setCodePostal(list.get(i++));
		adresseUpdated.setVille(list.get(i++));
		adresseUpdated.setPays(list.get(i++));
		
		patientUpdated.setAdressePatient(adresseUpdated);
		
		boolean patientUpdateStatus = patientDAO.update(patientUpdated);
		if (patientUpdateStatus==true) {
			adresseDAO.update(adresseUpdated);
			patientCree=patientUpdated;
			return true;
		}
		else return false;
	}
	
	/**
	 * @return l'adresse cr�� par l'utilisateur pour enregistrer le patient en base
	 */
	public Adresses getAdressePatient() {
		return adresseCree;
	}

	/**
	 * G�n�re la cr�ation d'un identifiant unique pour un patient
	 * 
	 * Avant d'�tre renvoy�, la m�thode v�rifie si le nouvel identifiant n'existe pas en BDD.
	 * A faire �voluer si la base de patients devient trop importante
	 * 
	 * @return un idPatient unique de type int
	 */
	public int generateUniquePatientId() {
		patientDAO = new PatientsDAO(SingleConnection.getInstance());
		int generatedId = -1 ;
		boolean uniqueId=false;
		while (uniqueId==false) {
			generatedId = new Random().nextInt(2500)+7;
			uniqueId = patientDAO.findById(generatedId).isEmpty();
		}
		return generatedId;
	}
	
	/**
	 * G�n�re la cr�ation d'un identifiant unique pour une adresse
	 * 
	 * Avant d'�tre renvoy�, la m�thode v�rifie si le nouvel identifiant n'existe pas en BDD.
	 * A faire �voluer si la base d'adresses devient trop importante
	 * 
	 * @return un idAdresse unique de type int
	 */
	private int generateUniqueAdresseId() {
		adresseDAO = new AdressesDAO(SingleConnection.getInstance());
		int generatedId = -1 ;
		boolean uniqueId=false;
		while (uniqueId==false) {
			generatedId = new Random().nextInt(2500)+7;
			uniqueId = adresseDAO.findById(generatedId).isEmpty();
		}
		return generatedId;
	}
}
