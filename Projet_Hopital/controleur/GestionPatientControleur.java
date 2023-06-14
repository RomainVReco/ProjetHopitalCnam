package controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Hopital.Patients;
import Vue.EcranConnexion;
import Vue.PatientModelTable;
import Vue.VuePatient;
import Vue.VueRechercheAgentPatient;
import connexion.SingleConnection;
import hopital_modeles.ModelAffichagePatient;
import hopital_modeles.ModelCreationPatient;
import hopital_modeles.ModelGestionPatient;

/**
 * Contrôleur pour la vue de recherche de patient 
 * Gère les interactions avec les boutons CRUD de la vue
 * En fonction du résultat de recherche, il peut être récupéré une liste de patients ou patient unique
 * 
 * {indiquer les possibilités en terme de Wildcard}
 * 
 * @author Romain
 *
 */

public class GestionPatientControleur {
	private VueRechercheAgentPatient vueRecherchePatient;
	private ModelGestionPatient modelPatient;
	private List<Patients> listePatients = new ArrayList<>();
	private Patients patientRecherche ;
	private EcranConnexion vueConnexion;
	private String lastRecherche="";
	
	public GestionPatientControleur (VueRechercheAgentPatient vuePatient, ModelGestionPatient modelPatient,
			EcranConnexion vueConnexion) {
		this.vueRecherchePatient=vuePatient;
		this.modelPatient=modelPatient;
		this.vueConnexion=vueConnexion;
		this.vueRecherchePatient.setNomSalarieUtilisateur(SingleConnection.getNomSalarie());
		this.vueRecherchePatient.getDeconnexion().addActionListener(new DeconnexionListener());
		this.vueRecherchePatient.getLancerRechercheBouton().addActionListener(new RechercheListener());
		this.vueRecherchePatient.getChampRecherche().addFocusListener(new FocusChampRecherche());
		this.vueRecherchePatient.getCreationPatientBouton().addActionListener(new CreerPatientListener());
		this.vueRecherchePatient.getAfficherPatientBouton().addActionListener(new AfficherPatientListener());
		this.vueRecherchePatient.getSupprimerPatientBouton().addActionListener(new SupprimerPatientListener());
	}
	
	/**
	 * Vérifie que la recherche faite par l'utilisateur est conforme à l'attendu du modèle.
	 * L'idPatient ne permet qu'une recherche exacte et le numéro de Sécurité de Sociale permet
	 * une recherche avec une wildcard '%'
	 * Retourne vraie si la saisie utilisateur est conforme au critère de recherche sélectionné
	 * 
	 * @param critereRecherche Récupéré à partir d'une liste finie de la vue et sélectionné par l'utilisateur
	 * @param champRecherche Saisie manuelle de l'utilisateur à partir du clavier qui est soit un numSS, soit un idPatient
	 * @return {@code true} si les contrôles sont conformes
	 */
	public boolean checkData(String critereRecherche, String champRecherche) {
		String regexNumSS = "[0-9%]+";
		String regexIdPatient = "[0-9]+";
		switch (critereRecherche) {
		case "Numéro SS":
			return champRecherche.matches(regexNumSS);
		case "idPatient":
			return champRecherche.matches(regexIdPatient);
		}
		return true;
	}
	
	/**
	 * Créé le tableau d'affiche pour UN patient à partir de la variable d'instance patientRecherche
	 * 
	 * Si aucun patient n'a été trouvé alors la méthode s'interrompt et l'affichage est inchangé
	 * et un message explicite est affiché à l'utilisateur
	 */
	public void afficherPatient() {
		if (patientRecherche.getIdPatient()==0) {
			vueRecherchePatient.rechercheVide();
			return;
		}
		String title[] = {"idPatient", "Nom", "Prénom", "Numéro SS"};
		String[][] data = new String[1][4] ;
			data[0][0] = String.valueOf(patientRecherche.getIdPatient());
			data[0][1] = patientRecherche.getNom();
			data[0][2] = patientRecherche.getPrenom();
			data[0][3] = patientRecherche.getNumeroSS();

		PatientModelTable model = new PatientModelTable(data, title);
		vueRecherchePatient.getMatriceAffichagePatient().setModel(model);
	}
	
