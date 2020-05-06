package Util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {
	
	
	public Connection connect(){
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/paf", "root", "");
			
			// For testing print success message
						System.out.println("Successfully Connected");
					} catch (Exception e) {
						e.printStackTrace();
					}
					return con;

				}
		

}
