package com.crisp.mvrc.api;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;





public class TestApplicationAPI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	    try{
	    	
	    	URL url = null;
	       
	    	//url = new URL("http://localhost:8080/Auditing/Audit");
	    	
	    	// JSON test web service URL
	    	//url = new URL("http://date.jsontest.com");
	    	
	    	HttpURLConnection urlConn = null;
			/*	
			urlConn = (HttpURLConnection) url.openConnection();
			
			urlConn.setDoInput (true);
			
			urlConn.setDoOutput (true);
			
			urlConn.setRequestMethod("POST");
			
			urlConn.setRequestProperty("Content-Type", "application/json");
	
			urlConn.connect();
			*/
			DataOutputStream output = null;

			DataInputStream input = null;
			
			//output = new DataOutputStream(urlConn.getOutputStream());
			
			  
			JSONObject obj=new JSONObject();
			obj.put("dbHost","localhost");
			obj.put("dbPort",new Integer(3306));
			obj.put("dbName","genaudit");
			obj.put("dbUsername","root");
			obj.put("dbPassword","");
			
			
			
			
			Random generator = new Random(); 
			int randomInt = generator.nextInt(10000000) + 1;			
			String trn = String.valueOf(randomInt);			
					
			obj.put("renewal_period",new Integer(7));			
			obj.put("plate","1234Ew");			
			obj.put("trn",trn);			
			obj.put("first_name","John");
			obj.put("last_name","Doe");
			obj.put("username","jdoe");
			obj.put("cost","3000.00");
			obj.put("creditcard_no","23234342342");
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			System.out.println("JSon Request Parameter: "+obj.toString());
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost post = new HttpPost("http://localhost:8080/SubmitApplicationAPI/submit_application");
			//HttpPost post = new HttpPost("http://date.jsontest.com");
			CloseableHttpResponse resp = null;
			
			
			 List<NameValuePair> params = new ArrayList<NameValuePair>();
		     params.add(new BasicNameValuePair("jsondata", obj.toString()));
			
		     post.setEntity(new UrlEncodedFormEntity(params));
	            
		     resp = httpClient.execute(post);
		     
		     System.out.println("Status Code:  "+ resp.getStatusLine().getStatusCode());
		    
		     BufferedReader rd = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));

		     StringBuffer result = new StringBuffer();
		     String line = "";
		     while ((line = rd.readLine()) != null) {
		         result.append(line);
		     }
		     System.out.println("Result: " + result);
		     
		     
			/*
			String firstName = audJasonOjb.getString("first_name");
			String lastName = audJasonOjb.getString("last_name");
			String username = audJasonOjb.getString("username");
			String cost = audJasonOjb.getString("cost");
			*?
			
			System.out.println("JSon Request Parameter: "+obj.toString());
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost post = new HttpPost("http://localhost:8080/MVRCRenewal/submit_application");
			//HttpPost post = new HttpPost("http://date.jsontest.com");
			CloseableHttpResponse resp = null;
			
			
			 List<NameValuePair> params = new ArrayList<NameValuePair>();
		     params.add(new BasicNameValuePair("jsondata", obj.toString()));
			
		     post.setEntity(new UrlEncodedFormEntity(params));
	            
		     resp = httpClient.execute(post);
		     
		     System.out.println("Status Code:  "+ resp.getStatusLine().getStatusCode());
		    
		     BufferedReader rd = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));

		     StringBuffer result = new StringBuffer();
		     String line = "";
		     while ((line = rd.readLine()) != null) {
		         result.append(line);
		     }
		     System.out.println("Result: " + result);
			
			 
			 /* Send the request data.*/
			// output.writeBytes(obj.toString());
			// output.flush();
			// output.close();
			
			 /* Get response data.*/
			/*String response = "";
			String jsonString = "";
			input = new DataInputStream (urlConn.getInputStream());
			
			while (null != ((response = input.readLine()))) {
				jsonString = jsonString + response;
			}
			System.out.println("JSON Response: " + jsonString);
			 input.close ();*/
		}
	    
	    
	    catch(Exception e){
				   e.printStackTrace();
		}		

	}

}
