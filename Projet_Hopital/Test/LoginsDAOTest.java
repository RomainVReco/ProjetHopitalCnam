package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import DAO.LoginsDAO;
import Exceptions.ErreurInterrogationBDD;
import connexion.Logins;
import connexion.SingleConnection;

/**
 * Classe de test du DAO pour l'objet Logins
 * Test l'ensemble des opérations CRUD
 * 
 * @author Romain
 *
 */
class LoginsDAOTest {
	
	public static Connection connection = SingleConnection.getInstance();

	@Test
	void createTest() throws ErreurInterrogationBDD {
		Logins loginTest = new Logins (99, "jbonbeurre", "poney", "Médecin");
		LoginsDAO loginDAO = new LoginsDAO(connection);
		boolean createStatus = false;
		
		createStatus = loginDAO.create(loginTest);
		
		assertTrue(createStatus);
		loginDAO.delete(loginTest);
	}
	
	@Test
	void createLoginFail() throws ErreurInterrogationBDD, SQLException {
		Logins loginTest = new Logins (1, "rvorlet", "poney", "Médecin");
		LoginsDAO loginDAO = new LoginsDAO(connection);
		boolean createStatus = false;
		
		createStatus = loginDAO.create(loginTest);

		assertFalse(createStatus);
	}
	
	@Test
	void updateTest () throws ErreurInterrogationBDD {
		Logins loginTest = new Logins (999, "rvorlet", "poney", "Médecin");
		LoginsDAO loginDAO = new LoginsDAO(connection);
		boolean createStatus = false;
		
		createStatus = loginDAO.create(loginTest);
				
		loginTest.setLogin("skhatiri");
		loginTest.setTypePoste("Technicien");
		
		boolean updateStatus = false; 
		updateStatus = loginDAO.update(loginTest);
		assertTrue(updateStatus);
		
		Logins loginTestUpdate = loginDAO.findById(999).get();
		
		assertEquals(loginTest.getLogin(), loginTestUpdate.getLogin());
		assertEquals("Technicien", loginTestUpdate.getTypePoste());
		
		loginTest.setLogin("rvorlet");
		loginTest.setTypePoste("Médecin");
		loginDAO.update(loginTest);
	}
	
	@Test 
	void findByLoginPassword() {
		LoginsDAO loginDAO = new LoginsDAO(connection);
		Logins loginTest = loginDAO.findByLoginPassword("rvorlet", "poney").get();
		
		assertEquals(999, loginTest.getIdLogin());
		assertEquals("Médecin", loginTest.getTypePoste());
		
	}
	
}
