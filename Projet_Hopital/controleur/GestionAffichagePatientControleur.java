package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Vue.VuePatient;
import hopital_modeles.ModelAffichagePatient;

/**
 * Controleur pour la vue de l'affichage des informations d'un patient, issu d'une recherche avec la vue RecherchePatient
 * @author Romain
 *
 */
public class GestionAffichagePatientControleur {
	VuePatient vueAffichagePatient;
	ModelAffichagePatient modelAffichagePatient;
	int idAdressePatient;
	List<String> listeInfosPatientModel = new ArrayList<>();
	boolean onGoingModification = false;

	/**
	 * Contructeur du controleur
	 * 
	 * Lors de sa création, il désactive le bouton "Création patient" qui ne sera pas utilisé dans cette vue 
	 * Il verrouille aussi l'ensemble des champs texte. En effet, l'édition ne peut se faire que si l'utilisateur clique sur 
	 * le bouton "Modifier patient", afin d'éviter toute modification par erreur
	 * 
	 * @param vueAffichPatient vue à afficher
	 * @param modelAffichagePatient modèle à utiliser
	 */
	public GestionAffichagePatientControleur (VuePatient vueAffichPatient, ModelAffichagePatient modelAffichagePatient) {
		this.vueAffichagePatient=vueAffichPatient;
		this.modelAffichagePatient=modelAffichagePatient;
		this.vueAffichagePatient.getCreationPatientBouton().setEnabled(false);
		extrairePatient();
		verrouillerAffichage();
		this.vueAffichagePatient.getModifierPatientBouton().addActionListener(new ModifierPatientListener());
		this.vueAffichagePatient.getAnnulerModifierPatient().addActionListener(new AnnulerModifierPatientListener());
		this.vueAffichagePatient.getValiderModifBouton().addActionListener(new validerModificationListener());
	}
	

