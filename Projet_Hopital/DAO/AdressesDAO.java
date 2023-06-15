package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Exceptions.ErreurInterrogationBDD;
import Hopital.Adresses;

/**
 * DAO gérant les opérations CRUD pour l'objet Adresses
 * 
 * En plus des opérations CRUD de base, permet une recherche d'adresse par ville
 * @author Romain
 *
 */
public class AdressesDAO extends AbstractDAO<Adresses> {

	public AdressesDAO(Connection conn) {
		super(conn);
	}
	/**
	 * Création d'adresse en base
	 */
	@Override
	public boolean create(Adresses objet) throws ErreurInterrogationBDD {
		if (findById(objet.getIdAdresse()).isPresent()) return false;
		int insertion = -1;
		String sql = "INSERT INTO adresses VALUES (?,?,?,?,?,?,?,?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, objet.getIdAdresse());
			pstmt.setString(2, objet.getNumero());
			pstmt.setString(3, objet.getAdresse1());
			pstmt.setString(4, objet.getAdresse2());
			pstmt.setString(5, objet.getCodePostal());
			pstmt.setString(6, objet.getVille());
			pstmt.setString(7, objet.getPays());
			pstmt.setString(8, objet.getDateCreationAdresse());
			insertion = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (insertion==1) return true;
		else return false;
	}

	/**
	 * Mise à jour d'adresse en base 
	 */
	@Override
	public boolean update(Adresses objet) {
		if (findById(objet.getIdAdresse()).isEmpty()) return false;
		int updateStatus = -1;
		String sql = "UPDATE adresses SET numero = ?, adresse1 = ?, adresse2 = ?,"
				+ "codePostal = ?, ville = ?, pays = ?, lastModified = ? WHERE idAdresse = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, objet.getNumero());
			pstmt.setString(2, objet.getAdresse1());
			pstmt.setString(3, objet.getAdresse2());
			pstmt.setString(4, objet.getCodePostal());
			pstmt.setString(5, objet.getVille());
			pstmt.setString(6, objet.getPays());
			pstmt.setString(7, objet.getDateCreationAdresse());
			pstmt.setInt(8, objet.getIdAdresse());
			updateStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (updateStatus==1) return true;
		else return false;
	}

	/**
	 * Recherche d'adresse en base par identifiant d'adresse
	 * 
	 * Retourne un objet Optional contenant une Adresse ou non
	 */
	@Override
	public Optional<Adresses> findById(int idAdresseRecherche) {
		String sql = "SELECT * FROM adresses WHERE idAdresse = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1,idAdresseRecherche);
			ResultSet rs = pstmt.executeQuery();
			if (rs.isBeforeFirst()) {
				Adresses adresseRecherche = new Adresses(idAdresseRecherche);
				while (rs.next()) {
					adresseRecherche.setNumero(rs.getString("numero"));
					adresseRecherche.setAdresse1(rs.getString("adresse1"));
					adresseRecherche.setAdresse2(rs.getString("adresse2"));
					adresseRecherche.setCodePostal(rs.getString("codePostal"));
					adresseRecherche.setVille(rs.getString("ville"));
					adresseRecherche.setPays(rs.getString("pays"));
					adresseRecherche.adressUpdateDate(rs.getString("lastModified"));
				}
				return Optional.of(adresseRecherche);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public boolean delete(Adresses objet) {
		if (findById(objet.getIdAdresse()).isEmpty()) return false;
		int deleteStatus = -1 ;
		String sql = "DELETE FROM adresses WHERE idAdresse = ?" ;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, objet.getIdAdresse());
			deleteStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (deleteStatus==1) return true;
		else return false;
	}
	
	public boolean deleteById (int idAdresse) {
		int deleteStatus = -1;
		String sql = "DELETE FROM adresses WHERE idAdresse = ?" ;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1,idAdresse);
			deleteStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (deleteStatus==1) return true;
		else return false;
	}
	
	/**
	 * Recherche des adresses par ville. La recherche doit être exacte.
	 * 
	 * @param city la ville à rechercher
	 * @return un objet Optional contenant ou non une liste d'adresses
	 */
	public Optional<List<Adresses>> findByCity(String city) {
		String sql = "SELECT * FROM adresses WHERE ville = ?" ;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1,city);
			ResultSet rs = pstmt.executeQuery();
			if (rs.isBeforeFirst()) {
				List<Adresses> villeRecherchee = new ArrayList<>();
				while (rs.next()) {
					villeRecherchee.add(extraireVille(rs));
				}
				return Optional.of(villeRecherchee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	/**
	 * Méthode pour récupérer l'adresse lors de l'exécution de la méthode findByCity(String str) 
	 * Est utilisée principalement afin de gagner en lisibilité dans la méthode findByCity(String str) 
	 * 
	 * @param rs ResultSet de la méthode findByCity(String str)
	 * @return un objet Adresses ou null si la récupération des informations échoue
	 */
	
	private Adresses extraireVille(ResultSet rs) {
		try {
			int id = rs.getInt("idAdresse");
			String num = rs.getString("numero");
			String Adresse1 = rs.getString("adresse1");
			String Adresse2 = rs.getString("adresse2");
			String codePostal = rs.getString("codePostal");
			String ville = rs.getString("ville");
			String pays = rs.getString("pays");
			String adressUpdateDate = rs.getString("lastModified");
			return new Adresses(id, num, Adresse1, Adresse2, codePostal, ville, pays, adressUpdateDate);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Le patient n'a pu être extrait de la BDD");
		}
		return null;
	}
}
