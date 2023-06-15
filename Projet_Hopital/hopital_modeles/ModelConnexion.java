package hopital_modeles;

import DAO.LoginsDAO;
import connexion.SingleConnection;

/**
 * Modèle de la vue Connexion
 * 
 * A partir d'un login et d'un password passé en paramètre, vérifie si ce couple existe en base de données 
 * pour renvoyer l'emploi occupé par l'utilisateur au contrôleur.
 *  
 * @author Romain
 *
 */
public class ModelConnexion {
	
	String login ="";
	String password = "";
	LoginsDAO loginDAO;
	String StatutSalarie="";
	
	public ModelConnexion(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public ModelConnexion() {
		super();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Vérifie l'existence du couple login + password saisis par l'utilisateur dans la base de données via un DAO
	 * et récupère le type de poste correspondant.
	 * 
	 * Si aucune correspondance n'est trouvée alors renvoie une chaine de caractères vide.
	 * 
	 * @param login le login renseigné par l'utilisateur sur la vue EcranConnexion et transmis par le contrôleur 
	 * @param password le mot de passe saisie par l'utilisateur sur la vue EcranConnexion et transmis par le contrôleur
	 * @return le type de poste occupé par l'utilisateur. Le contrôleur s'en servira pour choisir quelle vue affichée
	 */
	public String getConnexion(String login, String password) {
		loginDAO = new LoginsDAO(SingleConnection.getInstance());
		if (loginDAO.findByLoginPassword(login,password).isPresent()) {
			String typePoste = loginDAO.findByLoginPassword(login, password).get().getTypePoste();
			this.StatutSalarie=typePoste;
			return typePoste;
		}
		else return "";
	}
	
	
	
	

}
