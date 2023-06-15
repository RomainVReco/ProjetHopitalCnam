package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Vue.VuePatient;
import hopital_modeles.ModelAffichagePatient;

/**
 * Controleur pour la vue de l'affichage des informations d'un patient, issu d'une recherche avec la vue RecherchePatient
 * 
 * Il gère aussi la modification de ces informations en vérifiant leur cohérence avant de les envoyer au modèle
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
	 * le bouton "Modifier patient", afin d'éviter toute modification par erreur.
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
	
	/**
	 * Listener du bouton "Modifier patient"
	 * 
	 * Une fois cliqué, il rend éditable l'ensemble des champs texte de la vue, sauf idPatient et dateCreation.
	 * Le bouton "Modifier patient" est aussi remplacé par le bouton "Annuler modification".
	 * La variable d'instance @code onGoingModification passe à @code true.
	 * 
	 */
	class ModifierPatientListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
				deverrouillerAffichage();
				vueAffichagePatient.getModifierPatientBouton().setVisible(false);
				vueAffichagePatient.getAnnulerModifierPatient().setVisible(true);
				onGoingModification = true;
			}
		}
	
	/**
	 * Listener du bouton "Annuler modification"
	 * 
	 * Ce bouton n'est disponible que si le bouton "Modifier patient" a été cliqué.
	 * Il annule les modifications réalisées par l'utilisateur :
	 * - En remplaçant les champs textes par les données initialement chargées
	 * - En verrouillant l'ensemble des champs textes pour empecher toute modification ultérieure, sans cliquer de nouveau sur le bouton "Modifier patient"
	 * 
	 * Le bouton "Annuler modification" est alors remplacé par le bouton "Modifier patient"
	 *
	 */
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
	
	/**
	 * Listener du bouton "Valider modification"
	 * 
	 * Ce bouton récupère les informations disponibles dans les champs texte de la vue et les envois au modèle.
	 * 
	 * La longueur du code postal est contrôlée, et s'il est conforme, alors la liste des informations du patient
	 * est récupérée pour :
	 * - être comparée aux informations initialement chargée lors de l'instanciation de la vue,
	 * si aucune information n'a été modifiée alors une message spécifique est affiché à l'utilisateur
	 * - le bouton "Modifier patient" doit avoir été cliqué (variable d'instance onGoingModification==true)
	 * 
	 * La liste des informations patient modifées est alors passée au modèle pour mise à jour dans la base de données.
	 * Un rafraichissement de la vue est opéré en même temps. Si l'enregistrement a réussi, un message est affiché à l'utilisateur
	 * 
	 * Dans le cas où l'enregistrement en base de donnés échoue, un message est affiché 
	 *
	 */
	class validerModificationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean updateStatus = false;
			if (checkCodePostal()==false) return;
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
	
	/**
	 * Teste le code postal modifié par l'utilisateur pour vérifier <= à 10 caractères (contrainte de BDD).
	 * La même méthode est utilisée dans le contrôleur GestionCreationPatientControleur. Pour une maintenance simplifiée,
	 * ce contrôle devrait être remontée dans la création de la vue directement.
	 * 
	 * @return {@code true } s'il est bien <= 10 caractères, {@code false} si > 10 chiffres
	 */
	private boolean checkCodePostal() {
		if (vueAffichagePatient.getChampCodePostal().getText().length()>10) {
			vueAffichagePatient.codePostalFormatError();
			return false;
		}
		else return true;
	}
	
	/**
	 * Récupère les informations du patient à afficher depuis le modèle, stocke ces informations sous forme de liste
	 * qui pourra être comparé aux modifications apportées par l'utilisateur.
	 * 
	 * Les champs de la vue sont alors renseignées avec les informations récupérées. 
	 * 
	 * L'idAdresse de l'adresse du patient est stocké dans une variable d'instance {@code idAdressePatient} dédié.
	 */
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
	
	/**
	 * Récupère et renvoie sous forme de liste les informations du patient modifiées par l'utilisateur.
	 * Au moins une information doit être modifiée pour cette méthode soit appelée.
	 *  
	 * @return liste des informations modifiées du patient
	 */
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
	
	/**
	 * Permet le verrouillage de l'ensemble des champs de la vue pour prévenir d'une modification par erreur
	 */
	private void verrouillerAffichage() {
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
	
	/**
	 * Permet le déverrouillage des champs de la vue, sauf idPatient et dateCreation, pour permettre la modification
	 */
	private void deverrouillerAffichage() {
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
