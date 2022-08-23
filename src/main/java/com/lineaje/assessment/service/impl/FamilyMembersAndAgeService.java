/**
 * 
 */
package com.lineaje.assessment.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.lineaje.assessment.exception.LineajeException;
import com.lineaje.assessment.model.Member;
import com.lineaje.assessment.service.IFamilyService;

/**
 * @author HegdeNagaraj
 *
 */
public class FamilyMembersAndAgeService implements IFamilyService {

	private static Logger logger = Logger.getLogger(FamilyMembersAndAgeService.class.getName());
	
	/**
	 * 
	 */
	public FamilyMembersAndAgeService() {
	}

	@Override
	public void processFamilyData(List<Member> members) throws LineajeException {
		try (FileWriter myWriter = new FileWriter("FamilyMembersAndAge.txt", true)) {
			logger.info("Finding family memebrs and printing to file");
			myWriter.write("Family Members data\n");
			process(members, myWriter);
			myWriter.write("\n");
		}catch(Exception e) {
			logger.warning("Error while processing the family details for family members and age: "+e.getCause());
			throw new LineajeException("Error while processing the family details for family members and age: "+e.getCause());
		}
		
	}

	private void process(List<Member> members, FileWriter myWriter) throws IOException {
		if(members.isEmpty() || null == members) return;
		
		for(Member member : members) {
			int age = Integer.parseInt(member.getDeathYear()) - Integer.parseInt(member.getBirthYear());
			if(age > 0) {
				myWriter.write("Name: "+member.getName()+" and Age: "+ age +"\n");
				process(member.getMembers(), myWriter);
			}
		}
	}

}
