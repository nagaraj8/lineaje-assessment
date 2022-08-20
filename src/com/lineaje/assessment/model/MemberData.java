/**
 * 
 */
package com.lineaje.assessment.model;

/**
 * @author HegdeNagaraj
 *
 */
public class MemberData {
	
	private String name;
	
	private String birthYear;
	
	private String deathYear;

	/**
	 * 
	 */
	public MemberData() {
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
	 * @param name
	 * @param birthYear
	 * @param deathYear
	 */
	public MemberData(String name, String birthYear, String deathYear) {
		super();
		this.name = name;
		this.birthYear = birthYear;
		this.deathYear = deathYear;
	}

	
}
