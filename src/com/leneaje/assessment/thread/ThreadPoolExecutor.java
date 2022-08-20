/**
 * 
 */
package com.leneaje.assessment.thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.lineaje.assessment.model.LineajeMeta;
import com.lineaje.assessment.service.FamilyService;
import com.lineaje.assessment.utility.JsonReader;

/**
 * @author HegdeNagaraj
 *
 */
public class ThreadPoolExecutor {

	private static Logger logger = Logger.getLogger(ThreadPoolExecutor.class.getName());
	private static final ExecutorService pool = Executors.newCachedThreadPool();
	
	public static void processFolder(String inputPath, String outputPath) throws InterruptedException, FileNotFoundException {
		File inputFolder = new File(inputPath);
		for (String filename : inputFolder.list()) {
			String filePath = inputFolder.toPath().resolve(filename).toString();
			File file = new File(outputPath+new SimpleDateFormat("yyyyMMddHHmmSSMM'.txt'").format(new Date())); 
			FileOutputStream fos = new FileOutputStream(file);
			PrintStream ps = new PrintStream(fos);
			System.setOut(ps);
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
	
	
	private static synchronized void generateData(String filePath) throws Exception {
		LineajeMeta meta = new JsonReader().readJsonFile(filePath);
		logger.info("Printing Individual Family Lines with Shortest and Longest");
		System.out.println("Printing Individual Family Lines with Shortest and Longest");
		FamilyService.individualFamilyLinesWithShortestAndLongest(meta.getLineaje().getMembers());
		
		logger.info("Printing All Family members and their Age");
		System.out.println("Printing All Family members and their Age");
		FamilyService.printAllFamilyMembersAndTheirAge(meta.getLineaje().getMembers());

		logger.info("Ordering the Family members based on the age");
		System.out.println("Ordering the Family members based on the age");
		FamilyService.orderAgeOfFamilyMembers(meta.getLineaje().getMembers());
		
		logger.info("Range of the Lineaje");
		System.out.println("Range of the Lineaje");
		FamilyService.lineajeRange(meta.getLineaje().getMembers());
		
		logger.info("Mean age of all the members of lineaje");
		System.out.println("Mean age of all the members of lineaje");
		FamilyService.meanAgeOfLineaje(meta.getLineaje().getMembers());
		
		logger.info("Median age of all the members of lineaje");
		System.out.println("Median age of all the members of lineaje");
		FamilyService.findMedian(meta.getLineaje().getMembers());
		
		logger.info("InterQuartile Range of all the members of lineaje");
		System.out.println("InterQuartile Range of all the members of lineaje");
		FamilyService.findIQR();
		
		logger.info("Youngest and Longest Died Members of Lineaje");
		System.out.println("Youngest and Longest Died Members of Lineaje");
		FamilyService.findYoungestAndLongestDied();
	}

}
