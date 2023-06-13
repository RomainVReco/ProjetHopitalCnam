package hopital_modeles;

import java.util.ArrayList;
import java.util.List;

import DAO.PatientsDAO;
import Exceptions.ErreurInterrogationBDD;
import Hopital.Patients;
import connexion.SingleConnection;

/**
 * Modèle pour la recherche de patient
 * 
 * Gère l'accès à la base de données via les DAO
 * Seules les recherches par numéro SS, Nom et idPatient sont possibles 
 * @author Romain
 *
 */
public class ModelGestionPatient {
	PatientsDAO patientDAO;
	String critereRecherche="", lancerRecherche="Rechercher";
	
	public ModelGestionPatient () {
	}
	

	public void getPatients () {
	}
	
	public List<Patients> lancerRechercheNom(String critereRecherche, String str) {
		PatientsDAO patientDAO = new PatientsDAO(SingleConnection.getInstance());
		if (patientDAO.findByName(str).isPresent()) return patientDAO.findByName(str).get();
		else return new ArrayList<>();
	}
	
	public List<Patients> lancerRechercheNumSS(String critereRecherche, String str) {
		PatientsDAO patientDAO = new PatientsDAO(SingleConnection.getInstance());
		if (patientDAO.findByNumSS(str).isPresent()) return patientDAO.findByNumSS(str).get();	
		else return new ArrayList<>();
	}
	
	public Patients lancerRechercheIdPatient(String str) {
		PatientsDAO patientDAO = new PatientsDAO(SingleConnection.getInstance());
		if (patientDAO.findById(Integer.valueOf(str)).isPresent()) return patientDAO.findById(Integer.valueOf(str)).get();
		else return new Patients();
	}

	
	public boolean supprimerPatient(int idPatient) {
		PatientsDAO patientDAO = new PatientsDAO(SingleConnection.getInstance());
		boolean deleteStatus = false;
		if (patientDAO.findById(idPatient).isPresent()) {
			Patients patientSupprime = patientDAO.findById(idPatient).orElse(new Patients());
			try {
				deleteStatus = patientDAO.delete(patientSupprime);
			} catch (ErreurInterrogationBDD e) {
				e.printStackTrace();
			}
		}
		return deleteStatus;
	}

	public String getCritereRecherche() {
		return critereRecherche;
	}

	public void setCritereRecherche(String critereRecherche) {
		this.critereRecherche = critereRecherche;
	}	
}
