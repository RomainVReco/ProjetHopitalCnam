package hopital_modeles;

import DAO.LoginsDAO;
import connexion.SingleConnection;

/**
 * Mod�le de la vue Connexion
 * 
 * A partir d'un login et d'un password pass� en param�tre, v�rifie si ce couple existe en base de donn�es 
 * pour renvoyer l'emploi occup� par l'utilisateur au contr�leur.
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
	 * V�rifie l'existence du couple login + password saisis par l'utilisateur dans la base de donn�es via un DAO
	 * et r�cup�re le type de poste correspondant.
	 * 
	 * Si aucune correspondance n'est trouv�e alors renvoie une chaine de caract�res vide.
	 * 
	 * @param login le login renseign� par l'utilisateur sur la vue EcranConnexion et transmis par le contr�leur 
	 * @param password le mot de passe saisie par l'utilisateur sur la vue EcranConnexion et transmis par le contr�leur
	 * @return le type de poste occup� par l'utilisateur. Le contr�leur s'en servira pour choisir quelle vue affich�e
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
