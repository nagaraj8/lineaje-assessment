/**
 * 
 */
package com.lineaje.assessment.service.impl;

import java.io.FileWriter;
import java.util.List;
import java.util.logging.Logger;

import com.lineaje.assessment.exception.LineajeException;
import com.lineaje.assessment.model.Member;
import com.lineaje.assessment.model.MemberData;
import com.lineaje.assessment.service.IFamilyService;
import com.lineaje.assessment.utility.Utils;

/**
 * @author HegdeNagaraj
 *
 */
public class LineajeMeanService implements IFamilyService{
	
	private static Logger logger = Logger.getLogger(LineajeMeanService.class.getName());

	private int sumOfAllAges = 0;
	
	/**
	 * 
	 */
	public LineajeMeanService() {
	}

	@Override
	public synchronized void processFamilyData(List<Member> members) throws LineajeException {
		try (FileWriter myWriter = new FileWriter("LineajeMean.txt", true)) {
			findMean(members);
			List<MemberData> list = new Utils().getMemberData(members);
			int mean = sumOfAllAges/list.size();
			logger.info("Finding mean of the lineaje and writing file");
			myWriter.write("Mean Age of Lineaje: "+mean+"\n");
		}catch(Exception e) {
			logger.warning("Error while processing the family Lineaje Mean: "+e.getCause());
			throw new LineajeException("Error while processing the family Lineaje Mean: "+e.getCause());
		}
	}


	public void findMean(List<Member> members) {
		if(members.isEmpty() || null == members) return;
		
		for(Member member : members) {
			int age = Integer.parseInt(member.getDeathYear()) - Integer.parseInt(member.getBirthYear());
			if(age > 0) {
				sumOfAllAges += age;
				findMean(member.getMembers());
			}
		}
	}
}
