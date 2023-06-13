package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import DAO.ServicesDAO;
import Exceptions.ErreurInterrogationBDD;
import Hopital.Services;
import connexion.SingleConnection;

class ServicesDAOTest {

	public static Connection connection = SingleConnection.getInstance();
	
	@Test
	void testCreate() {
		ServicesDAO serviceDAO = new ServicesDAO(connection);
		int randomServiceId = new Random().nextInt(9999);
		Services serviceTest = new Services (randomServiceId, "ServiceTest", 999.0f,99.0f,99);
		boolean createStatus=false;
		
		try {
			createStatus = serviceDAO.create(serviceTest);
		} catch (ErreurInterrogationBDD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(createStatus);
		
		try {
			serviceDAO.delete(serviceTest);
		} catch (ErreurInterrogationBDD e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testCreateFail() {
		ServicesDAO serviceDAO = new ServicesDAO(connection);
		Services serviceTest = new Services (1, "ServiceTest", 999.0f,99.0f,99);
		boolean createStatus=false;
		try {
			createStatus = serviceDAO.create(serviceTest);
		} catch (ErreurInterrogationBDD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(createStatus);
	}
	
	@Test 
	void delete() {
		ServicesDAO serviceDAO = new ServicesDAO(connection);
		Services serviceTest = new Services (999, "ServiceTest", 999.0f,99.0f,99);
		boolean deleteStatus=false;
		
		try {
			serviceDAO.create(serviceTest);
		} catch (ErreurInterrogationBDD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			deleteStatus = serviceDAO.delete(serviceTest);
		} catch (ErreurInterrogationBDD e) {
			e.printStackTrace();
		}
		
		assertTrue(deleteStatus);
	}
	
	@Test
	void deleteFail() {
		ServicesDAO serviceDAO = new ServicesDAO(connection);
		Services serviceTest = new Services (888, "ServiceTest", 999.0f,99.0f,99);
		boolean deleteStatus=false;
		try {

			deleteStatus = serviceDAO.delete(serviceTest);
		} catch (ErreurInterrogationBDD e) {
			e.printStackTrace();
		}
		
		assertFalse(deleteStatus);
	}
	
	@Test
	void testFindById() {
		ServicesDAO serviceDAO = new ServicesDAO(connection);
		Services serviceTest = new Services (999, "ServiceTest", 999.0f,99.0f,99);
		try {
			serviceDAO.create(serviceTest);
		} catch (ErreurInterrogationBDD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(serviceDAO.findById(999).isPresent());
		assertEquals(serviceTest,serviceDAO.findById(999).get());
	}
	
	@Test
	void testFindByNom() {
		ServicesDAO serviceDAO = new ServicesDAO(connection);
		List<Services> listeServices = serviceDAO.findByNom("Pe%").get();
		assertNotNull(listeServices);
		assertEquals(2, listeServices.size());
		assertEquals("Pédiatrie", listeServices.get(0).getNomService());
		assertEquals("Pédopsychiatrie", listeServices.get(1).getNomService());
	}
	
	@Test
	void testUpdate() {
		ServicesDAO serviceDAO = new ServicesDAO(connection);
		Services serviceTest = serviceDAO.findById(4).get();
		int nouveauNbrPersonnel = serviceTest.getNombrePersonnel();
		serviceTest.setNombrePersonnel(nouveauNbrPersonnel+10);
		float nouveauBudgetConsomme = serviceTest.getBudgetConsomme();
		serviceTest.setBudgetConsomme(nouveauBudgetConsomme+15.0f);
		boolean statusUpdate = false;
		
		statusUpdate = serviceDAO.update(serviceTest);
		Services serviceTestV2 = serviceDAO.findById(4).get();
		
		assertTrue(statusUpdate);
		assertEquals(nouveauNbrPersonnel+10, serviceTestV2.getNombrePersonnel());
		assertEquals(nouveauBudgetConsomme+15.0f, serviceTestV2.getBudgetConsomme());
	}
	
	@Test
	void testUpdateFail() {
		ServicesDAO serviceDAO = new ServicesDAO(connection);
		Services serviceTest = new Services(888);
		boolean statusUpdate = false;
		
		statusUpdate = serviceDAO.update(serviceTest);
		
		assertFalse(statusUpdate);
	}
}