	/**
	 * Créé le tableau d'affichage pour une liste de patients (>=2 patients)
	 * Si aucun patient n'a été trouvé alors la méthode s'interrompt et l'affichage est inchangé
	 * et un message explicite est affiché à l'utilisateur
	 */
	public void afficherPatientsListe() {
		if (listePatients.isEmpty()) {
			vueRecherchePatient.rechercheVide();
			return;
		}
		String title[] = {"idPatient", "Nom", "Prénom", "Numéro SS"};
		String[][] data = new String[listePatients.size()][4] ;
		int i = 0;
		for (Patients p : listePatients) {
			data[i][0] = String.valueOf(p.getIdPatient());
			data[i][1] = p.getNom();
			data[i][2] = p.getPrenom();
			data[i][3] = p.getNumeroSS();
			i++;
		}
		PatientModelTable model = new PatientModelTable(data, title);
		vueRecherchePatient.getMatriceAffichagePatient().setModel(model);
		
	}
	
	/**
	 * Lance la vue de création de patient 
	 * Une fois le patient créé, il est possible de le modifier à la volée
	 */
	private void vueCreationPatient() {
		VuePatient vap = new VuePatient() ;
		ModelCreationPatient mc = new ModelCreationPatient();
		GestionCreationPatientControleur gcpc = new GestionCreationPatientControleur(vap,mc);
		vap.setVisible(true);
	}
	
	/**
	 * Lance la vue d'affichage de patient
	 * Une fois le patient affiché, il est possible de le modifier
	 * 
	 * @param patient le patient sélectionné par l'utilisateur pour affichage 
	 */
	private void vueAffichagePatient(Patients patient) {
		VuePatient vap = new VuePatient() ;
		ModelAffichagePatient ma = new ModelAffichagePatient(patient);
		GestionAffichagePatientControleur gapc = new GestionAffichagePatientControleur(vap,ma);
		vap.setVisible(true);
		}
	
	/**
	 * Passe les critères de recherche au modèle pour récuéprer le ou les patients
	 * Utilise la variable d'instance {@code lastRecherche}, qui contient la saisie utilisateur, 
	 * lorsque la méthode correspondant au critère de recherche est trouvée
	 * 
	 * @param critereRecherche le critère de recherche sélectionné par l'utilisateur
	 */
	private void getPatients (String critereRecherche) {
		if (checkData(critereRecherche, lastRecherche)==false) {
			vueRecherchePatient.rechercheError(critereRecherche);
		}
		else if (critereRecherche.equals("Nom")) {
			listePatients = modelPatient.lancerRechercheNom(lastRecherche);
			afficherPatientsListe();
		}
		else if (critereRecherche.equals("Numéro SS")) {
			listePatients = modelPatient.lancerRechercheNumSS(lastRecherche);
			afficherPatientsListe();
		}
		else {
			patientRecherche = modelPatient.lancerRechercheIdPatient(lastRecherche);
			afficherPatient(); 
		}
	}

	/**
	 * Listener pour le bouton "Rechercher"
	 * Récupère le contenu du champ de saisie de l'utilisateur et le passe en paramètre de recherche au modèle
	 * via la méthode getPatient(String str)
	 */
	
