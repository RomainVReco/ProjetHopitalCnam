package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.Test;

import DAO.SpecialitesDAO;
import Hopital.Specialites;
import connexion.SingleConnection;

class SpecialitesDAOTest {
	
	public static Connection connection = SingleConnection.getInstance();

	@Test
	void testCreateSpecialites() {
		SpecialitesDAO specialiteDAO = new SpecialitesDAO(connection);
		Specialites specialiteTest = new Specialites(99, "SpecialiteTest99");
		
		boolean createStatus = false;
		createStatus = specialiteDAO.create(specialiteTest);
		specialiteTest = specialiteDAO.findById(99).get();
		
		assertTrue(createStatus);
		assertEquals("SpecialiteTest99", specialiteTest.getNomSpecialite());
		
		specialiteDAO.delete(specialiteTest);
	}
	
	@Test
	void testDeleteSpecialites() {
		SpecialitesDAO specialiteDAO = new SpecialitesDAO(connection);
		Specialites specialiteTest = new Specialites(98, "SpecialiteTest98");
		
		boolean createStatus = false;
		
		createStatus = specialiteDAO.create(specialiteTest);
		assertTrue(createStatus);
		
		boolean deleteStatus = false;
		deleteStatus = specialiteDAO.delete(specialiteTest);
		
		assertTrue(deleteStatus);
		assertTrue(specialiteDAO.findById(98).isEmpty());
	}
	
	@Test
	void testFindById() {
		SpecialitesDAO specialiteDAO = new SpecialitesDAO(connection);
		Specialites specialiteTest = specialiteDAO.findById(6).get();

		assertEquals("Chirurgie Oculaire", specialiteTest.getNomSpecialite());
		assertEquals(6, specialiteTest.getIdSpecialite());		
	}
	
	@Test
	void testFindByNameWildcard() {
		SpecialitesDAO specialiteDAO = new SpecialitesDAO(connection);
		List<Specialites> specialiteTest = specialiteDAO.findByName("Chirurgie%").get();
		
		assertFalse(specialiteTest.isEmpty());
		assertEquals(13, specialiteTest.size());
		}
	
	@Test 
	void testFindByNameExact() {
		SpecialitesDAO specialiteDAO = new SpecialitesDAO(connection);
		List<Specialites> specialiteTest = specialiteDAO.findByName("Chirurgie").get();
		
		assertFalse(specialiteTest.isEmpty());
		assertEquals(1, specialiteTest.size());
		assertEquals(14, specialiteTest.get(0).getIdSpecialite());
	}
			
	@Test
	void testUpdateSpecialites() {
		SpecialitesDAO specialiteDAO = new SpecialitesDAO(connection);
		Specialites specialiteTest = new Specialites(154, "SpecialiteTest154");
		
		boolean createStatus = false;
		
		createStatus = specialiteDAO.create(specialiteTest);
		assertTrue(createStatus);
		
		boolean updateStatus = false;
		specialiteTest.setNomSpecialite("154SpecialiteTest");
		updateStatus = specialiteDAO.update(specialiteTest);
		assertTrue(updateStatus);
		
		Specialites specialiteControleTest = specialiteDAO.findById(154).get();
		assertEquals("154SpecialiteTest", specialiteControleTest.getNomSpecialite());
		
		specialiteDAO.delete(specialiteTest);
	}
	

}
