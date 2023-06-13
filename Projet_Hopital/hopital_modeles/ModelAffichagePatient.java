package hopital_modeles;

import java.util.List;

import DAO.AdressesDAO;
import DAO.PatientsDAO;
import Hopital.Adresses;
import Hopital.Patients;
import connexion.SingleConnection;

public class ModelAffichagePatient {
	PatientsDAO patientDAO;
	AdressesDAO adresseDAO;
	Patients patientAffiche;
	Adresses adresseAffiche;

	public ModelAffichagePatient() {
	}
	
	public ModelAffichagePatient(Patients patientAffiche) {
		super();
		this.patientAffiche = patientAffiche;
	}

	public Patients getPatientAffiche() {
		return patientAffiche;
	}

	public void setPatientAffiche(Patients patientAffiche) {
		this.patientAffiche = patientAffiche;
	}
	
	public boolean creerPatient() {
		return false;
	}
	
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
			System.out.println(adresseUpdateStatus);
			return true;
		}
		else return false;
	}
	
	public Adresses getAdressePatient() {
		adresseDAO = new AdressesDAO(SingleConnection.getInstance());
		adresseAffiche = adresseDAO.findById(patientAffiche.getAdressePatient().getIdAdresse()).orElse(new Adresses());
		return adresseAffiche;
	}
	
	
	
	

}
