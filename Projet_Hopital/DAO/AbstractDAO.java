package DAO;

import java.sql.Connection;

/**
 * Classe abstraite impl�mentant l'interface DAO et portant une variable d'instance d�di�e � la connection
 * et initialis� au moment de la cr�ation du DAO concret
 * @author Romain
 *
 * @param <T> type d'objet que va g�rer le DAO
 */
public abstract class AbstractDAO<T> implements DAO<T> {
	protected Connection conn;
	
	public AbstractDAO (Connection conn) {
		this.conn=conn;
	}

}
