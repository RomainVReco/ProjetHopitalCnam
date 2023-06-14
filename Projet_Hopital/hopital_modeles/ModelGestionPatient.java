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
 * Gère l'accès à la base de données SQL pour les patients via une classe DAO dédiée
 * Seules les recherches par numéro SS, Nom et idPatient sont possibles 
 * 
 * La recherche par numéro SS et Nom supporte le '%' pour une recherche élargie
 * 
 * @author Romain
 *
 */
public class ModelGestionPatient {
	PatientsDAO patientDAO;
	String critereRecherche="";
	

	public ModelGestionPatient () {
	}
		
	/**
	 * A partir du nom d'un patient, ou d'une partie du nom si le caractère '%' est utilisé dans le {@code str}, lance la recherche dans la base de données via la DAO dédiée
	 * 
	 * @param str la saisie utilisateur renseignée dans le champ de recherche, correpondant au nom recherché
	 * @return une liste de patients composée d'un patient ou plus. Si aucun patient n'est trouvé, renvoie une liste vide.
	 * 
	 */
	public List<Patients> lancerRechercheNom(String str) {
		PatientsDAO patientDAO = new PatientsDAO(SingleConnection.getInstance());
		if (patientDAO.findByName(str).isPresent()) return patientDAO.findByName(str).get();
		else return new ArrayList<>();
	}
	
	/**
	 * A partir du numéro de SS, ou d'une partie de ce numéro si le caractère '%' est utilisé dans le {@code str}, lance la recherche dans la base de données via la DAO dédiée
	 * 
	 * @param str la saisie utilisateur renseignée dans le champ de recherche, correpondant au numéro de SS recherché
	 * @return une liste de patients composée d'un patient ou plus. Si aucun patient n'est trouvé, renvoie une liste vide.
	 */
	public List<Patients> lancerRechercheNumSS(String str) {
		PatientsDAO patientDAO = new PatientsDAO(SingleConnection.getInstance());
		if (patientDAO.findByNumSS(str).isPresent()) return patientDAO.findByNumSS(str).get();	
		else return new ArrayList<>();
	}
	
	/**
	 * A partir de l'idPatient, lance la recherche dans la base données via la DAO dédiée
	 * L'idPatient ne peut être faite qu'à partir d'un int, d'où la conversion. La recherche est exacte.
	 * 
	 * @param str la saisie utilisateur renseignée dans le champ de recherche, correpondant à l'identifiant du patient recherché
	 * @return un seul patient. Si aucun patient n'est trouvé, renvoie un patient vide, dont l'idPatient est égal à 0
	 */
	public Patients lancerRechercheIdPatient(String str) {
		PatientsDAO patientDAO = new PatientsDAO(SingleConnection.getInstance());
		if (patientDAO.findById(Integer.valueOf(str)).isPresent()) return patientDAO.findById(Integer.valueOf(str)).get();
		else return new Patients();
	}

	/**
	 * A partir de l'idPatient, recherche en base de données le patient correspondant pour le supprimer.
	 * La suppression du patient entraine la suppression de son adresse dans table dédiées aux adresses.
	 * L'idPatient est utilisé pour récupéré l'objet patient à supprimer. Si aucun patient n'est trouvé, alors un patient vide, 
	 * dont l'idPatient est égale à 0 est utilisé pour faire échouer la suppression.
	 * 	
	 * @param idPatient L'identifiant du patient à supprimer. Sélectionné par l'utilisateur sur la vue et transmis par le contrôleur
	 * @return {@code true} si la patient a été correctement supprimé en base
	 */
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

}
