package com.jmr.testsuite.obdx;

public class CommonUtils {
	
	public static <T> boolean validInput(T a) {
		return (a == null || a.equals("")) ? false : true;
	}


}
