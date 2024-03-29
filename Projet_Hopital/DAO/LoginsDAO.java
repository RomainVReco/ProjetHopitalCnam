package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import Exceptions.ErreurInterrogationBDD;
import connexion.Logins;

/**
 * DAO g�rant les op�rations CRUD pour l'objet Logins
 * 
 * L'acc�s � cet objet et � son utilisation doivent �tre r�serv�s � l'administrateur
 * 
 * En plus des op�rations CRUD habituelles, la classe contient deux m�thodes Retrieve suppl�mentaire :
 * - findByLoginPassword(String login, String password)
 * - findByLogin(String login)
 * 
 * @author Romain
 *
 */
public class LoginsDAO extends AbstractDAO<Logins> {

	public LoginsDAO(Connection conn) {
		super(conn);
	}

	/**
	 * La m�thode de cr�ation des objets Logins en base g�re la contrainte de la BDD de n'avoir qu'un identifiant unique par tuple
	 */
	@Override
	public boolean create(Logins objet) throws ErreurInterrogationBDD {
		if (findById(objet.getIdLogin()).isPresent()) return false;
		String sql = "INSERT INTO logins VALUES (?,?,?,?);";
		int createStatus = -1;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			int i = 1;
			pstmt.setInt(i++, objet.getIdLogin());
			pstmt.setString(i++, objet.getLogin());
			pstmt.setString(i++, objet.getPassword());
			pstmt.setString(i++, objet.getTypePoste());
			createStatus = pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Ce login existe d�j�.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (createStatus==1) return true;
		else return false;
	}

	@Override
	public boolean update(Logins objet) {
		if (findById(objet.getIdLogin()).isEmpty()) return false;
		String sql = "UPDATE logins SET login = ?, password = ?, typePoste = ? WHERE idLogin = ?;";
		int updateStatus = -1;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			int i = 1;
			pstmt.setString(i++, objet.getLogin());
			pstmt.setString(i++, objet.getPassword());
			pstmt.setString(i++, objet.getTypePoste());
			pstmt.setInt(i++, objet.getIdLogin());
			updateStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (updateStatus==1) return true;
		else return false;
	}

	// Retourne un objet Optional contenant ou non un Logins par identifiant d'un Logins
	// M�thode en pratique inutilis�e
	@Override
	public Optional<Logins> findById(int objet) {
		String sql = "SELECT * FROM logins WHERE idLogin = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1,objet);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String loginConnexion = rs.getString("login");
				String passwordConnexion = rs.getString("password");
				String typePoste = rs.getString("typePoste");
				Logins loginRecherche = new Logins(objet, loginConnexion, passwordConnexion, typePoste);
				return Optional.of(loginRecherche);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	// Retourne un objet Optional contenant ou non un Logins, � partir d'une recherhce avec login ET du mot de passe
	public Optional<Logins> findByLoginPassword(String login, String password) {
		String sql = "SELECT idLogin, typePoste FROM logins WHERE login = ? AND password = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, login);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int idLogin = rs.getInt("idLogin");
				String typePoste = rs.getString("typePoste");
				Logins loginRecherche = new Logins(idLogin, login, password, typePoste);
				return Optional.of(loginRecherche);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();	
	}
	
	// Retourne un objet Optional contenant ou non un Logins apr�s recherche par login utilisateur 
	public Optional<Logins> findByLogin(String login) {
		String sql = "SELECT * FROM logins WHERE login = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1,login);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int idLogin = rs.getInt("login");
				String passwordConnexion = rs.getString("password");
				String typePoste = rs.getString("typePoste");
				Logins loginRecherche = new Logins(idLogin, login, passwordConnexion, typePoste);
				return Optional.of(loginRecherche);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public boolean delete(Logins objet) throws ErreurInterrogationBDD {
		if (findById(objet.getIdLogin()).isEmpty()) return false;
		String sql = "DELETE FROM logins WHERE idLogin = ?;";
		int deleteStatus = -1;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, objet.getIdLogin());
			deleteStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (deleteStatus==1) return true;
		else return false;
	}

}
