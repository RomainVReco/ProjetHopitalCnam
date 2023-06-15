package DAO;

import java.util.Optional;

import Exceptions.ErreurInterrogationBDD;

/**
 * Interface DAO pour les objets repr�sentant l'hopital
 * 
 * Dans un travail de refactoring � venir, il faudra retirer les exceptions et ajouter des m�thodes CRUD
 * 
 * @author Romain
 *
 * @param <T> type d'objet � acc�der en base
 */
public interface DAO<T> {
	
	boolean create (T objet) throws ErreurInterrogationBDD;
	boolean update (T objet) ;
	Optional<T> findById (int objet);
	boolean delete (T objet) throws ErreurInterrogationBDD;

}
