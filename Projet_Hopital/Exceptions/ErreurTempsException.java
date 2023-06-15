package Exceptions;

/**
 * Exception créée pour gérer les conflits d'horodatage entre la création et la mise à jour d'une information en BDD
 * A supprimer lors d'un refactoring pour gérer le problème autrement
 * 
 * @author Romain
 *
 */
@SuppressWarnings("serial")
public class ErreurTempsException extends Exception {

	public ErreurTempsException(String message) {
		super(message);
	}

}
