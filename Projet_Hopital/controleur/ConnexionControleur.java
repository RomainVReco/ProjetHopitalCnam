package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Hopital.Patients;
import Vue.EcranConnexion;
import Vue.VueRechercheAgentPatient;
import connexion.SingleConnection;
import hopital_modeles.ModelConnexion;
import hopital_modeles.ModelGestionPatient;

/**
 * Contrôleur pour l'écran de connexion (login + mdp)
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
	 * En fonction de l'argument {@code str}, lance la vue de gestion correspondante au salarié loggué. 
	 * 
	 * @param str Intitulé d'une fonction d'un salarié de l'hopital, récupéré par la BDD en fonction 
	 * du rôle affecté au login
	 */
	public void choixVue (String str) {
		switch (str) {
		case "Agent d'accueil":
			this.vueConnexion.setVisible(false);
			VueRechercheAgentPatient rp = new VueRechercheAgentPatient();
			ModelGestionPatient gp = new ModelGestionPatient();
			GestionPatientControleur pc = new GestionPatientControleur(rp, gp, vueConnexion);
			break;
		case "Médecin":
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
	 * Listener du bouton "Connexion" de l'écran de connexion.
	 * 
	 * Récupère le login et le MDP renseigné par l'utilisateur pour vérifier en BDD s'il correspond à un rôle donné,
	 * et le cas échéant, passe l'information à la méthode choixVue(String string).
	 * 
	 * Si des informations erronées sont renseignées alors un message d'erreur est affichée.
	 *  
	 * Le listener en profite pour stocker le login dans une variable static, pour l'afficher dans les
	 * autres vues qui seront créées.
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
