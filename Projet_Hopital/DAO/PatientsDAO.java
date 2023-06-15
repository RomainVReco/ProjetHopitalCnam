package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import Exceptions.ErreurInterrogationBDD;
import Hopital.Adresses;
import Hopital.Consultations;
import Hopital.Patients;

/**
 * DAO gérant les opérations CRUD pour l'objet Patients
 * 
 * En plus des opérations CRUD habituelles, le DAO contient les méthodes :
 * - findByName(String nom) 
 * - findByNumSS (String numSS)
 * - findByCity (String city)
 * 
 * Toutes peuvent être utilisées avec le wildcard '%'
 * 
 * @author Romain
 *
 */
public class PatientsDAO extends AbstractDAO<Patients> {

	public PatientsDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Patients objet) throws ErreurInterrogationBDD {
		if (findById(objet.getIdPatient()).isPresent()) return false;
		int insertion = -1;
		String sql = "INSERT INTO patients VALUES (?,?,?,?,?,?)";
		try (PreparedStatement pstmt=conn.prepareStatement(sql)) {
			pstmt.setInt(1, objet.getIdPatient());
			pstmt.setString(2,objet.getNom());
			pstmt.setString(3,objet.getPrenom());
			pstmt.setInt(4, objet.getAdressePatient().getIdAdresse());
			pstmt.setString(5, objet.getDateCreation());
			pstmt.setString(6, objet.getNumeroSS());
			insertion = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (insertion==1) return true;
		else return false;
	}
	
	@Override
	public boolean update(Patients objet) {
		if (findById(objet.getIdPatient()).isEmpty()==true) return false;
		int updateStatut = -1;
		String sql = "UPDATE patients SET nom = ?, prenom = ?, idAdresse = ?, "
				+ "dateCreation = ?, numeroSS = ? WHERE idPatient = ? ;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			int i = 1;
			pstmt.setString(i++, objet.getNom());
			pstmt.setString(i++, objet.getPrenom());
			pstmt.setInt(i++, objet.getAdressePatient().getIdAdresse());
			pstmt.setString(i++, objet.getDateCreation());
			pstmt.setString(i++, objet.getNumeroSS());
			pstmt.setInt(i++, objet.getIdPatient());
			updateStatut = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (updateStatut==1) return true;
		else return false;
	}

	@Override
	public Optional<Patients> findById(int idPatient) {
		String sql = "SELECT * FROM patients WHERE idPatient = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idPatient);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Patients patientRecherche = new Patients(idPatient);
				patientRecherche.setNom(rs.getString("nom"));
				patientRecherche.setPrenom(rs.getString("prenom"));
				patientRecherche.updateDateCreation(rs.getString("dateCreation"));
				patientRecherche.setNumeroSS(rs.getString("numeroSS"));
				AdressesDAO adrDAO = new AdressesDAO(conn);
				Optional<Adresses> adressePatient = adrDAO.findById(rs.getInt("idAdresse"));
				patientRecherche.setAdressePatient(adressePatient.orElse(new Adresses()));
				return Optional.of(patientRecherche);				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public boolean delete(Patients objet) throws ErreurInterrogationBDD {
		if (findById(objet.getIdPatient()).isEmpty()) return false; 
		int deleteStatus = -1 ;
		String sql = "DELETE FROM patients WHERE idPatient = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, objet.getIdPatient());
			deleteStatus = pstmt.executeUpdate();
			AdressesDAO adrDAO = new AdressesDAO(conn);
			adrDAO.deleteById(objet.getAdressePatient().getIdAdresse());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (deleteStatus==1) return true ;
		else return false;
	}
	
	public boolean deleteByName (String nom) {
		int deleteStatus = -1;
		String sql = "DELETE FROM patients WHERE nom = ?" ;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1,nom);
			deleteStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (deleteStatus==1) return true;
		else return false;
	}

	public Optional<List<Patients>> findByName(String nom) {
		String sql = "";
		if (nom.contains("%")) sql="SELECT idPatient, nom, prenom, numeroSS FROM patients WHERE nom LIKE ?";
		else sql = "SELECT idPatient, nom, prenom, numeroSS FROM patients WHERE nom = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, nom); 
			ResultSet rs = pstmt.executeQuery();
			if (rs.isBeforeFirst()) {
				List<Patients> listePatientsByName = new ArrayList<>();
				while (rs.next()) {
					listePatientsByName.add(extrairePatientSimple(rs));
				}
				return Optional.of(listePatientsByName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	// code à déplacer dans le DAO de l'objet Consultations
//	public boolean setAllConsultations (Patients patient) {
//		int id = patient.getIdPatient();
//		if (findById(id).isEmpty()) return false;
//		ConsultationsDAO consultationDAO = new ConsultationsDAO(conn);
//		Optional<List<Consultations>> optConsultationPatient = consultationDAO.findByPatientId(id);
//		try {
//			patient.setListeConsultations(optConsultationPatient.orElseThrow());
//		} catch (NoSuchElementException e) {
//			System.out.println("Pas de consulation pour ce patient");
//			return false;
//		}
//		return true;
//	}
	
	public Optional<List<Patients>> findByNumSS (String numSS) {
		String sql = "";
		if (numSS.contains("%")) sql="SELECT idPatient, nom, prenom, numeroSS FROM patients WHERE numeroSS LIKE ?";
		else sql = "SELECT idPatient, nom, prenom, numeroSS FROM patients WHERE numeroSS = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, numSS);
			ResultSet rs = pstmt.executeQuery();
			if (rs.isBeforeFirst()) {
				List<Patients> listePatientsByNumSS = new ArrayList<>();
				while (rs.next()) {
					listePatientsByNumSS.add(extrairePatientSimple(rs));
				}
				return Optional.of(listePatientsByNumSS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	public Optional<List<Patients>> findByCity (String city) {
		String sql = "";
		if (city.contains("%")) sql = "SELECT * FROM patients "
				+ "INNER JOIN adresses ON patients.idAdresse=adresses.idAdresse "
				+ "WHERE ville LIKE ?";
		
		else sql = "SELECT * FROM patients "
				+ "INNER JOIN adresses ON patients.idAdresse=adresses.idAdresse "
				+ "WHERE ville = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, city);
			ResultSet rs = pstmt.executeQuery();
			if (rs.isBeforeFirst()) {
				List<Patients> listByCities = new ArrayList<>();
				while (rs.next()) {
					int idPatient = rs.getInt("idPatient");
					String nomPatient = rs.getString("nom");
					String prenomPatient = rs.getString("prenom");
					String dateCreation = rs.getString("dateCreation");
					String numSS = rs.getString("numeroSS");
					AdressesDAO adrDAO = new AdressesDAO(conn);
					Adresses adr = adrDAO.findById(rs.getInt("idAdresse")).orElse(new Adresses());
					listByCities.add(new Patients(idPatient, nomPatient, prenomPatient, dateCreation, numSS, adr)); 
				}
				return Optional.of(listByCities);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	  /**
	 * Afin d'alléger le code des méthodes de type Retrieve du DAO, cette méthode est utilisée pour
	 * récupérer les informations simples du patient du ResultSet : identifiant, nom, prenom, numéro de SS. 
	 * @param rs ResultSet fourni par la méthode Retrieve
	 * @return un objet Patients ou Null si une erreur survient
	   */
	private Patients extrairePatientSimple (ResultSet rs) {
		try {
			int id = rs.getInt("idPatient");
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String numSS = rs.getString("numeroSS");
			return new Patients(id, nom, prenom, numSS);
		} catch (SQLException e) {
			new RuntimeException("Le patient n'a pu être extrait de la BDD");
		}
		return null;
	}
}
