package Hopital;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import idGenerator.IdGenMedecin;

/**
 * Représente un médecin qui est un membre du personnel
 * Il dispse d'un nombre d'actes effectués, d'une spécialité et d'une date d'obtention de doctorat.
 * La variable d'instance listeConsultations est utilisée pour le calcul du nombre d'actes
 * 
 * Il reçoit des patients en consultation et peut les créer, modifier, afficher, supprimer.
 * 
 * Il est lui même géré par un Administrateur 
 * 
 * Non présent dans la version actuelle de l'application
 */
public class Medecins extends Personnels {

	private int nbreActes=0;
	private LocalDate obtentionDoctorat;
	private Specialites specialiteMedecin;
	private List<Consultations> listeConsultations;
	
	// Constructeur pour initialiser un objet Medecins
	public Medecins() {
		
	}
	
	// Constructeur pour MedecinsDAO
	public Medecins(int idSalarie, String nom, String prenom, Date dateEntree, Date dateSortie,
			int anciennete, float remFixe, float remVar, int nbreActes2, 
			Date dateDoctorat, Services serviceAffectation, Specialites spe) {
		this.setIdSalarie(idSalarie);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setDateEntree(dateEntree.toLocalDate());
		this.setDateSortie(dateSortie.toLocalDate());
		this.setAnciennete(anciennete);
		this.setRemunerationFixe(remFixe);
		this.setRemunerationVariable(remVar);
		this.setNbreActes(nbreActes2);
		this.setObtentionDoctorat(dateDoctorat.toLocalDate());
		this.setServiceAffectation(serviceAffectation);
		this.specialiteMedecin=spe;
		}

	
	public Medecins(String nom, String prenom, Date dateEntree, Date dateSortie, int anciennete, float remFixe,
			float remVar, int nbreActes2, Date dateDoctorat, Services service, Specialites specialite) {
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setDateEntree(dateEntree.toLocalDate());
		this.setDateSortie(dateSortie.toLocalDate());
		this.setAnciennete(anciennete);
		this.setRemunerationFixe(remFixe);
		this.setRemunerationVariable(remVar);
		this.setNbreActes(nbreActes2);
		this.setObtentionDoctorat(dateDoctorat.toLocalDate());
		this.setServiceAffectation(service);
		this.specialiteMedecin=specialite;
	}

	public void calculNombreActes() {
		int i = 0;
		for (Consultations c : this.getListeConsultations()) {
			if (c.getMedecin().equals(this)) {
				i=i+1;
			}
		}
		this.nbreActes=i;
	}
	
	public void addConsultation (Consultations nouvelleConsultation) {
		//this.listeConsultations.add(nouvelleConsultation);
			}
		
	public int getNbreActes() {
		return nbreActes;
	}

	public void setNbreActes(int nbreActes) {
		this.nbreActes = nbreActes;
	}

	public LocalDate getObtentionDoctorat() {
		return obtentionDoctorat;
	}

	public void setObtentionDoctorat(LocalDate obtentionDoctorat) {
		this.obtentionDoctorat = obtentionDoctorat;
	}

	public Specialites getSpecialiteMedecin() {
		return specialiteMedecin;
	}

	public void setSpecialiteMedecin(Specialites specialiteMedecin) {
		this.specialiteMedecin = specialiteMedecin;
	}
	public List<Consultations> getListeConsultations() {
		return listeConsultations;
	}

	public void setListeConsultations(List<Consultations> listeConsultations) {
		this.listeConsultations = listeConsultations;
	}
	
	
}
