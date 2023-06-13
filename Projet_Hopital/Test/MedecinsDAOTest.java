package Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import DAO.MedecinsDAO;
import DAO.ServicesDAO;
import DAO.SpecialitesDAO;
import Hopital.Medecins;
import Hopital.Services;
import Hopital.Specialites;
import connexion.SingleConnection;

class MedecinsDAOTest {

	public static Connection connection = SingleConnection.getInstance();
	
	@Test
	void testCreate() {
		
		LocalDate ldtEntree = LocalDate.now();
		Date dateEntree = Date.valueOf(ldtEntree);
		
		LocalDate ldtSortie = LocalDate.of(1, 1, 1);
		Date dateSortie = Date.valueOf(ldtSortie);
		
		LocalDate ldtDoctorat = LocalDate.of(2000, 10, 01);
		Date dateDoctorat = Date.valueOf(ldtDoctorat);

		ServicesDAO serviceDAO = new ServicesDAO(connection);
		Services serviceTest = serviceDAO.findById(4).get();
		SpecialitesDAO specialiteDAO = new SpecialitesDAO(connection);
		Specialites specialiteTest = specialiteDAO.findById(16).get();
		
		Medecins medecinTest = new Medecins(999,"TEST", "Jean", dateEntree, dateSortie,0,2500.0f,200.2f,0,
				dateDoctorat,serviceTest,specialiteTest);
		
		boolean createStatus = false; 
		MedecinsDAO medecinDAO = new MedecinsDAO(connection);
		
		createStatus = medecinDAO.create(medecinTest);
		assertTrue(createStatus);
		
	}

}
