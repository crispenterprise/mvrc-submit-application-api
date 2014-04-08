package com.crisp.mvrc.util;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;

import com.crisp.mvrc.bean.ApplicationBO;
import com.crisp.mvrc.bean.PaymentBO;
import com.crisp.mvrc.bean.PersonBO;
import com.crisp.mvrc.bean.UserAccountBO;
import com.crisp.mvrc.db.ApplicationDAO;
import com.crisp.mvrc.db.PaymentDAO;



public class JsonUtil {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String encode(Integer eventAuditId, boolean status,String msg){
		String jsonString = "";
		
		Map debugMap = new HashMap();
		debugMap.put("data", "");
		debugMap.put("message", msg); 
		
		JSONObject debug = new JSONObject(debugMap);
		
		
		Map map = new HashMap();
		map.put("code", (status != true) ? 422 : 200 );
		map.put("application_id", eventAuditId );
		map.put("data",(status != true) ? null : "Application submitted : "+eventAuditId+" Successfully Created." );
		map.put("debug", debug);
		
		
		JSONObject jsonObj = new JSONObject(map);
		jsonString = jsonObj.toString();
		
		return jsonString;
	}
	
	public ApplicationBO decode (String jsonStr) //kim
	{
		
		ApplicationBO audObj = null;
	
		
		
		
		try{



			JSONObject audJasonOjb= new JSONObject(jsonStr);

			
			String dbHost = audJasonOjb.getString("dbHost");
			String dbName = audJasonOjb.getString("dbName");
			String dbUsername = audJasonOjb.getString("dbUsername");
			String dbPassword = audJasonOjb.getString("dbPassword");
			int dbPort = audJasonOjb.getInt("dbPort");

			
			
			
			String plateNo = audJasonOjb.getString("plate");			
			int renewalPeriod = audJasonOjb.getInt("renewal_period");
			
			String applicationNo = "";
			int accountNo = 0;
			Date newRegExpiryDate = null;
			String applicationStatus = "";


			audObj = new ApplicationBO(applicationNo, accountNo, plateNo, renewalPeriod, newRegExpiryDate, applicationStatus);

		}catch(Exception e){
			e.printStackTrace();
		}

		return audObj;
	}
	
	
	public int saveApplication (String jsonStr) throws SQLException //kim
	{
		
		int applicationNo = 0;
		
		ApplicationDAO applicationDAO = new ApplicationDAO();
		
		PaymentDAO paymentDAO = new PaymentDAO();
	
		
		try{



			JSONObject audJasonOjb= new JSONObject(jsonStr);

			
			/*
			String dbHost = audJasonOjb.getString("dbHost");
			String dbName = audJasonOjb.getString("dbName");
			String dbUsername = audJasonOjb.getString("dbUsername");
			String dbPassword = audJasonOjb.getString("dbPassword");
			int dbPort = audJasonOjb.getInt("dbPort");

			
			
			
			String plateNo = audJasonOjb.getString("plate");			
			int renewalPeriod = audJasonOjb.getInt("renewal_period");
			
			*/
			
			
			 Random generator = new Random(); 
			 int randomInt = generator.nextInt(10000000) + 1;

			
			//String trn = String.valueOf(randomInt);
			 String trn = audJasonOjb.getString("trn");
			 
			String firstName = audJasonOjb.getString("first_name");
			String lastName = audJasonOjb.getString("last_name");
			String username = audJasonOjb.getString("username");
			String cost = audJasonOjb.getString("cost");
			
			String plateNo = audJasonOjb.getString("plate");	
			String applicationStatus = "A";
			
					
			int renewPeriod = audJasonOjb.getInt("renewal_period");
			
			
			
			
			Double paymentAmmount = new Double(cost);
			
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
					applicationBO.setRenewalPeriod(renewPeriod);
					applicationBO.setNewRegExpiryDate(new Date(System.currentTimeMillis()));
					applicationBO.setApplicationStatus(applicationStatus);
					
					applicationNo = applicationDAO.insertApplication(applicationBO);
					
					if(applicationNo != 0){
						
						//save payment info
						PaymentBO paymentBO = new PaymentBO();
						paymentBO.setApplicationNo(applicationNo);
						paymentBO.setAmount(paymentAmmount);
						paymentBO.setPaymentDate(new Date(System.currentTimeMillis()));	
						
						paymentDAO.insertPayment(paymentBO);
						
					}

				}
				
				
			}
		

		}catch(Exception e){
			e.printStackTrace();
		}

		return applicationNo;
	}

}
