package idGenerator;

import java.util.Random;

/**
 * Génère un identifiant aléatoire compris entre 7 et 2507.
 * Utilisé dans la classe Patients
 * 
 * @author Romain
 *
 */
public class IdGenPatients {
	
	public static int genId() {
		return new Random().nextInt(2500)+7;
	}

}
