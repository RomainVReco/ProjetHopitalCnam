package hopital_modeles;

import DAO.LoginsDAO;
import connexion.SingleConnection;

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
