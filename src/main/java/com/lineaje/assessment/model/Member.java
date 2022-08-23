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
public class Member implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -208175566653578546L;

	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("BirthYear")
	private String birthYear;
	
	@JsonProperty("DeathYear")
	private String deathYear;
	
	@JsonProperty("Members")
	private List<Member> members = new ArrayList<>();
	
	

	/**
	 * 
	 */
	public Member() {
	}


	/**
	 * @param name
	 * @param birthYear
	 * @param deathYear
	 * @param members
	 */
	public Member(String name, String birthYear, String deathYear, List<Member> members) {
		this.name = name;
		this.birthYear = birthYear;
		this.deathYear = deathYear;
		this.members = members;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the birthYear
	 */
	public String getBirthYear() {
		return birthYear;
	}

	/**
	 * @param birthYear the birthYear to set
	 */
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	/**
	 * @return the deathYear
	 */
	public String getDeathYear() {
		return deathYear;
	}

	/**
	 * @param deathYear the deathYear to set
	 */
	public void setDeathYear(String deathYear) {
		this.deathYear = deathYear;
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
		return "Member [name=" + name + ", birthYear=" + birthYear + ", deathYear=" + deathYear + ", members=" + members
				+ "]";
	}
	
	

}
