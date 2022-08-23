/**
 * 
 */
package com.lineaje.assessment.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.lineaje.assessment.exception.LineajeException;
import com.lineaje.assessment.model.Member;
import com.lineaje.assessment.model.MemberData;
import com.lineaje.assessment.service.IFamilyService;

/**
 * @author HegdeNagaraj
 *
 */
public class FamilyLinesWithShortestAndLongestService implements IFamilyService {
	
	private static Logger logger = Logger.getLogger(FamilyLinesWithShortestAndLongestService.class.getName());

	
	private static int familyLine = 0;
	
	/**
	 * 
	 */
	public FamilyLinesWithShortestAndLongestService() {
	}

	@Override
	public synchronized void processFamilyData(List<Member> members) throws LineajeException {
		int shorter = 99999;
		int longer = 0;
		
	    try {
	    	try (FileWriter myWriter = new FileWriter("FamilyLinesWithShortestAndLongest.txt", true)) {
				for(int i=0;i<members.size();i++) {
					process(members, myWriter, i);
					shorter = Math.min(familyLine, shorter);
					longer = Math.max(longer, familyLine);
					familyLine = 1;
				}
				
				myWriter.write("\n");
				myWriter.write("Shortest Family line is: "+shorter+"\n");
				
				myWriter.write("Longest Family line is: "+longer+"\n");
				myWriter.write("\n");
			}
	    } catch (Exception e) {
			logger.warning("Error while processing the family details for family lines with Shortest and Longest: "+e.getCause());
			throw new LineajeException("Error while processing the family details for family lines with Shortest and Longest: "+e.getCause());
		}
		familyLine = 0;
	}

	private void process(List<Member> members, FileWriter myWriter, int i) throws IOException {
		List<MemberData> memberDataList = new ArrayList<>();
		logger.info("Started writing the data to file");
		myWriter.write("Printing family tree for family: "+(i+1)+"\n");
		myWriter.write("Name: "+members.get(i).getName()+" Birth Year: "+members.get(i).getBirthYear()+" Death Year: "+members.get(i).getDeathYear()+"\n");
		getFamilyTree(memberDataList, members.get(i).getMembers());
		for(MemberData memberData : memberDataList) {
			myWriter.write("Name: "+memberData.getName()+" Birth Year: "+memberData.getBirthYear()+" Death Year: "+memberData.getDeathYear()+"\n");
		}
		memberDataList = null;
	}
	
	private void getFamilyTree(List<MemberData> memberDataList, List<Member> members) {
		if(members.isEmpty() || members == null) return;
		for(Member m : members) {
			int age = Integer.parseInt(m.getDeathYear()) - Integer.parseInt(m.getBirthYear());
			if(age < 0) {
				logger.warning("The age is invalid and this member: "+m.getName()+" cannot be considered further!!");
				continue;
			}
			MemberData memberData = new MemberData(m.getName(), m.getBirthYear(), m.getDeathYear());
			memberDataList.add(memberData);
			getFamilyTree(memberDataList, m.getMembers());
		}
		
		familyLine++;
	}

}
