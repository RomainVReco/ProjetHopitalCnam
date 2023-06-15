package idGenerator;

import java.util.Random;

/**
 * Génère un identifiant aléatoire compris entre 7 et 2507.
 * Utilisé dans la classe Médecins
 * 
 * @author Romain
 *
 */
public class IdGenMedecin {
	
	public static int getIdGenMedecin() {
		return (new Random().nextInt(2500))+7;
	}

}
