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
public class FamilyMedianService implements IFamilyService{
	
	private static Logger logger = Logger.getLogger(FamilyMedianService.class.getName());
	

	/**
	 * 
	 */
	public FamilyMedianService() {
	}

	@Override
	public synchronized void processFamilyData(List<Member> members) throws LineajeException {
		try (FileWriter myWriter = new FileWriter("LineajeMedian.txt", true)) {
			if(members.isEmpty() || null == members) return;
			logger.info("Finding the median of the lineaje and writing to file");
			int median = 0;
			List<MemberData> list = new Utils().getMemberData(members);
			Utils.sort(list);
			if(list.size()%2 == 0) {
				median = getMedian(list);
			} else {
				MemberData member = list.get(list.size()/2);
				median = Integer.parseInt(member.getDeathYear()) - Integer.parseInt(member.getBirthYear());
			}
			myWriter.write("The median age of this lineaje is: "+median+"\n");
		}catch(Exception e) {
			logger.warning("Error while processing the family Lineaje Median: "+e.getCause());
			throw new LineajeException("Error while processing the family Lineaje Median: "+e.getCause());
		}
	}

	private int getMedian(List<MemberData> list) {
		int median;
		MemberData member1 = list.get(list.size()/2);
		MemberData member2 = list.get((list.size()/2)-1);
		int age1 = Integer.parseInt(member1.getDeathYear()) - Integer.parseInt(member1.getBirthYear());
		int age2 = Integer.parseInt(member2.getDeathYear()) - Integer.parseInt(member2.getBirthYear());
		median =  (age1+age2)/2;
		return median;
	}

}
