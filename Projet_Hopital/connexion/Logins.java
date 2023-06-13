package connexion;

import java.util.Objects;

public class Logins {
	
	int idLogin ;
	String login = "";
	String password = "";
	String typePoste = "" ;
	
	public Logins(int idLogin, String login, String password, String typePoste) {
		super();
		this.idLogin = idLogin;
		this.login = login;
		this.password = password;
		this.typePoste = typePoste;
	}

	public Logins(String login, String password, String typePoste) {
		super();
		this.login = login;
		this.password = password;
		this.typePoste = typePoste;
	}
	
	public Logins() {
		super();
	}

	public int getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(int idLogin) {
		this.idLogin = idLogin;
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

	public String getTypePoste() {
		return typePoste;
	}

	public void setTypePoste(String typePoste) {
		this.typePoste = typePoste;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idLogin, login, password, typePoste);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Logins other = (Logins) obj;
		return idLogin == other.idLogin && Objects.equals(login, other.login)
				&& Objects.equals(password, other.password) && Objects.equals(typePoste, other.typePoste);
	}

}
