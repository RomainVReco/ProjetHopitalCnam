package idGenerator;

import java.util.Random;

/**
 * G�n�re un identifiant al�atoire compris entre 7 et 2507.
 * Utilis� dans la classe M�decins
 * 
 * @author Romain
 *
 */
public class IdGenMedecin {
	
	public static int getIdGenMedecin() {
		return (new Random().nextInt(2500))+7;
	}

}
