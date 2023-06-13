package DAO;

import java.sql.Connection;

public abstract class AbstractDAO<T> implements DAO<T> {
	protected Connection conn;
	
	public AbstractDAO (Connection conn) {
		this.conn=conn;
	}

}
