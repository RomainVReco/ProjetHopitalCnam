package Exceptions;

/**
 * Exception créée pour gérer les erreurs fonctionnelles d'interrogation de base de données
 * Lors d'un travail de refactoring, cette exception sera à supprimer
 * @author Romain
 *
 */
@SuppressWarnings("serial")
public class ErreurInterrogationBDD extends Exception {
	
	public ErreurInterrogationBDD (String message) {
		super(message);
	}

}