	class ModifierPatientListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
				deverrouillerAffichage();
				vueAffichagePatient.getModifierPatientBouton().setVisible(false);
				vueAffichagePatient.getAnnulerModifierPatient().setVisible(true);
				onGoingModification = true;
			}
		}
		
	class AnnulerModifierPatientListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			extrairePatient();
			verrouillerAffichage();
			onGoingModification = false;
			vueAffichagePatient.getAnnulerModifierPatient().setVisible(false);
			vueAffichagePatient.getModifierPatientBouton().setVisible(true);
		}
	}
	
	class validerModificationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean updateStatus = false;
			List<String> infosPatientModifiees = creerPatientModifier();
			if (listeInfosPatientModel.equals(infosPatientModifiees)||onGoingModification==false) {
				vueAffichagePatient.sameInfoPatientError();
				return;
			}
			else {
				updateStatus = modelAffichagePatient.modifierPatient(infosPatientModifiees,idAdressePatient);
				extrairePatient();
			}
			if (updateStatus==false) vueAffichagePatient.updatePatientFailed();
			else vueAffichagePatient.updatePatientSuccess();
		}
	}
	
	public void extrairePatient() {
		String nomPatient = modelAffichagePatient.getPatientAffiche().getNom();
		String prenomPatient = modelAffichagePatient.getPatientAffiche().getPrenom();
		String numSS = modelAffichagePatient.getPatientAffiche().getNumeroSS();
		String dateCreation = modelAffichagePatient.getPatientAffiche().getDateCreation();
		String idPatient = String.valueOf(modelAffichagePatient.getPatientAffiche().getIdPatient());
		
		String numAdresse = modelAffichagePatient.getAdressePatient().getNumero();
		String adresse1 = modelAffichagePatient.getAdressePatient().getAdresse1();
		String adresse2 = modelAffichagePatient.getAdressePatient().getAdresse2();
		String codePostal = modelAffichagePatient.getAdressePatient().getCodePostal();
		String ville = modelAffichagePatient.getAdressePatient().getVille();
		String pays = modelAffichagePatient.getAdressePatient().getPays();
		String lastModifiedAdresse = modelAffichagePatient.getAdressePatient().getDateCreationAdresse();
		
		listeInfosPatientModel.add(nomPatient);
		listeInfosPatientModel.add(prenomPatient);
		listeInfosPatientModel.add(numSS);
		listeInfosPatientModel.add(dateCreation);
		listeInfosPatientModel.add(idPatient);

		listeInfosPatientModel.add(numAdresse);
		listeInfosPatientModel.add(adresse1);
		listeInfosPatientModel.add(adresse2);
		listeInfosPatientModel.add(codePostal);
		listeInfosPatientModel.add(ville);
		listeInfosPatientModel.add(pays);
		listeInfosPatientModel.add(lastModifiedAdresse);
				
		vueAffichagePatient.setChampNomPatient(nomPatient);
		vueAffichagePatient.setChampPrenomPatient(prenomPatient);
		vueAffichagePatient.setChampNumeroSSPatient(numSS);
		vueAffichagePatient.setChampDateCreationPatient(dateCreation);
		vueAffichagePatient.setChampIdPatient(idPatient);
		
		vueAffichagePatient.setChampNumAdresse(numAdresse);
		vueAffichagePatient.setChampAdresse1(adresse1);
		vueAffichagePatient.setChampAdresse2(adresse2);
		vueAffichagePatient.setChampCodePostal(codePostal);
		vueAffichagePatient.setChampVille(ville);
		vueAffichagePatient.setChampPays(pays);
		idAdressePatient = modelAffichagePatient.getAdressePatient().getIdAdresse();
	}
	
	private List<String> creerPatientModifier () {
		List<String> listeInfosPatient = new ArrayList<>();
		String nomPatient = vueAffichagePatient.getChampNomPatient().getText();
		String prenomPatient = vueAffichagePatient.getChampPrenomPatient().getText();
		String numSS = vueAffichagePatient.getChampNumeroSSPatient().getText();
		String dateCreation = vueAffichagePatient.getChampDateCreationPatient().getText();
		String idPatient = vueAffichagePatient.getChampIdPatient().getText();
		
		String numAdresse = vueAffichagePatient.getChampNumAdresse().getText();
		String adresse1 = vueAffichagePatient.getChampAdresse1().getText();
		String adresse2 = vueAffichagePatient.getChampAdresse2().getText();
		String codePostal = vueAffichagePatient.getChampCodePostal().getText();
		String ville = vueAffichagePatient.getChampVille().getText();
		String pays = vueAffichagePatient.getChampPays().getText();
		String lastModifiedAdresse = modelAffichagePatient.getAdressePatient().getDateCreationAdresse();
		
		listeInfosPatient.add(nomPatient);
		listeInfosPatient.add(prenomPatient);
		listeInfosPatient.add(numSS);
		listeInfosPatient.add(dateCreation);
		listeInfosPatient.add(idPatient);
		
		listeInfosPatient.add(numAdresse);
		listeInfosPatient.add(adresse1);
		listeInfosPatient.add(adresse2);
		listeInfosPatient.add(codePostal);
		listeInfosPatient.add(ville);
		listeInfosPatient.add(pays);
		listeInfosPatient.add(lastModifiedAdresse);
		
		return listeInfosPatient;
	}
	
	public void verrouillerAffichage() {
		vueAffichagePatient.getChampNomPatient().setEditable(false);
		vueAffichagePatient.getChampPrenomPatient().setEditable(false);
		vueAffichagePatient.getChampNumeroSSPatient().setEditable(false);
		
		vueAffichagePatient.getChampNumAdresse().setEditable(false);
		vueAffichagePatient.getChampAdresse1().setEditable(false);
		vueAffichagePatient.getChampAdresse2().setEditable(false);
		vueAffichagePatient.getChampCodePostal().setEditable(false);
		vueAffichagePatient.getChampVille().setEditable(false);
		vueAffichagePatient.getChampPays().setEditable(false);
	}
	
	public void deverrouillerAffichage() {
		vueAffichagePatient.getChampNomPatient().setEditable(true);
		vueAffichagePatient.getChampPrenomPatient().setEditable(true);
		vueAffichagePatient.getChampNumeroSSPatient().setEditable(true);
		
		vueAffichagePatient.getChampNumAdresse().setEditable(true);
		vueAffichagePatient.getChampAdresse1().setEditable(true);
		vueAffichagePatient.getChampAdresse2().setEditable(true);
		vueAffichagePatient.getChampCodePostal().setEditable(true);
		vueAffichagePatient.getChampVille().setEditable(true);
		vueAffichagePatient.getChampPays().setEditable(true);
	}

}
