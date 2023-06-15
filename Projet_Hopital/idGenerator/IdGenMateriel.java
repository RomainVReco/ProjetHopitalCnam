package idGenerator;

import java.util.Random;

/**
 * Génère un identifiant aléatoire compris entre 7 et 2507.
 * Utilisé dans la classe MaterielMedical
 * 
 * @author Romain
 *
 */
public class IdGenMateriel {
	
	public static int getIdGenMateriel() {
		return (new Random().nextInt(2500))+7;
	}

}
