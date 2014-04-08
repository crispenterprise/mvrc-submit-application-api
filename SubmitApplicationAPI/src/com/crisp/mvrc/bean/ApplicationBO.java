package com.crisp.mvrc.bean;

import java.sql.Date;

public class ApplicationBO {

	private String applicationNo;
	private int accountNo;
	private String plateNo;
	private int renewalPeriod;
	private Date newRegExpiryDate;
	private String applicationStatus;
	
	
	
	public ApplicationBO(String applicationNo, int accountNo, String plateNo,
			int renewalPeriod, Date newRegExpiryDate, String applicationStatus) {
		super();
		this.applicationNo = applicationNo;
		this.accountNo = accountNo;
		this.plateNo = plateNo;
		this.renewalPeriod = renewalPeriod;
		this.newRegExpiryDate = newRegExpiryDate;
		this.applicationStatus = applicationStatus;
	}
	
	public ApplicationBO() {
		// TODO Auto-generated constructor stub
	}

	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	
		
	public Date getNewRegExpiryDate() {
		return newRegExpiryDate;
	}
	public void setNewRegExpiryDate(Date newRegExpiryDate) {
		this.newRegExpiryDate = newRegExpiryDate;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public int getRenewalPeriod() {
		return renewalPeriod;
	}
	public void setRenewalPeriod(int renewalPeriod) {
		this.renewalPeriod = renewalPeriod;
	}
	
	
	
	
}
