package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton pour ouvrir la connexion avec la base de données.
 * Les caractéristiques de connexion sont stockées dans la classe. Pour plus de sécurité, elles devront être déportées dans une configuration dédiée.
 * 
 *  La variable static {@code loginSalarie} permet de stocker le login de l'utilisateur connecté afin de le réafficher sur les vues affichées à l'utilisateur
 * 
 * @author Romain
 */
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
