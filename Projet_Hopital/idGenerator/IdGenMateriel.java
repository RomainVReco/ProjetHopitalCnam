package idGenerator;

import java.util.Random;

/**
 * G�n�re un identifiant al�atoire compris entre 7 et 2507.
 * Utilis� dans la classe MaterielMedical
 * 
 * @author Romain
 *
 */
public class IdGenMateriel {
	
	public static int getIdGenMateriel() {
		return (new Random().nextInt(2500))+7;
	}

}
