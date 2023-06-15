package Exceptions;

/**
 * Exception créée pour gérer les détails cliniques trop court
 * Lors d'un refactoring, il serait utile de gérer le problème autrement et supprimer cette exception
 * @author Romain
 *
 */
@SuppressWarnings("serial")
public class FormatIncorrectException extends Exception {
	
	public FormatIncorrectException (String message) {
		super(message);
	}
}
