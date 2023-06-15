package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import DAO.AdressesDAO;
import DAO.PatientsDAO;
import Exceptions.ErreurInterrogationBDD;
import Hopital.Adresses;
import Hopital.Patients;
import connexion.SingleConnection;

/**
 * Classe de test du DAO pour l'objet Patients
 * @author Romain
 *
 */
class PatientsDAOTest {

	public static Connection connection = SingleConnection.getInstance();
	
	@AfterAll
	static void deleteNewPatient() {
		PatientsDAO patientDAO = new PatientsDAO(connection);
		patientDAO.deleteByName("CREATION");
	}
	
	@Test
	void testCreate() {
		PatientsDAO patientDAO = new PatientsDAO(connection);
		Adresses nouvelleAdresse = new Adresses(10);
		Patients nouveauPatient = new Patients("CREATION", "Patient",nouvelleAdresse);
		boolean createStatus = false;
		try {
			createStatus = patientDAO.create(nouveauPatient);
		} catch (ErreurInterrogationBDD e) {
			e.printStackTrace();
		}
		assertTrue(createStatus);
	}
	
	@Test
	void testExistingPatient () {
		PatientsDAO patientDAO = new PatientsDAO(connection);
		Adresses nouvelleAdresse = new Adresses(11);
		Patients nouveauPatient = new Patients(1,"CREATION", "Patient",nouvelleAdresse);
		boolean createStatus = false;
		try {
			createStatus = patientDAO.create(nouveauPatient);
		} catch (ErreurInterrogationBDD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(createStatus);
	}
	
	@Test
	void testUpdate() {
		PatientsDAO patientDAO = new PatientsDAO (connection);
		Patients patientTest = patientDAO.findById(5).get();
		patientTest.setNumeroSS("9999");
		String dateCreationOriginale = patientTest.getDateCreation();
		String nomOriginal = patientTest.getNom();
		String prenomOriginal = patientTest.getPrenom();
		
		boolean updateStatus = false;
		updateStatus = patientDAO.update(patientTest);
		
		assertTrue(updateStatus);
		
		Patients patientTestV2 = patientDAO.findById(5).get();
		assertEquals(dateCreationOriginale, patientTestV2.getDateCreation());
		assertEquals(nomOriginal, patientTestV2.getNom());
		assertEquals(prenomOriginal, patientTestV2.getPrenom());
		assertEquals(patientTest, patientTestV2);
	}

	@Test
	void testFindById() {
		PatientsDAO patientDAO = new PatientsDAO(connection);
		boolean patientEstPresent = patientDAO.findById(1).isPresent();
		assertTrue(patientEstPresent);
	}
	
	@Test
	void testFailFindById () {
	PatientsDAO patientDAO = new PatientsDAO(connection);
	boolean patientEstPresent = patientDAO.findById(0).isPresent();
	assertFalse(patientEstPresent);
	}
	
	@Test
	void testDelete() {
		PatientsDAO patientDAO = new PatientsDAO(connection);
		Adresses nouvelleAdresse = new Adresses(11);
		Patients nouveauPatient = new Patients("CREATION", "Patient",nouvelleAdresse);
		boolean createStatus = false;
		try {
			createStatus = patientDAO.create(nouveauPatient);
		} catch (ErreurInterrogationBDD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean deleteStatus = false;
		try {
			deleteStatus = patientDAO.delete(nouveauPatient);
		} catch (ErreurInterrogationBDD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(createStatus);
		assertTrue(deleteStatus);	
		}

	@Test
	void testDeleteNull() {
		PatientsDAO patientDAO = new PatientsDAO(connection);
		Patients nouveauPatient = patientDAO.findById(9999).orElse(new Patients());		
		
		boolean deleteStatus = false;
		try {
			deleteStatus = patientDAO.delete(nouveauPatient);
		} catch (ErreurInterrogationBDD e) {
			e.printStackTrace();
		}
		assertFalse(deleteStatus);	
		}
	
	@Test
	void testFindByNameExact() {
		PatientsDAO patientDAO = new PatientsDAO(connection);
		AdressesDAO adresseDAO = new AdressesDAO(connection);
		Adresses nouvelleAdresse = adresseDAO.findById(12).get();
		Patients nouveauPatient = new Patients(7,"STRUEUSE","Simone", nouvelleAdresse,"2020-03-17 10:15:00.601","185");
		try {
			patientDAO.create(nouveauPatient);
		} catch (ErreurInterrogationBDD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Patients> listePatients = new ArrayList<Patients>();
		listePatients = patientDAO.findByName("STRUEUSE").get();
		
		assertEquals(2, listePatients.size());
		assertEquals(6, listePatients.get(0).getIdPatient());
		assertEquals(7, listePatients.get(1).getIdPatient());
		assertEquals("12345678901", listePatients.get(0).getNumeroSS());
		assertEquals("185", listePatients.get(1).getNumeroSS());
	
	}

	@Test
	void testFindByNumSSExact() {
		PatientsDAO patientDAO = new PatientsDAO(connection);
		List<Patients> listePatients = new ArrayList<Patients>();
		listePatients = patientDAO.findByNumSS("185").get();
		assertEquals(1, listePatients.size());
		assertEquals("185", listePatients.get(0).getNumeroSS());
	}
	
	@Test
	void testFindByNumSSWildcard() {
		PatientsDAO patientDAO = new PatientsDAO(connection);
		List<Patients> listePatients = new ArrayList<Patients>();
		listePatients = patientDAO.findByNumSS("185%").get();
		assertEquals(2, listePatients.size());
		assertEquals("18566", listePatients.get(0).getNumeroSS());
		assertEquals("185", listePatients.get(1).getNumeroSS());
	}

	@Test
	void testFindByCityExact() {
		PatientsDAO patientDAO = new PatientsDAO(connection);
		List<Patients> listePatients = new ArrayList<Patients>();
		listePatients = patientDAO.findByCity("Lannion").get();
		
		assertEquals(1, listePatients.size());
		assertEquals("STRUEUSE", listePatients.get(0).getNom());
	}
	
	@Test
	void testFindByCityWildcard() {
		PatientsDAO patientDAO = new PatientsDAO(connection);
		List<Patients> listePatients = new ArrayList<Patients>();
		listePatients = patientDAO.findByCity("bo%").get();
		
		assertEquals(2, listePatients.size());
		assertEquals("REYSCOUSSE", listePatients.get(0).getNom());
		assertEquals("331001", listePatients.get(1).getAdressePatient().getCodePostal());
	}
	
	@Test
	void testFindByCityNone() {
		PatientsDAO patientDAO = new PatientsDAO(connection);
		List<Patients> listePatients = new ArrayList<Patients>();
		listePatients = patientDAO.findByCity("Jambon").orElse(null);
		assertNull(listePatients);
	}

}
