package hopital_modeles;

import java.util.List;

import DAO.AdressesDAO;
import DAO.PatientsDAO;
import Hopital.Adresses;
import Hopital.Patients;
import connexion.SingleConnection;

/**
 * Modèle pour l'affichage d'un patient qui a été recherché et sélectionné dans la vue RechercheAgentPatient
 * 
 * Cette vue étant dédiée à l'affichage et la modification, le modèle ne permet que le modification du patient affiché. 
 * 
 * Afin de transmettre les informations modifiées nécessaires à la base données, la classe contient 2 variables d'instances :
 * {@code patientAffiche} qui permettra de stocker le patient à modifier et {@code adresseAffiche} qui stocke les informations
 * relatives à l'adresse du patient à modifier
 * 
 * @author Romain
 *
 */
public class ModelAffichagePatient {
	PatientsDAO patientDAO;
	AdressesDAO adresseDAO;
	Patients patientAffiche;
	Adresses adresseAffiche;

	/**
	 * Constructeur par défaut
	 */
	public ModelAffichagePatient() {
	}
	
	/**
	 * Constructeur du modèle qui prend en paramètre un patient, qui initialise la variable d'instance {@code patientAffiche}
	 * @param patientAffiche
	 */
	public ModelAffichagePatient(Patients patientAffiche) {
		super();
		this.patientAffiche = patientAffiche;
		this.adresseAffiche = patientAffiche.getAdressePatient();
	}

	public Patients getPatientAffiche() {
		return patientAffiche;
	}

	public void setPatientAffiche(Patients patientAffiche) {
		this.patientAffiche = patientAffiche;
	}
	
	/**
	 * Prend en paramètre la liste des informations personnelles et d'adresse du patient à modifier, et l'idAdresse afin de faire le lien
	 * avec la table Adresses
	 * 
	 * 2 objets Patients et Adresses sont créés et passés au DAO pour modifier la base de données.
	 * 
	 * @param list La liste des informations du Patient, personnelles et d'adresses.
	 * @param idAdressePatient L'identifiant de l'adresse
	 * @return {@code true} si la modification du patient à pu être faite
	 */
	public boolean modifierPatient(List<String> list, int idAdressePatient) {
		int i=0;
		patientDAO = new PatientsDAO(SingleConnection.getInstance());
		adresseDAO = new AdressesDAO(SingleConnection.getInstance());
		Patients patientUpdated = new Patients();
		patientUpdated.setNom(list.get(i++));
		patientUpdated.setPrenom(list.get(i++));
		patientUpdated.setNumeroSS(list.get(i++));
		patientUpdated.updateDateCreation(list.get(i++));
		patientUpdated.setIdPatient(Integer.valueOf(list.get(i++)));
		
		Adresses adresseUpdated = new Adresses();
		adresseUpdated.setIdAdresse(idAdressePatient);
		adresseUpdated.setNumero(list.get(i++));
		adresseUpdated.setAdresse1(list.get(i++));
		adresseUpdated.setAdresse2(list.get(i++));
		adresseUpdated.setCodePostal(list.get(i++));
		adresseUpdated.setVille(list.get(i++));
		adresseUpdated.setPays(list.get(i++));
		adresseUpdated.setDateCreationAdresse(list.get(i++));
		
		patientUpdated.setAdressePatient(adresseUpdated);
		
		boolean adresseUpdateStatus = adresseDAO.update(adresseUpdated);
		boolean patientUpdateStatus = patientDAO.update(patientUpdated);
		if (patientUpdateStatus==true) {
			adresseUpdateStatus = adresseDAO.update(adresseUpdated);
			patientAffiche=patientUpdated;
			adresseAffiche=patientUpdated.getAdressePatient();
			System.out.println(adresseUpdateStatus);
			return true;
		}
		else return false;
	}
	
	/**
	 * @return l'adresse du patient enregistré dans le modèle
	 */
	public Adresses getAdresseAffiche() {
		return adresseAffiche;
	}
	
	
	
	

}
