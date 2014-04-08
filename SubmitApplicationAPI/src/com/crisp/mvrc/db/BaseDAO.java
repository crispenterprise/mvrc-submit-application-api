package com.crisp.mvrc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BaseDAO {

	protected Connection dbConnection;
	
	String host = "localhost";
	String port = "3306";
	String database = "genaudit";
	
	String username = "root";
	String password = "";
	
	public BaseDAO() throws SQLException {
		
		try {                        
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception E) {
            System.err.println("Unable to load driver");
            E.printStackTrace();
        }
        
		String url =
            "jdbc:mysql://"+host+":"+port+"/"+database;
		
	
			
			dbConnection =
			    DriverManager.getConnection(
			                url,username, password);	
            
			
		/*} catch (SQLException e) {
			
			System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState:     " + e.getSQLState());
            System.out.println("VendorError:  " + e.getErrorCode());

            
		}*/
		
	}

}
