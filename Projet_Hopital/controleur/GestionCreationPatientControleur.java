package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import Hopital.Patients;
import Vue.VuePatient;
import hopital_modeles.ModelCreationPatient;

/**
 * Contr�leur pour la cr�ation d'un nouveau patient
 * 
 * Une fois le patient cr��, il peut �tre modifi� dans la foul�e
 * @author Romain
 *
 */

public class GestionCreationPatientControleur {
	VuePatient vueCreationPatient;
	ModelCreationPatient modelCreationPatient;
	int idAdressePatient;
	List<String> listeInfosPatientModel = new ArrayList<>();
	boolean onGoingModification = false;
	boolean onGoingCreation = true;
	Patients patientCree ;
	
	public GestionCreationPatientControleur (VuePatient vueAffichPatient, ModelCreationPatient modelCreationPatient) {
		this.vueCreationPatient=vueAffichPatient;
		this.modelCreationPatient=modelCreationPatient;
		this.vueCreationPatient.getModifierPatientBouton().addActionListener(new ModifierPatientListener());
		this.vueCreationPatient.getModifierPatientBouton().setEnabled(false);
		this.vueCreationPatient.getAnnulerModifierPatient().addActionListener(new AnnulerModifierPatientListener());
		this.vueCreationPatient.getAnnulerModifierPatient().setEnabled(false);
		this.vueCreationPatient.getValiderModifBouton().addActionListener(new validerModificationListener());
		this.vueCreationPatient.getValiderModifBouton().setEnabled(false);
		this.vueCreationPatient.getCreationPatientBouton().addActionListener(new CreerPatientListener());
		this.vueCreationPatient.getChampIdPatient().setText(genererIdPatient());
		this.vueCreationPatient.getChampDateCreationPatient().setText(generateDateCreation());
	}
	
	/**
	 * Demande au mod�le la cr�ation d'un nouvel identifiant unique pour un patient
	 * 
	 * @return un nouvel identifiant unique de type String
	 */
	private String genererIdPatient() {
		int idPatientACreer ;
		idPatientACreer = modelCreationPatient.generateUniquePatientId();
		return String.valueOf(idPatientACreer);
	}
	
	/**
	 * Demande au mod�le la date de cr�ation du patient au format dd-MM-yyyy HH:mm:ss
	 * 
	 * @return une date au format String
	 */
	private String generateDateCreation() {
		DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateCreation = LocalDateTime.now() ;
		return dateCreation.format(CUSTOM_FORMATTER);
	}
	
	/**
	 * Contr�le le format du code postal 
	 * 
	 * @return {@code true} si CP < 10 caract�res
	 */
	private boolean controleLongueurCodePostal() {
		if (vueCreationPatient.getChampCodePostal().getText().length()>10) {
			vueCreationPatient.codePostalFormatError();
			return false;
		}
		else return true;
	}

	/**
	 * Listener permettant la cr�ation d'un patient
	 * 
	 * Pour qu'un patient soit cr��, il faut que l'ensemble des champs de la vue soient renseign�s,
	 * Le code postal ne peut pas �tre sup�rieur � 10 chiffres.
	 * Dans le cas contraire, le bouton "Cr�er patient" ne fait rien.
	 * 
	 * Une fois le patient cr��, les champs deviennent non �ditables. Le bouton "Cr�er patient" est d�sactiv�.
	 */
	class CreerPatientListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean creationStatus = false;
			int champTexteComplet = 0;
			JTextField[] tableTemp = vueCreationPatient.getJTextField();
			for (JTextField jt : tableTemp) {
				if (!jt.getText().isBlank()) champTexteComplet=champTexteComplet+1;
			}
			if (champTexteComplet!=tableTemp.length) return;
			if (controleLongueurCodePostal()==false) return;
			else creationStatus = creerPatient();
			
