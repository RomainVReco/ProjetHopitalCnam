package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton pour ouvrir la connexion avec la base de donn�es.
 * Les caract�ristiques de connexion sont stock�es dans la classe. Pour plus de s�curit�, elles devront �tre d�port�es dans une configuration d�di�e.
 * 
 *  La variable static {@code loginSalarie} permet de stocker le login de l'utilisateur connect� afin de le r�afficher sur les vues affich�es � l'utilisateur
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
