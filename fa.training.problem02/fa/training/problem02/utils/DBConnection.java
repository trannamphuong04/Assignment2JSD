package fa.training.problem02.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection con = null;
	private static final String URL = "jdbc:mysql://localhost:3307/jpl_test01";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "Ohmyshit123456.";
	
	private DBConnection() {
	}
	
	/**
	 *  Get the connection from the instance
	 * 
	 *  @return {@link Connection}

	 */
	public static Connection getConnection() {
		try {
			if(con==null || con.isClosed()) {
				openConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
		
	}
	
	public static synchronized void openConnection() {
		try {
			con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(){
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