			if (creationStatus==true) {
				vueCreationPatient.creationPatientSuccess();
				onGoingCreation = false;
				verrouillerAffichage();
				vueCreationPatient.getModifierPatientBouton().setEnabled(true);
				vueCreationPatient.getCreationPatientBouton().setEnabled(false);
			}
			else vueCreationPatient.creationPatientFailed();
		}
	}

	/**
	 * Listener pour le bouton "Modifier Patient"
	 * 
	 * Ce bouton n'est utilisable que si un patient a �t� cr��. 
	 * Lorsqu'un patient est en cours de modification, ce bouton est remplac� par un bouton "Annuler modification" 
	 */
	class ModifierPatientListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
				deverrouillerAffichage();
				vueCreationPatient.getModifierPatientBouton().setVisible(false);
				vueCreationPatient.getAnnulerModifierPatient().setVisible(true);
				vueCreationPatient.getAnnulerModifierPatient().setEnabled(true);
				vueCreationPatient.getValiderModifBouton().setEnabled(true);
				onGoingModification = true;
			}
		}
	/**
	 * Listener pour le bouton "Annuler modification"
	 * 
	 * Ce bouton est utilisable lorsque les informations d'un patient nouvellement cr�� sont en cours de modification
	 * Il permet le retour arri�re aux informations initiales et verrouille � nouveau les champs textes.
	 */
	class AnnulerModifierPatientListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			extrairePatient();
			verrouillerAffichage();
			onGoingModification = false;
			vueCreationPatient.getAnnulerModifierPatient().setVisible(false);
			vueCreationPatient.getModifierPatientBouton().setVisible(true);
		}
	}
	
	/**
	 * Listener pour le bouton "Valider modification"
	 * 
	 * Ce bouton est utilisable lorsqu'un patient a �t� cr�� et que le bouton "Modifier patient" a �t� cliqu�.
	 * Si aucune information n'a �t� modifi�e, alors un message d'information s'affiche
	 * Si la mise � jour �choue en BDD, alors un message s'affiche
	 */
	class validerModificationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean updateStatus = false;
			List<String> infosPatientModifiees = creerPatientModifier();
			if (listeInfosPatientModel.equals(infosPatientModifiees)||onGoingModification==false) {
				vueCreationPatient.sameInfoPatientError();
				return;
			}
			else {
				updateStatus = modelCreationPatient.modifierPatient(infosPatientModifiees,idAdressePatient);
				extrairePatient();
			}
			if (updateStatus==false) vueCreationPatient.updatePatientFailed();
			else {
				vueCreationPatient.updatePatientSuccess();
			}
		}
	}
	
	/**
	 * M�thode de cr�ation d'un nouveau patient
	 * Les informations du patient sont r�cup�r�es par la contr�leur et transmis au mod�le gr�ce � une liste
	 * 
	 * @return {@code true} si patient a pu �tre cr�� en BDD
	 */
	private boolean creerPatient () {
		
		List<String> infosPatient = new ArrayList<>();
		String nomPatient = vueCreationPatient.getChampNomPatient().getText();
		String prenomPatient = vueCreationPatient.getChampPrenomPatient().getText();
		String numSS = vueCreationPatient.getChampNumeroSSPatient().getText();
		String dateCreation = vueCreationPatient.getChampDateCreationPatient().getText();
		String idPatient = vueCreationPatient.getChampIdPatient().getText();
		infosPatient.add(nomPatient);
		infosPatient.add(prenomPatient);
		infosPatient.add(numSS);
		infosPatient.add(dateCreation);
		infosPatient.add(idPatient);
		
		List<String> infosAdresse = new ArrayList<>();
		String numAdresse = vueCreationPatient.getChampNumAdresse().getText();
		String adresse1 = vueCreationPatient.getChampAdresse1().getText();
		String adresse2 = vueCreationPatient.getChampAdresse2().getText();
		String codePostal = vueCreationPatient.getChampCodePostal().getText();
		String ville = vueCreationPatient.getChampVille().getText();
		String pays = vueCreationPatient.getChampPays().getText();
		infosAdresse.add(numAdresse);
		infosAdresse.add(adresse1);
		infosAdresse.add(adresse2);
		infosAdresse.add(codePostal);
		infosAdresse.add(ville);
		infosAdresse.add(pays);
		
		return modelCreationPatient.creerPatientModel(infosPatient, infosAdresse);
		
	}
	
	/**
	 * M�thode utilis�e uniquement si un patient cr�� a �t� modifi�. 
	 * Les informations mises � jour sont transmises � a la vue.
	 */
	public void extrairePatient() {
		String nomPatient = modelCreationPatient.getPatientCree().getNom();
		String prenomPatient = modelCreationPatient.getPatientCree().getPrenom();
		String numSS = modelCreationPatient.getPatientCree().getNumeroSS();
		String dateCreation = modelCreationPatient.getPatientCree().getDateCreation();
		String idPatient = String.valueOf(modelCreationPatient.getPatientCree().getIdPatient());
		
		String numAdresse = modelCreationPatient.getAdressePatient().getNumero();
		String adresse1 = modelCreationPatient.getAdressePatient().getAdresse1();
		String adresse2 = modelCreationPatient.getAdressePatient().getAdresse2();
		String codePostal = modelCreationPatient.getAdressePatient().getCodePostal();
		String ville = modelCreationPatient.getAdressePatient().getVille();
		String pays = modelCreationPatient.getAdressePatient().getPays();
		idAdressePatient = modelCreationPatient.getAdressePatient().getIdAdresse();
		
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
		
		vueCreationPatient.setChampNomPatient(nomPatient);
		vueCreationPatient.setChampPrenomPatient(prenomPatient);
		vueCreationPatient.setChampNumeroSSPatient(numSS);
		vueCreationPatient.setChampDateCreationPatient(dateCreation);
		vueCreationPatient.setChampIdPatient(idPatient);
		
		vueCreationPatient.setChampNumAdresse(numAdresse);
		vueCreationPatient.setChampAdresse1(adresse1);
		vueCreationPatient.setChampAdresse2(adresse2);
		vueCreationPatient.setChampCodePostal(codePostal);
		vueCreationPatient.setChampVille(ville);
		vueCreationPatient.setChampPays(pays);

	}
	
	/**
	 * M�thode appel�e lorsque les modifications d'un patient sont valid�es afin de renvoyer une liste de l'ensemble des infos,
	 * qui sera elle-m�me transmise au mod�le
	 * @return
	 */
	private List<String> creerPatientModifier () {
		List<String> listeInfosPatient = new ArrayList<>();
		String nomPatient = vueCreationPatient.getChampNomPatient().getText();
		String prenomPatient = vueCreationPatient.getChampPrenomPatient().getText();
		String numSS = vueCreationPatient.getChampNumeroSSPatient().getText();
		String idPatient = vueCreationPatient.getChampIdPatient().getText();
		
		String numAdresse = vueCreationPatient.getChampNumAdresse().getText();
		String adresse1 = vueCreationPatient.getChampAdresse1().getText();
		String adresse2 = vueCreationPatient.getChampAdresse2().getText();
		String codePostal = vueCreationPatient.getChampCodePostal().getText();
		String ville = vueCreationPatient.getChampVille().getText();
		String pays = vueCreationPatient.getChampPays().getText();
		
		listeInfosPatient.add(nomPatient);
		listeInfosPatient.add(prenomPatient);
		listeInfosPatient.add(numSS);
		listeInfosPatient.add(idPatient);
		
		listeInfosPatient.add(numAdresse);
		listeInfosPatient.add(adresse1);
		listeInfosPatient.add(adresse2);
		listeInfosPatient.add(codePostal);
		listeInfosPatient.add(ville);
		listeInfosPatient.add(pays);
		
		return listeInfosPatient;
	}
	
	/**
	 * Permet le verrouillage de l'ensemble des champs de la vue pour pr�venir d'une modification par erreur
	 */
	private void verrouillerAffichage() {
		vueCreationPatient.getChampNomPatient().setEditable(false);
		vueCreationPatient.getChampPrenomPatient().setEditable(false);
		vueCreationPatient.getChampNumeroSSPatient().setEditable(false);
		
		vueCreationPatient.getChampNumAdresse().setEditable(false);
		vueCreationPatient.getChampAdresse1().setEditable(false);
		vueCreationPatient.getChampAdresse2().setEditable(false);
		vueCreationPatient.getChampCodePostal().setEditable(false);
		vueCreationPatient.getChampVille().setEditable(false);
		vueCreationPatient.getChampPays().setEditable(false);
	}
	
	/**
	 * Permet le d�verrouillage des champs de la vue, sauf idPatient et dateCreation, pour permettre la modification
	 */
	private void deverrouillerAffichage() {
		vueCreationPatient.getChampNomPatient().setEditable(true);
		vueCreationPatient.getChampPrenomPatient().setEditable(true);
		vueCreationPatient.getChampNumeroSSPatient().setEditable(true);
		
		vueCreationPatient.getChampNumAdresse().setEditable(true);
		vueCreationPatient.getChampAdresse1().setEditable(true);
		vueCreationPatient.getChampAdresse2().setEditable(true);
		vueCreationPatient.getChampCodePostal().setEditable(true);
		vueCreationPatient.getChampVille().setEditable(true);
		vueCreationPatient.getChampPays().setEditable(true);
	}


}
