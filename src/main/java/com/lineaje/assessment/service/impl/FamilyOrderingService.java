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
public class FamilyOrderingService implements IFamilyService{
	
	private static Logger logger = Logger.getLogger(FamilyOrderingService.class.getName());
	
	/**
	 * 
	 */
	public FamilyOrderingService() {
	}

	@Override
	public void processFamilyData(List<Member> members) throws LineajeException {
		try (FileWriter myWriter = new FileWriter("FamilyOrdering.txt", true)) {
			List<MemberData> list = new Utils().getMemberData(members);
			Utils.sort(list);				
			process(myWriter, list);
			myWriter.write("\n");
		} catch (Exception e) {
			logger.warning("Error while processing the family Ordering: "+e.getCause());
			throw new LineajeException("Error while processing the family Ordering: "+e.getCause());
		}
		
	}

	private void process(FileWriter myWriter, List<MemberData> list) throws IOException {
		logger.info("Ordering the family members in ascending order based on age and writing to file");
		myWriter.write("After ordering in ascending order"+"\n");
		for(MemberData m : list) {
			int age = Integer.parseInt(m.getDeathYear()) - Integer.parseInt(m.getBirthYear());
			myWriter.write("Name: "+m.getName()+" and Age: "+age+"\n");
		}
	}
	
}
