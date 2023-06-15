package Exceptions;

/**
 * Exception cr��e pour g�rer les conflits d'horodatage entre la cr�ation et la mise � jour d'une information en BDD
 * A supprimer lors d'un refactoring pour g�rer le probl�me autrement
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
