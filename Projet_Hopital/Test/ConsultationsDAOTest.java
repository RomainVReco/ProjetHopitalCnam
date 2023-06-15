package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import DAO.AdressesDAO;
import DAO.ConsultationsDAO;
import DAO.MedecinsDAO;
import Exceptions.ErreurInterrogationBDD;
import Exceptions.FormatIncorrectException;
import Hopital.Adresses;
import Hopital.Consultations;
import Hopital.MaterielMedical;
import Hopital.Medecins;
import Hopital.Patients;
import connexion.SingleConnection;

/**
 * Classe de test du DAO pour l'objet Consultations
 * 
 * Pour le moment non fonctionnel mais non utilisé dans la version actuelle du logiciel
 * @author Romain
 *
 */
class ConsultationsDAOTest {
	
	public static Connection connection = SingleConnection.getInstance();
	
	// Vérifie la création d'une nouvelle consultation en base de données
	@Test
	void testCreate() {
		ConsultationsDAO consultationDAO = new ConsultationsDAO(connection);
		String detailClinique = "Détail clinique de la consultation numero TEST";
		String listePrescription = "Jour de repos TEST";
		AdressesDAO adresseDAO = new AdressesDAO(connection);
		Adresses nouvelleAdresse = adresseDAO.findById(12).get();
		Patients nouveauPatient = new Patients(7,"STRUEUSE","Simone", nouvelleAdresse,"2020-03-17 10:15:00.601","185");
		MedecinsDAO medecinDAO = new MedecinsDAO(connection);
		Medecins nouveauMedecin = medecinDAO.findById(4).get();
		MaterielMedical materiel = new MaterielMedical(9999);
		LocalDateTime ldtTest = LocalDateTime.now();
		Set<String> listePathologie = new HashSet<>();
		listePathologie.add("a");listePathologie.add("b");listePathologie.add("c");listePathologie.add("d");
		Consultations consultationNouvelle = new Consultations(9999, ldtTest, detailClinique, listePrescription, 
				nouveauPatient, nouveauMedecin, listePathologie, materiel);
		boolean insertStatus = false;
		insertStatus = consultationDAO.create(consultationNouvelle);
		
		assertTrue(insertStatus);
		assertEquals(9999, consultationDAO.findById(9999).get().getIdConsultation());
		
		try {
			consultationDAO.delete(consultationNouvelle);
		} catch (ErreurInterrogationBDD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Vérifie l'échec de création d'une consultaiton en double (même identifiant) et l'absence d'erreur
	 */
	@Test
	void testCreateFail() {
		ConsultationsDAO consultationDAO = new ConsultationsDAO(connection);
		Consultations consultationNouvelle = consultationDAO.findById(6).get();
		
		boolean insertStatus = false;
		insertStatus = consultationDAO.create(consultationNouvelle);
		assertFalse(insertStatus);
	}

	/**
	 * Vérifie la bonne mise à jour de l'heure de fin de consultation en base de données
	 */
	@Test
	void testUpdateHorodatageFin() {
		
		ConsultationsDAO consultationDAO = new ConsultationsDAO(connection);
		Consultations consultationTest = consultationDAO.findById(999).get();
		
		LocalDateTime ldtDebut = consultationTest.getHorodatageDebut();
		LocalDateTime ldtFin = ldtDebut.plusMonths(1);

		boolean updateHorodatageFin = false;
		
		updateHorodatageFin = consultationDAO.updateHorodatageFin(ldtFin, 999);
		consultationTest = consultationDAO.findById(999).get();
		
		assertTrue(updateHorodatageFin);
		assertNotNull(consultationTest.getHorodatageDebut());
		assertNotNull(consultationTest.getHorodatageFin());
		assertEquals(ldtFin,consultationTest.getHorodatageFin());

	}
	
	/**
	 * Vérifie la possibilité d'insérer une heure de fin de consultation null
	 */
	@Test
	void testUpdateHorodatageFinNull () {
		LocalDateTime ldtFin = null ;
		ConsultationsDAO consultationDAO = new ConsultationsDAO(connection);
		boolean updateHorodatageFin = false;
		
		updateHorodatageFin = consultationDAO.updateHorodatageFin(ldtFin, 999);
	
		Consultations consultationTest = consultationDAO.findById(999).get();
		
		assertTrue(updateHorodatageFin);
		assertEquals(null,consultationTest.getHorodatageFin());
	}
	
	/**
	 * Vérifier que l'horodatage de fin de consultation ne soit pas inférieur à la date de création
	 */
	@Test
	void testUpdateHorodatageFinIsBefore () {
		ConsultationsDAO consultationDAO = new ConsultationsDAO(connection);
		Consultations consultationTest = consultationDAO.findById(999).get();
		LocalDateTime ldtDebut = consultationTest.getHorodatageDebut();
		LocalDateTime ldtFin = ldtDebut.minusMonths(1);
		
		boolean updateHorodatageFin = false;
		
		updateHorodatageFin = consultationDAO.updateHorodatageFin(ldtFin, 999);
		consultationTest = consultationDAO.findById(999).get();
		
		assertFalse(updateHorodatageFin);
		assertNotEquals(ldtFin,consultationTest.getHorodatageFin());
		assertEquals(null,consultationTest.getHorodatageFin());
	}

	// Vérifie la bonne mise à jour des informations de consultation
	@Test
	void testUpdate() {
		ConsultationsDAO consultationDAO = new ConsultationsDAO(connection);
		Consultations consultationTest = consultationDAO.findById(999).get();
		
		try {
			consultationTest.setDetailClinique("Jour de repos Test & Update xoxoxoxoxo");
		} catch (FormatIncorrectException e) {
			e.printStackTrace();
		}
		consultationTest.setPrescription("Update prescription");
		LocalDateTime ldtDebut = consultationTest.getHorodatageDebut();
		LocalDateTime ldtDebutUpdate = ldtDebut.minusMonths(1);
		consultationTest.setHorodatageDebut(ldtDebutUpdate);
		consultationTest.addPathologie("e");
		MaterielMedical materiel = new MaterielMedical(1);
		consultationTest.setMateriel(materiel);
		
		boolean updateStatus = false;
		updateStatus = consultationDAO.update(consultationTest);
		consultationTest = consultationDAO.findById(999).get();
		System.out.println(consultationTest.getPathologie().toString());
		System.out.println(consultationTest.getPathologie().contains("e"));
		assertTrue(updateStatus);
		assertEquals("Jour de repos Test & Update xoxoxoxoxo", consultationTest.getDetailClinique());
		assertEquals(ldtDebut, consultationTest.getHorodatageDebut());
		assertTrue(consultationTest.getPathologie().contains("e"));		
	}
	
	/**
	 * Vérifie l'échec de la mise à jour et l'absence d'erreur si la consultatio n'existe pas en base 
	 */
	@Test
	void testUpdateFail() {
		ConsultationsDAO consultationDAO = new ConsultationsDAO(connection);
		Consultations consultationTest = new Consultations(888);
		boolean updateStatus = false;
		updateStatus = consultationDAO.update(consultationTest);
		assertFalse(updateStatus);		
	}

	// Vérifie le que la méthode finById(int i) récupère bien un objet Consultations
	@Test
	void testFindById() {
		ConsultationsDAO consultationDAO = new ConsultationsDAO(connection);
		Consultations consultationTest = consultationDAO.findById(1).get();
		assertNotNull(consultationTest);
		assertEquals(1,consultationTest.getPatient().getIdPatient());
	}

	// Vérifie le que la méthode finByPatient(String str) récupère bien une liste d'objet Consultations
	@Test
	void testFindByPatient() {
		ConsultationsDAO consultationDAO = new ConsultationsDAO(connection);
		List<Consultations> listeConsultationsByName = consultationDAO.findByPatientName("Strueuse").orElseThrow();
		
		assertNotNull(listeConsultationsByName);
		assertEquals(2, listeConsultationsByName.size());
		assertEquals(7, listeConsultationsByName.get(0).getPatient().getIdPatient());
		assertEquals(6, listeConsultationsByName.get(1).getPatient().getIdPatient());
	}
	
	// Vérifie le bon fonctionnement de la wildcard sur la méthode finByPatient(String str)
	@Test 
	void testFindByPatientLike() {
		ConsultationsDAO consultationDAO = new ConsultationsDAO(connection);
		List<Consultations> listeConsultationsByName = consultationDAO.findByPatientName("St%").orElseThrow();
		
		assertNotNull(listeConsultationsByName);
		assertEquals(2, listeConsultationsByName.size());
		assertEquals(7, listeConsultationsByName.get(0).getPatient().getIdPatient());
		assertEquals(6, listeConsultationsByName.get(1).getPatient().getIdPatient());
	}
	
	// Vérifie l'absence d'erreur si aucun patient n'est trouvé avec la méthode findByPatient(String str)
	@Test 
	void testFindByPatientEmpty() {
		ConsultationsDAO consultationDAO = new ConsultationsDAO(connection);
		Optional<List<Consultations>> listeConsultationsByName = consultationDAO.findByPatientName("Romain");
		
		assertTrue(listeConsultationsByName.isEmpty());
	}

	// Vérifie le bon fonctionnement de la méthode findByPathologie(String str)
	@Test
	void testFindByPathologie() {
		ConsultationsDAO consultationDAO = new ConsultationsDAO(connection);
		List<Consultations> listeConsultationsByPathologie = consultationDAO.findByPathologie("%dy%").get();
		Consultations consultationDysphorie = consultationDAO.findById(7).get();
		
		assertEquals(2, listeConsultationsByPathologie.size());
		assertTrue(listeConsultationsByPathologie.contains(consultationDysphorie));
	}

	// Vérifie la bonne suppression d'une consultation existante
	@Test
	void testDelete() {
		ConsultationsDAO consultationDAO = new ConsultationsDAO(connection);
		String detailClinique = "Détail clinique de la consultation numero TEST";
		String listePrescription = "Jour de repos TEST";
		AdressesDAO adresseDAO = new AdressesDAO(connection);
		Adresses nouvelleAdresse = adresseDAO.findById(12).get();
		Patients nouveauPatient = new Patients(7,"STRUEUSE","Simone", nouvelleAdresse,"2020-03-17 10:15:00.601","185");
		MedecinsDAO medecinDAO = new MedecinsDAO(connection);
		Medecins nouveauMedecin = medecinDAO.findById(4).get();
		MaterielMedical materiel = new MaterielMedical(888);
		LocalDateTime ldtTest = LocalDateTime.now();
		Set<String> listePathologie = new HashSet<>();
		listePathologie.add("a");listePathologie.add("b");listePathologie.add("c");listePathologie.add("d");
		Consultations consultationNouvelle = new Consultations(888, ldtTest, detailClinique, listePrescription, 
				nouveauPatient, nouveauMedecin, listePathologie, materiel);
		boolean insertStatus = false;
		insertStatus = consultationDAO.create(consultationNouvelle);
		
		assertTrue(insertStatus);
		
		boolean deleteStatus =false;
		try {
			deleteStatus = consultationDAO.delete(consultationNouvelle);
		} catch (ErreurInterrogationBDD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(deleteStatus);
		
	}

	// Vérifie la bonne suppression d'une consultation existante avec l'identifiant de consultation
	@Test
	void testDeleteById() {
		ConsultationsDAO consultationDAO = new ConsultationsDAO(connection);
		String detailClinique = "Détail clinique de la consultation numero TEST";
		String listePrescription = "Jour de repos TEST";
		AdressesDAO adresseDAO = new AdressesDAO(connection);
		Adresses nouvelleAdresse = adresseDAO.findById(12).get();
		Patients nouveauPatient = new Patients(7,"STRUEUSE","Simone", nouvelleAdresse,"2020-03-17 10:15:00.601","185");
		MedecinsDAO medecinDAO = new MedecinsDAO(connection);
		Medecins nouveauMedecin = medecinDAO.findById(4).get();
		MaterielMedical materiel = new MaterielMedical(888);
		LocalDateTime ldtTest = LocalDateTime.now();
		Set<String> listePathologie = new HashSet<>();
		listePathologie.add("a");listePathologie.add("b");listePathologie.add("c");listePathologie.add("d");
		Consultations consultationNouvelle = new Consultations(888, ldtTest, detailClinique, listePrescription, 
				nouveauPatient, nouveauMedecin, listePathologie, materiel);
		boolean insertStatus = false;
		insertStatus = consultationDAO.create(consultationNouvelle);
		
		assertTrue(insertStatus);
		
		boolean deleteStatus =false;
		deleteStatus = consultationDAO.deleteById(888);
		
		assertTrue(deleteStatus);
	}
	
	// Vérifie l'échec de suppression d'une consultation et d'absence d'erreur si elle n'existe pas
	@Test
	void testDeleteFail() {
		ConsultationsDAO consultationDAO = new ConsultationsDAO(connection);
		Consultations consultationNouvelle2 = new Consultations(8888);
		boolean deleteStatus =false;
		try {
			deleteStatus = consultationDAO.delete(consultationNouvelle2);
		} catch (ErreurInterrogationBDD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		assertFalse(deleteStatus);
	}
	
	// Vérifie l'échec de suppression d'une consultation à partir de son identifiant et d'absence d'erreur si elle n'existe pas
	@Test
	void testDeleteByIdFail() {
		ConsultationsDAO consultationDAO = new ConsultationsDAO(connection);
		boolean deleteStatus =false;
		deleteStatus = consultationDAO.deleteById(8888);	
		assertFalse(deleteStatus);
		
	}

}
