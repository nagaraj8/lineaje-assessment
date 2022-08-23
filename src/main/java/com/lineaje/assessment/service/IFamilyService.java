/**
 * 
 */
package com.lineaje.assessment.service;

import java.util.List;

import com.lineaje.assessment.exception.LineajeException;
import com.lineaje.assessment.model.Member;

/**
 * @author HegdeNagaraj
 *
 */
public interface IFamilyService {
	
	public void processFamilyData(List<Member> members) throws LineajeException;

}
