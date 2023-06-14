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
 * Contr�leur pour la vue de recherche de patient 
 * G�re les interactions avec les boutons CRUD de la vue
 * En fonction du r�sultat de recherche, il peut �tre r�cup�r� une liste de patients ou patient unique
 * 
 * {indiquer les possibilit�s en terme de Wildcard}
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
	 * V�rifie que la recherche faite par l'utilisateur est conforme � l'attendu du mod�le.
	 * L'idPatient ne permet qu'une recherche exacte et le num�ro de S�curit� de Sociale permet
	 * une recherche avec une wildcard '%'
	 * Retourne vraie si la saisie utilisateur est conforme au crit�re de recherche s�lectionn�
	 * 
	 * @param critereRecherche R�cup�r� � partir d'une liste finie de la vue et s�lectionn� par l'utilisateur
	 * @param champRecherche Saisie manuelle de l'utilisateur � partir du clavier qui est soit un numSS, soit un idPatient
	 * @return {@code true} si les contr�les sont conformes
	 */
	public boolean checkData(String critereRecherche, String champRecherche) {
		String regexNumSS = "[0-9%]+";
		String regexIdPatient = "[0-9]+";
		switch (critereRecherche) {
		case "Num�ro SS":
			return champRecherche.matches(regexNumSS);
		case "idPatient":
			return champRecherche.matches(regexIdPatient);
		}
		return true;
	}
	
	/**
	 * Cr�� le tableau d'affiche pour UN patient � partir de la variable d'instance patientRecherche
	 * 
	 * Si aucun patient n'a �t� trouv� alors la m�thode s'interrompt et l'affichage est inchang�
	 * et un message explicite est affich� � l'utilisateur
	 */
	public void afficherPatient() {
		if (patientRecherche.getIdPatient()==0) {
			vueRecherchePatient.rechercheVide();
			return;
		}
		String title[] = {"idPatient", "Nom", "Pr�nom", "Num�ro SS"};
		String[][] data = new String[1][4] ;
			data[0][0] = String.valueOf(patientRecherche.getIdPatient());
			data[0][1] = patientRecherche.getNom();
			data[0][2] = patientRecherche.getPrenom();
			data[0][3] = patientRecherche.getNumeroSS();

		PatientModelTable model = new PatientModelTable(data, title);
		vueRecherchePatient.getMatriceAffichagePatient().setModel(model);
	}
	
	/**
	 * Cr�� le tableau d'affichage pour une liste de patients (>=2 patients)
	 * Si aucun patient n'a �t� trouv� alors la m�thode s'interrompt et l'affichage est inchang�
	 * et un message explicite est affich� � l'utilisateur
	 */
	public void afficherPatientsListe() {
		if (listePatients.isEmpty()) {
			vueRecherchePatient.rechercheVide();
			return;
		}
		String title[] = {"idPatient", "Nom", "Pr�nom", "Num�ro SS"};
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
	 * Lance la vue de cr�ation de patient 
	 * Une fois le patient cr��, il est possible de le modifier � la vol�e
	 */
	private void vueCreationPatient() {
		VuePatient vap = new VuePatient() ;
		ModelCreationPatient mc = new ModelCreationPatient();
		GestionCreationPatientControleur gcpc = new GestionCreationPatientControleur(vap,mc);
		vap.setVisible(true);
	}
	
	/**
	 * Lance la vue d'affichage de patient
	 * Une fois le patient affich�, il est possible de le modifier
	 * 
	 * @param patient le patient s�lectionn� par l'utilisateur pour affichage 
	 */
	private void vueAffichagePatient(Patients patient) {
		VuePatient vap = new VuePatient() ;
		ModelAffichagePatient ma = new ModelAffichagePatient(patient);
		GestionAffichagePatientControleur gapc = new GestionAffichagePatientControleur(vap,ma);
		vap.setVisible(true);
		}
	
	/**
	 * Passe les crit�res de recherche au mod�le pour r�cu�prer le ou les patients
	 * Utilise la variable d'instance {@code lastRecherche}, qui contient la saisie utilisateur, 
	 * lorsque la m�thode correspondant au crit�re de recherche est trouv�e
	 * 
	 * @param critereRecherche le crit�re de recherche s�lectionn� par l'utilisateur
	 */
	private void getPatients (String critereRecherche) {
		if (checkData(critereRecherche, lastRecherche)==false) {
			vueRecherchePatient.rechercheError(critereRecherche);
		}
		else if (critereRecherche.equals("Nom")) {
			listePatients = modelPatient.lancerRechercheNom(lastRecherche);
			afficherPatientsListe();
		}
		else if (critereRecherche.equals("Num�ro SS")) {
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
	 * R�cup�re le contenu du champ de saisie de l'utilisateur et le passe en param�tre de recherche au mod�le
	 * via la m�thode getPatient(String str)
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
	 * Listerner pour le bouton "D�connexion
	 * Ferme la vue RecherchePatient pour afficher de nouveau la vue EcranConnexion instanci�e au lancement du programme
	 */
	
	class DeconnexionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane jop = new JOptionPane();
			@SuppressWarnings("static-access")
			int option = jop.showConfirmDialog(null,"Voulez-vous vous d�connecter ?","Vous �tes d�connect�",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.OK_OPTION) {
				vueRecherchePatient.dispose();
				vueConnexion.setVisible(true);
			}			
		}
	}
	
	/**
	 * Listener du bouton "Cr�er patient"
	 * Lance la vue de cr�ation de patient
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
	 * Il contr�le qu'une ligne dans le tableau des patients a bien a �t� s�lectionn�e. 
	 * Dans le cas contraire, le listener ne d�clenche aucune action.
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
	 * Supprimer le patient s�lectionn� et rafraichit la tableau des patients pour tenir compte de la suppression.
	 * Avant d'envoyer la suppression au mod�le, une fen�tre de confirmation est affich�e � l'utilisateur pour lui demander
	 * de confirmer son choix. 
	 * 
	 * S'il s'agissait du dernier patient de la liste affich�e, alors un nouveau tableau vide, construit � la vol�e, est affich� � l'utilisateur.
	 * 
	 * Si aucune ligne du tableau des patients n'est s�lectionn�e, le listener ne d�clenche aucune action.
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
			int option = jop.showConfirmDialog(null,"�tes-vous certain de vouloir supprimer le patient : "
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
				                    "idPatient", "Nom", "Pr�nom", "Num�ro SS"
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
	 * Efface l'aide contextuelle par d�faut lorsque fait l'objet du focus
	 * Utilise la variable d'instance {@code lastRecherche} afin de ne pas effacer la pr�c�dente recherche
	 */
	class FocusChampRecherche extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			vueRecherchePatient.setChampRecherche(lastRecherche);
			vueRecherchePatient.getChampRecherche().setForeground(Color.BLACK);
		}
	}
}


	