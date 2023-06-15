package DAO;

import java.sql.Connection;

/**
 * Classe abstraite implémentant l'interface DAO et portant une variable d'instance dédiée à la connection
 * et initialisé au moment de la création du DAO concret
 * @author Romain
 *
 * @param <T> type d'objet que va gérer le DAO
 */
public abstract class AbstractDAO<T> implements DAO<T> {
	protected Connection conn;
	
	public AbstractDAO (Connection conn) {
		this.conn=conn;
	}

}
