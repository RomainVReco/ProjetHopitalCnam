package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import Exceptions.ErreurInterrogationBDD;
import Hopital.Medecins;
import Hopital.Services;
import Hopital.Specialites;

/**
 * DAO gérant les opérations CRUD pour l'objet Médecins
 * 
 * En plus des opérations CRUD habituelles, le DAO contient la méthode :
 * - updateDateSortie (LocalDate dateSortie, int idMedecin) pour mettre à jour la date de sortie des effectifs d'un médecin
 * 
 * @author Romain
 *
 */
public class MedecinsDAO extends AbstractDAO<Medecins> {

	public MedecinsDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Medecins objet) {
		if (findById(objet.getIdSalarie()).isPresent()) return false;
		int insertion = -1;
		String sql = "INSERT INTO medecins VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			int i = 1;
			pstmt.setInt(i++, objet.getIdSalarie());
			pstmt.setString(i++, objet.getNom());
			pstmt.setString(i++, objet.getPrenom());
			pstmt.setDate(i++, Date.valueOf(objet.getDateEntree()));
			pstmt.setDate(i++, null);
			pstmt.setInt(i++, objet.getAnciennete());
			pstmt.setBigDecimal(i++,  BigDecimal.valueOf(objet.getRemunerationFixe()));
			pstmt.setBigDecimal(i++, BigDecimal.valueOf(objet.getRemunerationVariable()));
			pstmt.setInt(i++, objet.getNbreActes());
			pstmt.setDate(i++, Date.valueOf(objet.getObtentionDoctorat()));
			pstmt.setInt(i++, objet.getServiceAffectation().getIdService());
			pstmt.setInt(i++, objet.getSpecialiteMedecin().getIdSpecialite());
			insertion = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (insertion==1) return true;		
		else return false;
	}

	@Override
	public boolean update(Medecins objet) {
		if (checkMedecinExiste(objet.getIdSalarie())==false) return false; 

		int updateStatus = -1;
		String sql = "UPDATE medecins SET nom = ? prenom = ? dateEntree = ? "
				+ "anciennete = ? remunerationFixe = ? "
				+ "remunerationVariable = ? nbreActes = ? obtentionDoctorat = ? "
				+ "idService = ? specialite = ?  WHERE idSalarie = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			int i = 1;
			pstmt.setString(i++, objet.getNom());
			pstmt.setString(i++, objet.getPrenom());
			pstmt.setDate(i++, Date.valueOf(objet.getDateEntree()));
			pstmt.setInt(i++, objet.getAnciennete());
			pstmt.setBigDecimal(i++,  BigDecimal.valueOf(objet.getRemunerationFixe()));
			pstmt.setBigDecimal(i++, BigDecimal.valueOf(objet.getRemunerationVariable()));
			pstmt.setInt(i++, objet.getNbreActes());
			pstmt.setDate(i++, Date.valueOf(objet.getObtentionDoctorat()));
			pstmt.setInt(i++, objet.getServiceAffectation().getIdService());
			pstmt.setInt(i++, objet.getSpecialiteMedecin().getIdSpecialite());	
			pstmt.setInt(i++, objet.getIdSalarie());
			updateStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (updateStatus==1) return true;
		else return false;
	}
	
	/**
	 * Met à jour la date de sortie des effectifs du médecin désigné par son identifiant
	 * 
	 * @param dateSortie la date à enregistrer en BDD
	 * @param idMedecin l'identifiant du médecin sur lequel la mise à jour s'applique
	 * @return {@code true} si la mise à jour est effective
	 * @throws ErreurInterrogationBDD
	 */
	public boolean updateDateSortie (LocalDate dateSortie, int idMedecin) throws ErreurInterrogationBDD {
		if (findById(idMedecin).isEmpty()) {
			throw new ErreurInterrogationBDD("Ce médecin n'existe pas en base de données");
		}
		int updateStatus = -1;
		String sql = "UPDATE medecins SET dateSortie = ? WHERE idSalarie = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDate(1, Date.valueOf(dateSortie));
			pstmt.setInt(2, idMedecin);
			updateStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (updateStatus==1) return true;
		else return false ;
	}

	// A reprendre avec la table Specialite, Service 
	@Override
	public Optional<Medecins> findById(int idSalarie) {
		String sql = "SELECT * FROM medecins WHERE idSalarie = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idSalarie);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Medecins medecinRecherche = new Medecins();
				medecinRecherche = extraireMedecin(rs);
				medecinRecherche.setIdSalarie(idSalarie);
				return Optional.of(medecinRecherche);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}	

	@Override
	public boolean delete(Medecins objet) throws ErreurInterrogationBDD {
		if (findById(objet.getIdSalarie()).isEmpty()) {
			throw new ErreurInterrogationBDD("Ce médecin n'existe pas en base de données");
		}
		int deleteStatus = -1;
		String sql = "DELETE FROM medecins WHERE idSalarie = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, objet.getIdSalarie());
			deleteStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (deleteStatus==1) return true;
		else return false;
	}
	
	public boolean deleteById (int idSalarie) throws ErreurInterrogationBDD {
		if (findById(idSalarie).isEmpty()) {
			throw new ErreurInterrogationBDD("Ce médecin n'existe pas en base de données");
		}
		int deleteStatus = -1;
		String sql = "DELETE FROM medecins WHERE idSalarie = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idSalarie);
			deleteStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (deleteStatus==1) return true;
		else return false;
	}
	
	/**
	 * Vérifie l'existence du medéceon sur lequel l'opération de type Retrieve, Update ou Delete doit être
	 * Peut être supprimé lors d'un refactoring au profit de findById(int i)
	 * 
	 * @param idMedecin l'identifiant du médecin
	 * @return {@code true} si le médecine existe
	 */
	  private boolean checkMedecinExiste(int idMedecin) {
	  try {
			if (findById(idMedecin).isEmpty()) {
				throw new ErreurInterrogationBDD("Cette consultation n'existe pas en BDD");
			}
		} catch (ErreurInterrogationBDD e) {
			return false;
		}
		return true;
	  }
	  
	  /**
	 * Afin d'alléger le code des méthodes de type Retrieve du DAO, cette méthode est utilisée pour
	 * récupérer les informations du médecin du ResultSet 
	 * @param rs ResultSet fourni par la méthode Retrieve
	 * @return un objet Medecins ou Null si une erreur survient
	   */
	  private Medecins extraireMedecin (ResultSet rs) {
		  try {
			String nom = rs.getString("nom");
			String prenom = rs.getString("Prenom");
			Date dateEntree = rs.getDate("dateEntree");
			Date dateSortie = rs.getDate("dateSortie");
			int anciennete = rs.getInt("anciennete");
			float remFixe = rs.getBigDecimal("remunerationFixe").floatValue();
			float remVar = rs.getBigDecimal("remunerationVariable").floatValue();
			int nbreActes = rs.getInt("nbreActes");
			Date dateDoctorat = rs.getDate("obtentionDoctorat");
			int idService = rs.getInt("idService");
			ServicesDAO serviceDAO = new ServicesDAO(conn);
			Services service = serviceDAO.findById(idService).orElseGet(null);
			int idSpecialite = rs.getInt("idSpecialite");
			SpecialitesDAO specialiteDAO = new SpecialitesDAO(conn);
			Specialites specialite = specialiteDAO.findById(idSpecialite).orElseGet(null);
			return new Medecins(nom, prenom, dateEntree, dateSortie, 
					anciennete, remFixe, remVar, nbreActes, dateDoctorat, service, specialite);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;  
	  }
}
