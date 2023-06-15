package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.EcranConnexion;
import Vue.VueRechercheAgentPatient;
import Vue.VueRechercheConsultation;
import connexion.SingleConnection;
import hopital_modeles.ModelConnexion;
import hopital_modeles.ModelGestionPatient;

/**
 * Contr�leur pour l'�cran de connexion (login + mdp)
 * @author Romain
 *
 */
public class ConnexionControleur {
	private EcranConnexion vueConnexion;
	private ModelConnexion modelConnexion;
	String resultatConnexion="";
	
	public ConnexionControleur(EcranConnexion vueConnexion, ModelConnexion modelConnexion) {
		this.vueConnexion=vueConnexion;
		this.modelConnexion=modelConnexion;
		this.vueConnexion.getBoutonConnexion().addActionListener(new ConnexionListener());
	}
	
	/**
	 * En fonction de l'argument {@code str}, lance la vue de gestion correspondante au salari� loggu�. 
	 * Si str est une chaine vide, alors rien ne se passe
	 * 
	 * @param str Intitul� d'une fonction d'un salari� de l'hopital (typePoste), r�cup�r� par la BDD en fonction 
	 * du r�le affect� au login
	 */
	public void choixVue (String str) {
		switch (str) {
		case "Agent d'accueil":
			this.vueConnexion.setVisible(false);
			VueRechercheAgentPatient rp = new VueRechercheAgentPatient();
			ModelGestionPatient gp = new ModelGestionPatient();
			GestionPatientControleur pc = new GestionPatientControleur(rp, gp, vueConnexion);
			break;
		// � des fins de test, l'affichage de la vue de recherche de consultation a �t� impl�ment�e mais n'est pr�sentement pas fonctionnelle
		case "M�decin":
			this.vueConnexion.setVisible(false);
			VueRechercheConsultation rc = new VueRechercheConsultation();
			rc.setVisible(true);
			break;
		case "Administrateur":
			break;
		case "Technicien":
			break;
		case "":
			break;
		}
	}
	
	/**
	 * Listener du bouton "Connexion" de l'�cran de connexion.
	 * 
	 * R�cup�re le login et le MDP renseign� par l'utilisateur pour v�rifier en BDD s'il correspond � un r�le donn�,
	 * et le cas �ch�ant, passe l'information � la m�thode choixVue(String string).
	 * 
	 * Si des informations erron�es sont renseign�es alors un message d'erreur est affich�e.
	 *  
	 * Le listener en profite pour stocker le login dans une variable static, pour l'afficher dans les
	 * autres vues qui seront cr��es.
	 */
	class ConnexionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String login = vueConnexion.getLoginField().getText();
			String password =  vueConnexion.getPasswordField().getText();
			resultatConnexion = modelConnexion.getConnexion(login,password);
			if (resultatConnexion.isBlank()) {
				vueConnexion.connexionError();
			}
			else vueConnexion.connexionGranted(resultatConnexion);
			SingleConnection.setNomSalarie(login);
			choixVue(resultatConnexion);
		}
	}
}
