package com.crisp.mvrc.db;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Random;

import com.crisp.mvrc.bean.ApplicationBO;
import com.crisp.mvrc.bean.PaymentBO;
import com.crisp.mvrc.bean.PersonBO;
import com.crisp.mvrc.bean.UserAccountBO;

public class TestDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			
			ApplicationDAO applicationDAO = new ApplicationDAO();
			
			PaymentDAO paymentDAO = new PaymentDAO();
		
			
			/*
			
				
			ApplicationBO applicationBO = new ApplicationBO();			
			applicationBO.setAccountNo(5);
			applicationBO.setPlateNo("1234EZ");
			applicationBO.setRenewalPeriod(6);
			applicationBO.setNewRegExpiryDate(new Date(System.currentTimeMillis()));
			applicationBO.setApplicationStatus("P");
						
			int applicationNo = applicationDAO.insertApplication(applicationBO);
			
			if(applicationNo != 0){
				
				PaymentBO paymentBO = new PaymentBO();
				paymentBO.setApplicationNo(applicationNo);
				paymentBO.setAmount(new Double("3000.00"));
				paymentBO.setPaymentDate(new Date(System.currentTimeMillis()));	
				
				paymentDAO.insertPayment(paymentBO);
			}
			
			*/
			
			 Random generator = new Random(); 
			 int randomInt = generator.nextInt(10000000) + 1;

			
			String trn = String.valueOf(randomInt);
			String firstName = "Jerome";
			String lastName = "Campbell";
			String username = "jcamp";
			
			String plateNo = "1234EZ";			
			String applicationStatus = "A";
			
			String paymentAmmount = "5000.00";
			
			//save person info
			PersonBO personBO = new PersonBO();
					
			personBO.setTrn(trn);
			personBO.setFirstName(firstName);
			personBO.setLastName(lastName);
			
			int personId = applicationDAO.insertPerson(personBO);
			
			if(personId != 0){
				
				//save account info
				UserAccountBO accountBO = new UserAccountBO();
				accountBO.setPersonId(personId);
				accountBO.setUsername(username);
				
				int userAccountId = applicationDAO.insertUserAccount(accountBO);
				
				if(userAccountId != 0){
					
					//save application info
					ApplicationBO applicationBO = new ApplicationBO();			
					applicationBO.setAccountNo(userAccountId);
					applicationBO.setPlateNo(plateNo);
					applicationBO.setRenewalPeriod(6);
					applicationBO.setNewRegExpiryDate(new Date(System.currentTimeMillis()));
					applicationBO.setApplicationStatus(applicationStatus);
					
					int applicationNo = applicationDAO.insertApplication(applicationBO);
					
					if(applicationNo != 0){
						
						//save payment info
						PaymentBO paymentBO = new PaymentBO();
						paymentBO.setApplicationNo(applicationNo);
						paymentBO.setAmount(new Double(paymentAmmount));
						paymentBO.setPaymentDate(new Date(System.currentTimeMillis()));	
						
						paymentDAO.insertPayment(paymentBO);
						
					}
				}
				
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
