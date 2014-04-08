package com.crisp.mvrc.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.crisp.mvrc.bean.ApplicationBO;
import com.crisp.mvrc.bean.PersonBO;
import com.crisp.mvrc.bean.UserAccountBO;


public class ApplicationDAO extends BaseDAO {


	public ApplicationDAO() throws SQLException {
		super();
		
	}


	public int insertApplication(ApplicationBO application) throws SQLException {
		
		int last_inserted_id = 0;
		
			try {
				
				PreparedStatement stmt = null;
							
				String query = "insert into application (account_no, plate_no, renewal_period, new_reg_expiry_date, application_status) VALUES (?, ?, ?, ?, ?)";
			
				stmt = dbConnection.prepareStatement(query);
			
					stmt.setInt(1, application.getAccountNo());
					stmt.setString(2, application.getPlateNo());
					stmt.setInt(3, application.getRenewalPeriod());
					stmt.setDate(4, application.getNewRegExpiryDate());
					stmt.setString(5, application.getApplicationStatus());
				
				
					
					 int count = stmt.executeUpdate();
					 
					 
					 ResultSet rs = stmt.getGeneratedKeys();
					 if(rs.next())
					 {
						 last_inserted_id = rs.getInt(1);
					 }
		                
		                
				     System.out.println(count + "row(s) affected "+last_inserted_id);
					
				     stmt.close();
				
						        
	            
	            
				
			} catch (SQLException e) {
				
				System.out.println("SQLException: " + e.getMessage());
	            System.out.println("SQLState:     " + e.getSQLState());
	            System.out.println("VendorError:  " + e.getErrorCode());
	
	            
			}
			
		return last_inserted_id;
		
	}
	
	
public int insertPerson(PersonBO personBO) throws SQLException {
		
		int last_inserted_id = 0;
		
			try {
				
				PreparedStatement stmt = null;
		
				
				//String query = "insert into person (trn, first_name, last_name, street_no, street_name, parish, home_tel, cell_tel, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				String query = "insert into person (trn, first_name, last_name) VALUES (?, ?, ?)";
			
				
					stmt = dbConnection.prepareStatement(query);
			
					stmt.setString(1, personBO.getTrn());
					stmt.setString(2, personBO.getFirstName());
					stmt.setString(3, personBO.getLastName());
					
				
					
					 int count = stmt.executeUpdate();
					 
					 
					 ResultSet rs = stmt.getGeneratedKeys();
					 if(rs.next())
					 {
						 last_inserted_id = rs.getInt(1);
					 }
		                
		                
				     System.out.println(count + "row(s) affected "+last_inserted_id);
					
				     stmt.close();
				
						        
	            
	            
				
			} catch (SQLException e) {
				
				System.out.println("SQLException: " + e.getMessage());
	            System.out.println("SQLState:     " + e.getSQLState());
	            System.out.println("VendorError:  " + e.getErrorCode());
	
	            
			}
			
		return last_inserted_id;
		
	}
	

	public int insertUserAccount(UserAccountBO userAccountBO) throws SQLException {
		
		int last_inserted_id = 0;
		
			try {
				
				PreparedStatement stmt = null;
		
					
				String query = "insert into user_account (person_id, username) VALUES (?, ?)";
			
				
					stmt = dbConnection.prepareStatement(query);
			
					stmt.setInt(1, userAccountBO.getPersonId());
					stmt.setString(2, userAccountBO.getUsername());
													
					
					 int count = stmt.executeUpdate();
					 
					 
					 ResultSet rs = stmt.getGeneratedKeys();
					 if(rs.next())
					 {
						 last_inserted_id = rs.getInt(1);
					 }
		                
		                
				     System.out.println(count + "row(s) affected "+last_inserted_id);
					
				     stmt.close();
				
						        
	            
	            
				
			} catch (SQLException e) {
				
				System.out.println("SQLException: " + e.getMessage());
	            System.out.println("SQLState:     " + e.getSQLState());
	            System.out.println("VendorError:  " + e.getErrorCode());
	
	            
			}
			
		return last_inserted_id;
		
	}
	
	
	
}
