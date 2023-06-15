package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import Exceptions.ErreurInterrogationBDD;
import Exceptions.ErreurTempsException;
import Exceptions.FormatIncorrectException;
import Hopital.Consultations;
import Hopital.MaterielMedical;
import Hopital.Medecins;
import Hopital.Patients;


/**
 * DAO gérant les opérations CRUD pour l'objet Consultations
 * 
 * En plus des opérations CRUD de base, permet une recherche par
 * - Pathologie
 * - Nom de patient 
 * - Médecin
 * - identifiant de Patient
 * 
 * Une méthode dédiée à l'enregistrement d'un horodatage de fin de consultation est également présente
 * 
 * @author Romain
 *
 */
public class ConsultationsDAO extends AbstractDAO<Consultations> {

	public ConsultationsDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Consultations objet) {
		if (findById(objet.getIdConsultation()).isPresent()) return false;
		int createStatus = -1 ;
		String sql = "INSERT INTO consultations VALUES (?,?,?,?,?,?,?,?,?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			int i = 1;
			pstmt.setInt(i++, objet.getIdConsultation());
			pstmt.setTimestamp(i++, Timestamp.valueOf(objet.getHorodatageDebut()));
			pstmt.setTimestamp(i++,null);
			pstmt.setString(i++, objet.getDetailClinique());
			pstmt.setString(i++, objet.getPrescription());
			pstmt.setInt(i++, objet.getPatient().getIdPatient());
			pstmt.setInt(i++, objet.getMedecin().getIdSalarie());
			pstmt.setString(i++, objet.convertirSetPathologie());
			pstmt.setInt(i++, objet.getMateriel().getIdMateriel());
			createStatus = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (createStatus==1) return true;
		else return false;
	}
	
	/**
	 * Ajoute en base l'horodatage de fin de la consultation
	 * Teste dans un premier temps l'existence de la consultation à mettre à jour, puis si l'horodatage de fin renseignée est bien postérieure
	 * à l'horodatage de création de la consultation
	 * 
	 * @param horodagateFin l'horodatage de fin à mettre à jour en base
	 * @param idConsultation l'identifidant de la consultation concernée
	 * @return {@code true} si la mise à jour a bien été faite, false si une condition n'est pas vérifiée ou que la mise à jour ne s'est pas faite
	 */
	public boolean updateHorodatageFin(LocalDateTime horodagateFin, int idConsultation) {
		if (checkConsultationExiste(idConsultation)==false) return false;
		try {
			if (horodagateFin.isBefore(findById(idConsultation).get().getHorodatageDebut())) return false;
		} catch (NullPointerException e) {
			System.out.println("L'horodatage de fin passé en paramètre de la méthode "
					+ "updateHorodatageFin() est NULL");
		}
		
		int updateStatut = -1 ;
		String sql = "UPDATE consultations SET horodatageFin = ? WHERE idConsultation = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			if (horodagateFin!=null) {
				pstmt.setTimestamp(1, Timestamp.valueOf(horodagateFin));
			}
			else pstmt.setTimestamp(1,null);
			pstmt.setInt(2, idConsultation);
			updateStatut = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (updateStatut==1) return true;
		else return false;
	}

	@Override
	public boolean update(Consultations objet) {
		if (checkConsultationExiste(objet.getIdConsultation())==false) return false;
		
		int updateStatus = -1;
		 String sql = "UPDATE consultations SET detailClinique = ?, listePrescription = ?, "
		  + "idPatient = ?, idMedecin = ?, listePathologie = ?, idMateriel = ? "
		  + "WHERE idConsultation = ?";
		 	
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			int i = 1 ;
			pstmt.setString(i++, objet.getDetailClinique());
			pstmt.setString(i++, objet.getPrescription());
			pstmt.setInt(i++, objet.getPatient().getIdPatient());
			pstmt.setInt(i++, objet.getMedecin().getIdSalarie());
			pstmt.setString(i++, objet.convertirSetPathologie());
			pstmt.setInt(i++, objet.getMateriel().getIdMateriel());
			pstmt.setInt(i++, objet.getIdConsultation());
			updateStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (updateStatus==1) return true;
		else return false;
	}

	@Override
	public Optional<Consultations> findById(int idConsultation) {
		String sql = "SELECT * FROM consultations WHERE idConsultation = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idConsultation);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Consultations consultationRecherchee = new Consultations (idConsultation);
				consultationRecherchee.setHorodatageDebut(rs.getTimestamp("horodatageDebut").toLocalDateTime());
				
				if (rs.getTimestamp("horodatageFin")!=null) {
					consultationRecherchee.setHorodatageFin(rs.getTimestamp("horodatageFin").toLocalDateTime());
				} else consultationRecherchee.setHorodatageFin(null);
				
				consultationRecherchee.setDetailClinique(rs.getString("detailClinique"));
				consultationRecherchee.setPrescription(rs.getString("listePrescription"));
				int idPatient = rs.getInt("idPatient");
				PatientsDAO patientDAO = new PatientsDAO(conn);
				Optional<Patients> optPatient = patientDAO.findById(idPatient);
				consultationRecherchee.setPatient(optPatient.orElse(null));
				int idMedecin = rs.getInt("idMedecin");
				MedecinsDAO medecinDAO = new MedecinsDAO(conn);
				Optional<Medecins> optMedecin = medecinDAO.findById(idMedecin);
				consultationRecherchee.setMedecin(optMedecin.orElse(null));
				String listeBDDPathologie = rs.getString("listePathologie");
				consultationRecherchee.setPathologie(extraireListePathologie(listeBDDPathologie));
				Integer idMateriel = rs.getInt("idMateriel");
				if (idMateriel!=null) {
					MaterielMedicalDAO materielDAO = new MaterielMedicalDAO(conn);
					consultationRecherchee.setMateriel(materielDAO.findById(idMateriel.intValue()).orElse(null));
				}	
				return Optional.of(consultationRecherchee);
			}
			
		} catch (SQLException | ErreurTempsException | FormatIncorrectException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	public Optional<List<Consultations>> findByPatientId (int idPatient) {
		PatientsDAO patientDAO = new PatientsDAO(conn);
		if (patientDAO.findById(idPatient).isEmpty()) return Optional.empty();
		String sql = "SELECT * FROM consultations WHERE idPatient = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idPatient);
			ResultSet rs = pstmt.executeQuery();
			if (rs.isBeforeFirst())  {
				List<Consultations> listeConsultationsByPatient = new ArrayList<>();
				while (rs.next()) {
					listeConsultationsByPatient.add(extraireConsultation(rs));
				}
				return Optional.of(listeConsultationsByPatient);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	
	public Optional<List<Consultations>> findByPatientName (String nomPatient) {
		String sql = "";
		if (nomPatient.contains("%")) {
			sql = "SELECT * FROM consultations "
					+ "INNER JOIN patients ON consultations.idPatient = patients.idPatient "
					+ "WHERE nom LIKE ?;";	
		}
		else sql = "SELECT * FROM consultations "
				+ "INNER JOIN patients ON consultations.idPatient = patients.idPatient "
				+ "WHERE nom = ?;";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, nomPatient);
			ResultSet rs = pstmt.executeQuery();
			if (rs.isBeforeFirst())  {
				List<Consultations> listeConsultationsByPatient = new ArrayList<>();
				while (rs.next()) {
					listeConsultationsByPatient.add(extraireConsultation(rs));
				}
				return Optional.of(listeConsultationsByPatient);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	public Optional<List<Consultations>> findByMedecin (int idMedecin) {
		MedecinsDAO medecinDAO = new MedecinsDAO(conn);
		if (medecinDAO.findById(idMedecin).isEmpty()) return Optional.empty();
		String sql = "SELECT * FROM consultations WHERE idMedecin = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idMedecin);
			ResultSet rs = pstmt.executeQuery();
			if (rs.isBeforeFirst()) {
				List<Consultations> listeConsultationsByMedecin = new ArrayList<>();
				while (rs.next()) {
					listeConsultationsByMedecin.add(extraireConsultation(rs));
				}
				return Optional.of(listeConsultationsByMedecin);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	public Optional<List<Consultations>> findByPathologie(String nomMaladie) {
		String sql = "SELECT * FROM consultations WHERE listePathologie LIKE ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, nomMaladie);
			ResultSet rs = pstmt.executeQuery();
			if (rs.isBeforeFirst()) {
				List<Consultations> listeConsultationsByPathologie = new ArrayList<>();
				while (rs.next()) {
					listeConsultationsByPathologie.add(extraireConsultation(rs));
				}
				return Optional.of(listeConsultationsByPathologie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Optional.empty();
	}

	@Override
	public boolean delete(Consultations objet) throws ErreurInterrogationBDD {
		if (checkConsultationExiste(objet.getIdConsultation())==false) return false;

		int deleteStatus = -1;
		String sql = "DELETE FROM consultations WHERE idConsultation = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, objet.getIdConsultation());
			deleteStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (deleteStatus==1) return true ;
		else return false;
	}
	
	public boolean deleteById (int idConsultation) {
		if (checkConsultationExiste(idConsultation)==false) return false;
		int deleteStatus = -1;
		String sql = "DELETE FROM consultations WHERE idConsultation = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idConsultation);
			deleteStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (deleteStatus==1) return true ;
		else return false;
	}
	
	// Méthodes privées de traitement BDD, afin de simplifier le code des méthodes CRUD DAO
	
	/**
	 * Méthode pour transformer en Set, les pathologies récupérées sous forme d'une longue chaîne de caractère
	 * afin de les stocker dans un objet Consultations
	 * @param pathologies pathologies extraite de la base de données sous forme d'un String
	 * @return un Set de pathologies
	 */
	private Set<String> extraireListePathologie(String pathologies) {
		HashSet<String> listePathologie = new HashSet<>();
		if (pathologies==null) return listePathologie;
		String str = pathologies.trim();
		str = pathologies.replace(" ","");
		String [] tabStr = str.split(",");
		for (String s : tabStr) {
			listePathologie.add(s);
		}
		return listePathologie;
	}
	
	/**
	 * Afin d'alléger le code des méthodes de type Retrieve du DAO, cette méthode est utilisée pour
	 * récupérer les informations de consultations du ResultSet 
	 * @param rs ResultSet fourni par la méthode Retrieve
	 * @return un objet Consultations ou Null si une erreur survient
	 */
	private Consultations extraireConsultation (ResultSet rs) {
		try {
			int idConsultation = rs.getInt("idConsultation");
			LocalDateTime horodatageDebut = rs.getTimestamp("horodatageDebut").toLocalDateTime();
			LocalDateTime horodatageFin = null;
			Timestamp ts = rs.getTimestamp("horodatageFin");
			if (ts!=null) {
				horodatageFin = ts.toLocalDateTime();
			}
			String detailClinique = rs.getString("detailClinique");
			String listePrescription = rs.getString("listePrescription");
			int idPatient = rs.getInt("idPatient");
			PatientsDAO patientDAO = new PatientsDAO(conn);
			int idMedecin = rs.getInt("idMedecin");
			MedecinsDAO medecinDAO = new MedecinsDAO(conn);
			String stringPathologie = rs.getString("listePathologie");
			HashSet<String> listePathologie = new HashSet<>();
			listePathologie = (HashSet<String>) extraireListePathologie(stringPathologie); 
			Integer idMateriel = rs.getInt("idMateriel");
			Consultations ajoutConsultation = new Consultations(idConsultation, horodatageDebut, 
					horodatageFin, detailClinique, listePrescription, 
					patientDAO.findById(idPatient).orElse(null),
					medecinDAO.findById(idMedecin).orElse(null), 
					listePathologie);	
			if (idMateriel!=null) {
				MaterielMedicalDAO materielDAO = new MaterielMedicalDAO(conn);
				ajoutConsultation.setMateriel(materielDAO.findById(idMateriel.intValue()).orElse(null));
			}			
			return ajoutConsultation; 
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Vérifie si la consultation existe en BDD
	 * Pourrait être supprimer au profit de findById
	 * @param idConsultation l'identifiant de la consultaiton à rechercher 
	 * @return {@code true} si la consultation existe en BDD
	 */
	private boolean checkConsultationExiste(int idConsultation) {
		try {
			if (findById(idConsultation).isEmpty()) {
				throw new ErreurInterrogationBDD("Cette consultation n'existe pas en BDD");
			}
		} catch (ErreurInterrogationBDD e) {
			return false;
		}
		return true;
	}
	
}
