package idGenerator;

import java.util.Random;

/**
 * G�n�re un identifiant al�atoire compris entre 7 et 2507.
 * Utilis� dans la classe Patients
 * 
 * @author Romain
 *
 */
public class IdGenPatients {
	
	public static int genId() {
		return new Random().nextInt(2500)+7;
	}

}
