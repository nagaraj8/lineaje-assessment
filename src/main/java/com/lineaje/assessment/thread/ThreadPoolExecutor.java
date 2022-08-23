/**
 * 
 */
package com.lineaje.assessment.thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.lineaje.assessment.model.LineajeMeta;
import com.lineaje.assessment.service.IFamilyService;
import com.lineaje.assessment.service.impl.FamilyIQRService;
import com.lineaje.assessment.service.impl.FamilyLinesWithShortestAndLongestService;
import com.lineaje.assessment.service.impl.FamilyMedianService;
import com.lineaje.assessment.service.impl.FamilyMembersAndAgeService;
import com.lineaje.assessment.service.impl.FamilyOrderingService;
import com.lineaje.assessment.service.impl.FamilyYoungestAndLongestService;
import com.lineaje.assessment.service.impl.LineajeMeanService;
import com.lineaje.assessment.service.impl.LineajeRangeService;
import com.lineaje.assessment.utility.JsonReader;


/**
 * @author HegdeNagaraj
 *
 */
public class ThreadPoolExecutor {

	private static Logger logger = Logger.getLogger(ThreadPoolExecutor.class.getName());
	private static final ExecutorService pool = Executors.newCachedThreadPool();
	
	public static void processFolder(String inputPath) throws InterruptedException, FileNotFoundException {
		File inputFolder = new File(inputPath);
		for (String filename : inputFolder.list()) {
			String filePath = inputFolder.toPath().resolve(filename).toString();
			System.out.println("----------------------------------Generating the report for file: "+filename+"------------------------------------");
			pool.execute(() -> {
				logger.info("Start processing " + filePath);
				try {
					generateData(filePath);
				} catch (Exception e) {
					logger.warning("Error while creating the lineaje data: " + e);
				}
			});
			pool.awaitTermination(1, TimeUnit.MINUTES);
		}
	}
	
	
	private static void generateData(String filePath) throws Exception {
		LineajeMeta meta = new JsonReader().readJsonFile(filePath);
		IFamilyService familyLineService= new FamilyLinesWithShortestAndLongestService();
		IFamilyService familyMemberAgeService= new FamilyMembersAndAgeService();
		IFamilyService lineajeRangeService= new LineajeRangeService();
		IFamilyService lineajeMeanService = new LineajeMeanService();
		IFamilyService familyMedianService= new FamilyMedianService();
		IFamilyService familyIQRService= new FamilyIQRService();
		IFamilyService familyYoungestAndLongestService= new FamilyYoungestAndLongestService();
		IFamilyService orderFamilyService = new FamilyOrderingService();
		
		
		logger.info("Printing Individual Family Lines with Shortest and Longest");
		familyLineService.processFamilyData(meta.getLineaje().getMembers());
		
		logger.info("Printing All Family members and their Age");
		familyMemberAgeService.processFamilyData(meta.getLineaje().getMembers());

		logger.info("Ordering the Family members based on the age");
		orderFamilyService.processFamilyData(meta.getLineaje().getMembers());
		
		logger.info("Range of the Lineaje");
		lineajeRangeService.processFamilyData(meta.getLineaje().getMembers());
		
		logger.info("Mean age of all the members of lineaje");
		lineajeMeanService.processFamilyData(meta.getLineaje().getMembers());
		
		logger.info("Median age of all the members of lineaje");
		familyMedianService.processFamilyData(meta.getLineaje().getMembers());
		
		logger.info("InterQuartile Range of all the members of lineaje");
		familyIQRService.processFamilyData(meta.getLineaje().getMembers());
		
		logger.info("Youngest and Longest Died Members of Lineaje");
		familyYoungestAndLongestService.processFamilyData(meta.getLineaje().getMembers());
	}

}
