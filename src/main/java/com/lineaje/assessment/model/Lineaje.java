/**
 * 
 */
package com.lineaje.assessment.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author HegdeNagaraj
 *
 */
public class Lineaje implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4624288149562990410L;

	@JsonProperty("FamilyTree")
	private String familyTree;
	
	
	@JsonProperty("Members")
	private List<Member> members = new ArrayList<>();
	
	

	/**
	 * 
	 */
	public Lineaje() {
	}

	/**
	 * @return the familyTree
	 */
	public String getFamilyTree() {
		return familyTree;
	}

	/**
	 * @param familyTree the familyTree to set
	 */
	public void setFamilyTree(String familyTree) {
		this.familyTree = familyTree;
	}

	/**
	 * @return the members
	 */
	public List<Member> getMembers() {
		return members;
	}

	/**
	 * @param members the members to set
	 */
	public void setMembers(List<Member> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return "Lineaje [familyTree=" + familyTree + ", members=" + members + "]";
	}
	
	

}
