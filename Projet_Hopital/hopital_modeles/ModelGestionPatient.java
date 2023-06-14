package hopital_modeles;

import java.util.ArrayList;
import java.util.List;

import DAO.PatientsDAO;
import Exceptions.ErreurInterrogationBDD;
import Hopital.Patients;
import connexion.SingleConnection;

/**
 * Mod�le pour la recherche de patient
 * 
 * G�re l'acc�s � la base de donn�es SQL pour les patients via une classe DAO d�di�e
 * Seules les recherches par num�ro SS, Nom et idPatient sont possibles 
 * 
 * La recherche par num�ro SS et Nom supporte le '%' pour une recherche �largie
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
	 * A partir du nom d'un patient, ou d'une partie du nom si le caract�re '%' est utilis� dans le {@code str}, lance la recherche dans la base de donn�es via la DAO d�di�e
	 * 
	 * @param str la saisie utilisateur renseign�e dans le champ de recherche, correpondant au nom recherch�
	 * @return une liste de patients compos�e d'un patient ou plus. Si aucun patient n'est trouv�, renvoie une liste vide.
	 * 
	 */
	public List<Patients> lancerRechercheNom(String str) {
		PatientsDAO patientDAO = new PatientsDAO(SingleConnection.getInstance());
		if (patientDAO.findByName(str).isPresent()) return patientDAO.findByName(str).get();
		else return new ArrayList<>();
	}
	
	/**
	 * A partir du num�ro de SS, ou d'une partie de ce num�ro si le caract�re '%' est utilis� dans le {@code str}, lance la recherche dans la base de donn�es via la DAO d�di�e
	 * 
	 * @param str la saisie utilisateur renseign�e dans le champ de recherche, correpondant au num�ro de SS recherch�
	 * @return une liste de patients compos�e d'un patient ou plus. Si aucun patient n'est trouv�, renvoie une liste vide.
	 */
	public List<Patients> lancerRechercheNumSS(String str) {
		PatientsDAO patientDAO = new PatientsDAO(SingleConnection.getInstance());
		if (patientDAO.findByNumSS(str).isPresent()) return patientDAO.findByNumSS(str).get();	
		else return new ArrayList<>();
	}
	
	/**
	 * A partir de l'idPatient, lance la recherche dans la base donn�es via la DAO d�di�e
	 * L'idPatient ne peut �tre faite qu'� partir d'un int, d'o� la conversion. La recherche est exacte.
	 * 
	 * @param str la saisie utilisateur renseign�e dans le champ de recherche, correpondant � l'identifiant du patient recherch�
	 * @return un seul patient. Si aucun patient n'est trouv�, renvoie un patient vide, dont l'idPatient est �gal � 0
	 */
	public Patients lancerRechercheIdPatient(String str) {
		PatientsDAO patientDAO = new PatientsDAO(SingleConnection.getInstance());
		if (patientDAO.findById(Integer.valueOf(str)).isPresent()) return patientDAO.findById(Integer.valueOf(str)).get();
		else return new Patients();
	}

	/**
	 * A partir de l'idPatient, recherche en base de donn�es le patient correspondant pour le supprimer.
	 * La suppression du patient entraine la suppression de son adresse dans table d�di�es aux adresses.
	 * L'idPatient est utilis� pour r�cup�r� l'objet patient � supprimer. Si aucun patient n'est trouv�, alors un patient vide, 
	 * dont l'idPatient est �gale � 0 est utilis� pour faire �chouer la suppression.
	 * 	
	 * @param idPatient L'identifiant du patient � supprimer. S�lectionn� par l'utilisateur sur la vue et transmis par le contr�leur
	 * @return {@code true} si la patient a �t� correctement supprim� en base
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