	class RechercheListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lastRecherche = vueRecherchePatient.getChampRecherche().getText();
			String critereRecherche = vueRecherchePatient.getChoixCritereRecherche().getSelectedItem().toString();
			getPatients(critereRecherche);
	}
	}
	
	/**
	 * Listerner pour le bouton "Déconnexion
	 * Ferme la vue RecherchePatient pour afficher de nouveau la vue EcranConnexion instanciée au lancement du programme
	 */
	
	class DeconnexionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane jop = new JOptionPane();
			@SuppressWarnings("static-access")
			int option = jop.showConfirmDialog(null,"Voulez-vous vous déconnecter ?","Vous êtes déconnecté",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.OK_OPTION) {
				vueRecherchePatient.dispose();
				vueConnexion.setVisible(true);
			}			
		}
	}
	
	/**
	 * Listener du bouton "Créer patient"
	 * Lance la vue de création de patient
	 */
	class CreerPatientListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			vueCreationPatient();	
		}
	}
	
	/**
	 * Listener du bouton "Afficher patient"
	 * Lance la vue d'affichage du patient
	 * Il contrôle qu'une ligne dans le tableau des patients a bien a été sélectionnée. 
	 * Dans le cas contraire, le listener ne déclenche aucune action.
	 */
	class AfficherPatientListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String idPatient = "";	
			int rowSelected ;
			rowSelected = vueRecherchePatient.getMatriceAffichagePatient().getSelectedRow();
			if (rowSelected==-1) return;

			PatientModelTable modelTemp = (PatientModelTable) vueRecherchePatient.getMatriceAffichagePatient().getModel();
			idPatient = (String) modelTemp.getValueAt(rowSelected, 0);
	
			if (idPatient==null) return;
			patientRecherche = modelPatient.lancerRechercheIdPatient(idPatient);
			vueAffichagePatient(patientRecherche);
		}
	}
	
	/**
	 * Listener du bouton "Supprimer patient"
	 * Supprimer le patient sélectionné et rafraichit la tableau des patients pour tenir compte de la suppression.
	 * Avant d'envoyer la suppression au modèle, une fenêtre de confirmation est affichée à l'utilisateur pour lui demander
	 * de confirmer son choix. 
	 * 
	 * S'il s'agissait du dernier patient de la liste affichée, alors un nouveau tableau vide, construit à la volée, est affiché à l'utilisateur.
	 * 
	 * Si aucune ligne du tableau des patients n'est sélectionnée, le listener ne déclenche aucune action.
	 */
	class SupprimerPatientListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int rowSelected;
			Integer idPatient ;
			rowSelected = vueRecherchePatient.getMatriceAffichagePatient().getSelectedRow();
			if (rowSelected==-1) return;
			PatientModelTable modelTemp = (PatientModelTable) vueRecherchePatient.getMatriceAffichagePatient().getModel();
			
			if (modelTemp.getValueAt(rowSelected, 0)==null) return;
			idPatient =  Integer.valueOf((String) modelTemp.getValueAt(rowSelected, 0));
			String nomPatient = (String) modelTemp.getValueAt(rowSelected, 1);
			String PrenomPatient = (String) modelTemp.getValueAt(rowSelected, 2);
			String str = nomPatient+" "+PrenomPatient;
			
			JOptionPane jop = new JOptionPane();
			@SuppressWarnings("static-access")
			int option = jop.showConfirmDialog(null,"Êtes-vous certain de vouloir supprimer le patient : "
					+str+", idPatient "+idPatient+" ?","Suppression du patient "+str+" en cours.",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.OK_OPTION) {
				if (modelPatient.supprimerPatient(idPatient)) {
					vueRecherchePatient.suppressionPatientSuccess(str);
					if (vueRecherchePatient.getMatriceAffichagePatient().getModel().getRowCount()<=1) {
						@SuppressWarnings("serial")
						PatientModelTable patientModelTable = new PatientModelTable(
				                new String [][] {
				                    {null, null, null, null},
				                    {null, null, null, null},
				                    {null, null, null, null},
				                    {null, null, null, null}
				                },
				                new String [] {
				                    "idPatient", "Nom", "Prénom", "Numéro SS"
				                }
				            ) {
				                Class[] types = new Class [] {
				                    Integer.class, String.class, String.class, String.class
				                };
				                boolean[] canEdit = new boolean [] {
				                    false, false, false, false
				                };

				                public Class getColumnClass(int columnIndex) {
				                    return types [columnIndex];
				                }

				                public boolean isCellEditable(int rowIndex, int columnIndex) {
				                    return false;
				                }
				            };
				         vueRecherchePatient.getMatriceAffichagePatient().setModel(patientModelTable);
					}
					
					else  {
						String critereRecherche = vueRecherchePatient.getChoixCritereRecherche().getSelectedItem().toString();
						getPatients(critereRecherche);
					}
				}
				else vueRecherchePatient.suppressionPatientFailed(str);
			}
		}
	}
	
	/**
	 * Listener du champ de saisie de l'utilisateur
	 * Efface l'aide contextuelle par défaut lorsque fait l'objet du focus
	 * Utilise la variable d'instance {@code lastRecherche} afin de ne pas effacer la précédente recherche
	 */
	class FocusChampRecherche extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			vueRecherchePatient.setChampRecherche(lastRecherche);
			vueRecherchePatient.getChampRecherche().setForeground(Color.BLACK);
		}
	}
}


	