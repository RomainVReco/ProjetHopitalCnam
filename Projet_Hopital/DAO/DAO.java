package DAO;

import java.util.Optional;

import Exceptions.ErreurInterrogationBDD;

/**
 * Interface DAO pour les objets représentant l'hopital
 * 
 * Dans un travail de refactoring à venir, il faudra retirer les exceptions et ajouter des méthodes CRUD
 * 
 * @author Romain
 *
 * @param <T> type d'objet à accéder en base
 */
public interface DAO<T> {
	
	boolean create (T objet) throws ErreurInterrogationBDD;
	boolean update (T objet) ;
	Optional<T> findById (int objet);
	boolean delete (T objet) throws ErreurInterrogationBDD;

}
