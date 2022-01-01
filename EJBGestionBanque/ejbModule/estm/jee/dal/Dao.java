package estm.jee.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
	
	private static Connection con = null;
	private  static String url = "jdbc:mysql://localhost:3306/";
	private static String dbName = "gestionbanque";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String userName = "fayz";
	private static String password = "123123";
	public Dao() throws SQLException
	{
		
	}
	
	
	
	public static void deconnexion()
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getCnx() {
		  try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			con = DriverManager
			  .getConnection(url + dbName, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}



