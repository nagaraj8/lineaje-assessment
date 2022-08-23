/**
 * 
 */
package com.lineaje.assessment.service.impl;

import java.io.FileWriter;
import java.util.List;
import java.util.logging.Logger;

import com.lineaje.assessment.exception.LineajeException;
import com.lineaje.assessment.model.Member;
import com.lineaje.assessment.service.IFamilyService;

/**
 * @author HegdeNagaraj
 *
 */
public class LineajeRangeService implements IFamilyService{
	
	private static Logger logger = Logger.getLogger(LineajeRangeService.class.getName());
	
	private static int birthYear = 999999;
	private static int deathYear = 0;

	/**
	 * 
	 */
	public LineajeRangeService() {
	}

	@Override
	public void processFamilyData(List<Member> members) throws LineajeException {
		try (FileWriter myWriter = new FileWriter("LineajeRange.txt", true)) {
			range(members);
			logger.info("Finding range of the lineaje and writing file");
			myWriter.write("Birth Year: "+birthYear+"\n");
			myWriter.write("Death Year: "+deathYear+"\n");
			myWriter.write("Lineaje Range: "+(deathYear - birthYear)+"\n");
			myWriter.write("\n");
		} catch(Exception e) {
			logger.warning("Error while processing the family Lineaje Range: "+e.getCause());
			throw new LineajeException("Error while processing the family Lineaje Range: "+e.getCause());
		}
		
	}
	
	private static void range(List<Member> members) {
		if(members.isEmpty() || null == members) return;
		
		for(Member member : members) {
			
			int age = Integer.parseInt(member.getDeathYear()) - Integer.parseInt(member.getBirthYear());
			if(age > 0) {
				birthYear = Math.min(birthYear, Integer.parseInt(member.getBirthYear()));
				deathYear = Math.max(deathYear, Integer.parseInt(member.getDeathYear()));
				
				range(member.getMembers());
			}
			
			
		}
	}

}
