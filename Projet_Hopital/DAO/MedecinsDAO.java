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
 * DAO g�rant les op�rations CRUD pour l'objet M�decins
 * 
 * En plus des op�rations CRUD habituelles, le DAO contient la m�thode :
 * - updateDateSortie (LocalDate dateSortie, int idMedecin) pour mettre � jour la date de sortie des effectifs d'un m�decin
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
	 * Met � jour la date de sortie des effectifs du m�decin d�sign� par son identifiant
	 * 
	 * @param dateSortie la date � enregistrer en BDD
	 * @param idMedecin l'identifiant du m�decin sur lequel la mise � jour s'applique
	 * @return {@code true} si la mise � jour est effective
	 * @throws ErreurInterrogationBDD
	 */
	public boolean updateDateSortie (LocalDate dateSortie, int idMedecin) throws ErreurInterrogationBDD {
		if (findById(idMedecin).isEmpty()) {
			throw new ErreurInterrogationBDD("Ce m�decin n'existe pas en base de donn�es");
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
			throw new ErreurInterrogationBDD("Ce m�decin n'existe pas en base de donn�es");
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
			throw new ErreurInterrogationBDD("Ce m�decin n'existe pas en base de donn�es");
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
	 * V�rifie l'existence du med�ceon sur lequel l'op�ration de type Retrieve, Update ou Delete doit �tre
	 * Peut �tre supprim� lors d'un refactoring au profit de findById(int i)
	 * 
	 * @param idMedecin l'identifiant du m�decin
	 * @return {@code true} si le m�decine existe
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
	 * Afin d'all�ger le code des m�thodes de type Retrieve du DAO, cette m�thode est utilis�e pour
	 * r�cup�rer les informations du m�decin du ResultSet 
	 * @param rs ResultSet fourni par la m�thode Retrieve
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
