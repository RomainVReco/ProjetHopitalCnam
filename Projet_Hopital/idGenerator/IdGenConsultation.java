package idGenerator;

import java.util.Random;

/**
 * G�n�re un identifiant al�ratoire sous forme d'entier, compris entre 7 et 2507.
 * Utilis� dans la classe Consultations 
 * 
 * @author Romain
 *
 */
public class IdGenConsultation {
	
	public static int getIdGenConsultation() {
		return (new Random().nextInt(2500))+7;
	}

}
