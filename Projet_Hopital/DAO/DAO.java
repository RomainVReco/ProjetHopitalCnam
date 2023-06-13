package DAO;

import java.util.Optional;

import Exceptions.ErreurInterrogationBDD;

public interface DAO<T> {
	
	boolean create (T objet) throws ErreurInterrogationBDD;
	boolean update (T objet) ;
	Optional<T> findById (int objet);
	boolean delete (T objet) throws ErreurInterrogationBDD;

}
