package Exceptions;

/**
 * Exception cr��e pour g�rer les erreurs fonctionnelles d'interrogation de base de donn�es
 * Lors d'un travail de refactoring, cette exception sera � supprimer
 * @author Romain
 *
 */
@SuppressWarnings("serial")
public class ErreurInterrogationBDD extends Exception {
	
	public ErreurInterrogationBDD (String message) {
		super(message);
	}

}
