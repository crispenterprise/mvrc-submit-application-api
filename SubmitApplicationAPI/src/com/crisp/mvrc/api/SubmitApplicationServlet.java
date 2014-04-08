package com.crisp.mvrc.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crisp.mvrc.util.JsonUtil;

/**
 * Servlet implementation class SubmitApplication
 */
@WebServlet("/submit_application")
public class SubmitApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitApplicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		

				
				boolean successFlag = false;
				int applicatedId = 0;

				String errMsg = "";
				JsonUtil util = new JsonUtil();
				
				try {
					applicatedId = util.saveApplication((String)request.getParameter("jsondata"));
				} catch (SQLException e1) {
					successFlag = false;
					errMsg = e1.getMessage();
					e1.printStackTrace();
				}
				 
				//ApplicationBO applicationBO = util.decode((String)request.getParameter("jsondata"));
				
				if(applicatedId != 0){

					System.out.println("application data decoded");
					//try {

						//ApplicationDAO applicationDAO = new ApplicationDAO();

						//applicatedId = applicationDAO.insertApplication(applicationBO);

						/*
						applicationBO = new ApplicationBO();			
						applicationBO.setAccountNo(5);
						applicationBO.setPlateNo("1234EZ");
						applicationBO.setRenewalPeriod(6);
						applicationBO.setNewRegExpiryDate(new Date(System.currentTimeMillis()));
						applicationBO.setApplicationStatus("P");
									
						int applicationNo = applicationDAO.insertApplication(applicationBO);
						*/
						
						if(applicatedId > 0){
							successFlag = true;
						}else{
							successFlag = false;
						}

//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						successFlag = false;
//						errMsg = e.getMessage();
//						e.printStackTrace();
//
//					}

				}else{					
					//errMsg = "invalid input";
					
					System.out.println("application data error");
					
				}
				
				System.out.println("Application submission Status: " + successFlag);
				
				String jsonResp = util.encode(applicatedId, successFlag, errMsg);
				
				//if(successFlag){
				if(true){
					response.setStatus(200);
				
				// Set response content type
			      response.setContentType("text/html");
			      
			      // Actual logic goes here.
			      PrintWriter outHtml = response.getWriter();
			      
			      outHtml.println(jsonResp);
			      
				}else{
					response.sendError(422,jsonResp);
				}
				
				System.out.println("jsonResp: "+ jsonResp);
				
				
	}

}
