package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.junit.jupiter.api.Test;

import DAO.AdressesDAO;
import Exceptions.ErreurInterrogationBDD;
import Hopital.Adresses;
import connexion.SingleConnection;

class AdressesDAOTest {
	
	public static Connection connection = SingleConnection.getInstance();

	@Test
	void createAdresse() {
		AdressesDAO adresseDAO = new AdressesDAO(connection);
		DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateCreation = LocalDateTime.now() ;
		String date = dateCreation.format(CUSTOM_FORMATTER);
		int idAdresse = new Random().nextInt(2500)+7;
		Adresses adresseTest = new Adresses (idAdresse,"N°","rue","complément","99","Inconnu","Corée du Nord", date);
		boolean adresseCreationStatus = false;
		
		try {
			adresseCreationStatus = adresseDAO.create(adresseTest);
		} catch (ErreurInterrogationBDD e) {
			e.printStackTrace();
		}
		assertTrue(adresseCreationStatus);
		adresseDAO.delete(adresseTest);
	}
	
	@Test
	void createAdresseFail() {
		AdressesDAO adresseDAO = new AdressesDAO(connection);
		Adresses adresseTest = new Adresses (1);
		boolean adresseCreationStatus = false;
		
		try {
			adresseCreationStatus = adresseDAO.create(adresseTest);
		} catch (ErreurInterrogationBDD e) {
			e.printStackTrace();
		}
		assertFalse(adresseCreationStatus);
	}
	
	@Test
	void deleteAdresse() {
		AdressesDAO adresseDAO = new AdressesDAO(connection);
		DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateCreation = LocalDateTime.now() ;
		String date = dateCreation.format(CUSTOM_FORMATTER);
		int idAdresse = new Random().nextInt(2500)+7;
		System.out.println(idAdresse);
		Adresses adresseTest = new Adresses (idAdresse,"N°","rue","complément","99","Inconnu","Corée du Nord", date);
		boolean adresseDeleteStatus = false;
		
		try {
			adresseDAO.create(adresseTest);
		} catch (ErreurInterrogationBDD e) {
			e.printStackTrace();
		}
		
		adresseDeleteStatus = adresseDAO.delete(adresseTest);
		assertTrue(adresseDeleteStatus);
	}
	
	@Test
	void deleteAdresseFail() {
		AdressesDAO adresseDAO = new AdressesDAO(connection);
		Adresses adresseTest = new Adresses (9999);
		boolean adresseDeleteStatus = false;
		
		adresseDeleteStatus = adresseDAO.delete(adresseTest);

		assertFalse(adresseDeleteStatus);
	}
	
	@Test
	void updateAdresse() {
		AdressesDAO adresseDAO = new AdressesDAO(connection);
		DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateCreation = LocalDateTime.now() ;
		String date = dateCreation.format(CUSTOM_FORMATTER);
		int idAdresse = new Random().nextInt(2500)+7;
		Adresses adresseTest = new Adresses (idAdresse,"N°","rue","complément","99","Inconnu","Corée du Nord", date);
		boolean adresseUpdateStatus = false;
		
		try {
			adresseDAO.create(adresseTest);
		} catch (ErreurInterrogationBDD e) {
			e.printStackTrace();
		}
		String nouveauPays = "Arabie Saoudite";
		adresseTest.setPays(nouveauPays);
		adresseUpdateStatus = adresseDAO.update(adresseTest);
		Adresses adresseUpdate = adresseDAO.findById(idAdresse).get();
		String countryUpdated = adresseUpdate.getPays();
		
		assertTrue(adresseUpdateStatus);
		assertEquals(nouveauPays, countryUpdated);
		
		adresseDAO.delete(adresseUpdate);
	}

	@Test
	void updateAdresseFail() {
		AdressesDAO adresseDAO = new AdressesDAO(connection);
		Adresses adresseTest = new Adresses (9999);
		boolean adresseDeleteStatus = false;
		
		adresseDeleteStatus = adresseDAO.update(adresseTest);

		assertFalse(adresseDeleteStatus);
	}
}
