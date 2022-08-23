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
public class FamilyIQRService implements IFamilyService{
	
	private static Logger logger = Logger.getLogger(FamilyIQRService.class.getName());
	

	/**
	 * 
	 */
	public FamilyIQRService() {
	}

	@Override
	public synchronized void processFamilyData(List<Member> members) throws LineajeException {
		try (FileWriter myWriter = new FileWriter("LineajeIQR.txt", true)) {
			logger.info("Finding Lineaje IQR and writing to file");
			List<MemberData> list = new Utils().getMemberData(members);
			Utils.sort(list);
			int mid = getMid(0, list.size());
			
			MemberData member1 = list.get(getMid(0, mid));
			
			MemberData member2 = list.get(getMid(mid+1, list.size()));
			
			int age1 = Integer.parseInt(member1.getDeathYear()) - Integer.parseInt(member1.getBirthYear());
			int age2 = Integer.parseInt(member2.getDeathYear()) - Integer.parseInt(member2.getBirthYear());
			myWriter.write("IQR Family Data \n");
			myWriter.write("IQR value is: "+(age2 - age1)+"\n");
		}catch(Exception e) {
			logger.warning("Error while processing the family Lineaje IQR: "+e);
			throw new LineajeException("Error while processing the family Lineaje IQR: "+e);
		}
	}


	private static int getMid(int i, int j) {
		int n = j - i + 1;
	    n = (n + 1) / 2 - 1;
	    return n + i;
	}

}
