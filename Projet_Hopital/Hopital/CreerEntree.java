package Hopital;

import java.util.*;

/**
 * @param <T>
 * 
 */
public interface CreerEntree {

	/**
	 * 
	 */
	public void creerEntree();

	/**
	 * @param T identifiant
	 */
	public void modifierEntree(String identifiant);

	/**
	 * 
	 */
	public void supprimerEntree();

	/**
	 * @param <T>
	 * @param T   identifiant
	 */
	public <T> T rechercherEntree(String identifiant);

}