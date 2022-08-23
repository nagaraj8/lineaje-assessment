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
import com.lineaje.assessment.model.MemberData;
import com.lineaje.assessment.service.IFamilyService;
import com.lineaje.assessment.utility.Utils;

/**
 * @author HegdeNagaraj
 *
 */
public class FamilyYoungestAndLongestService implements IFamilyService{
	
	
	private static Logger logger = Logger.getLogger(FamilyYoungestAndLongestService.class.getName());
	
	
	@Override
	public void processFamilyData(List<Member> members) throws LineajeException {
		try (FileWriter myWriter = new FileWriter("LineajeYoungestAndLongestDeath.txt", true)) {
			List<MemberData> list = new Utils().getMemberData(members);
			Utils.sort(list);
			processAndWriteToFile(myWriter, list);
		}catch(Exception e) {
			logger.warning("Error while processing the Youngest and Longest Deaths of Lineaje: "+e);
			throw new LineajeException("Error while processing the Youngest and Longest Deaths of Lineaje: "+e);
		}
		
	}


	private void processAndWriteToFile(FileWriter myWriter, List<MemberData> list) throws IOException {
		MemberData member1 = list.get(0);
		
		MemberData member2 = list.get(list.size()-1);
		
		int age1 = Integer.parseInt(member1.getDeathYear()) - Integer.parseInt(member1.getBirthYear());
		int age2 = Integer.parseInt(member2.getDeathYear()) - Integer.parseInt(member2.getBirthYear());
		logger.info("Getting the youngest and Longest Deaths and writing to file");
		
		myWriter.write("Youngest and Longest Deaths\n");
		myWriter.write("Youngest Death, Name: "+member1.getName()+" and age: "+age1+"\n");
		
		myWriter.write("Longest Death, Name: "+member2.getName()+" and age: "+age2+"\n");
	}
}
