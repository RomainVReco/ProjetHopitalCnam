package Exceptions;

/**
 * Exception cr��e pour g�rer les d�tails cliniques trop court
 * Lors d'un refactoring, il serait utile de g�rer le probl�me autrement et supprimer cette exception
 * @author Romain
 *
 */
@SuppressWarnings("serial")
public class FormatIncorrectException extends Exception {
	
	public FormatIncorrectException (String message) {
		super(message);
	}
}
