package com.crisp.mvrc.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.crisp.mvrc.bean.ApplicationBO;
import com.crisp.mvrc.bean.PaymentBO;


public class PaymentDAO extends BaseDAO {


	public PaymentDAO() throws SQLException {
		super();
		
	}


	public void insertPayment(PaymentBO paymentBO){
		
		
			try {
				
				PreparedStatement stmt = null;
							
				String query = "insert into payment (application_no, payment_dtime, amount) VALUES (?, ?, ?)";
			
				stmt = dbConnection.prepareStatement(query);
			
					stmt.setInt(1, paymentBO.getApplicationNo());
					stmt.setDate(2, paymentBO.getPaymentDate());
					stmt.setDouble(3, paymentBO.getAmount());
					
				
					
					 int count = stmt.executeUpdate();
				     System.out.println(count + "row(s) affected");
					
				     stmt.close();
				
						        
	            
	            
				
			} catch (SQLException e) {
				
				System.out.println("SQLException: " + e.getMessage());
	            System.out.println("SQLState:     " + e.getSQLState());
	            System.out.println("VendorError:  " + e.getErrorCode());
	
	            
			}
	}
	
	
	
	
}
