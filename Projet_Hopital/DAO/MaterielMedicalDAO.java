package DAO;

import java.sql.Connection;
import java.util.Optional;

import Exceptions.ErreurInterrogationBDD;
import Hopital.MaterielMedical;

/**
 * DAO gérant les opérations CRUD pour l'objet MaterielMedical
 * 
 * A finir de coder
 * 
 * Non utilisé dans la version actuelle du logiciel
 * 
 * @author Romain
 *
 */
public class MaterielMedicalDAO extends AbstractDAO<MaterielMedical> {

	public MaterielMedicalDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(MaterielMedical objet) throws ErreurInterrogationBDD {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(MaterielMedical objet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<MaterielMedical> findById(int objet) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean delete(MaterielMedical objet) throws ErreurInterrogationBDD {
		// TODO Auto-generated method stub
		return false;
	}

}
