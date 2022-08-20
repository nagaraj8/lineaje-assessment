package com.lineaje.assessment.evaluation.test;

import java.util.logging.Logger;

import com.leneaje.assessment.thread.ThreadPoolExecutor;

public class FamilytreeTest {

	private static Logger logger = Logger.getLogger(FamilytreeTest.class.getName());
	
	
	public static void main(String[] args) {
		try {
			ThreadPoolExecutor.processFolder("C:\\Users\\HegdeNagaraj\\eclipse-workspace\\lineaje-assessment\\src\\resources\\", "C:\\Users\\HegdeNagaraj\\eclipse-workspace\\lineaje-assessment\\src\\resources\\");
		} catch (Exception e) {
			logger.warning("Error while creating the lineaje data: "+e);
		}
	}

	
	
	
	
	
	
}
