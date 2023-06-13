package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {
	
	String url="jdbc:mysql://localhost/tp_jdbc";
	String login="root";
	String password="";
	private static String loginSalarie="";
	private static Connection conn;
	
	private SingleConnection () {
	try {
		conn=DriverManager.getConnection(url,login,password);
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public static Connection getInstance() {
		if (conn==null) new SingleConnection() ;
		return conn;
	}

	public static String getNomSalarie() {
		return loginSalarie;
	}
	
	public static void setNomSalarie(String str) {
		SingleConnection.loginSalarie=str;
	}


}
