package com.crisp.mvrc.util;

import java.sql.SQLException;

public class TestJsonUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		boolean successFlag = false;
		int applicatedId = 0;
		
		String errMsg = "";
		JsonUtil util = new JsonUtil();
		
		try {
			applicatedId = util.saveApplication("dummy json");
		} catch (SQLException e1) {
			successFlag = false;
			errMsg = e1.getMessage();
			e1.printStackTrace();
		}
		
		
	}

}
