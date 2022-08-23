/**
 * 
 */
package com.lineaje.assessment.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author HegdeNagaraj
 *
 */
public class LineajeMeta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8364181895167686619L;
	
	
	@JsonProperty("lineage")
	private Lineaje lineage;

	/**
	 * 
	 */
	public LineajeMeta() {
	}

	/**
	 * @return the lineaje
	 */
	public Lineaje getLineaje() {
		return lineage;
	}

	/**
	 * @param lineaje the lineaje to set
	 */
	public void setLineaje(Lineaje lineaje) {
		this.lineage = lineaje;
	}

	@Override
	public String toString() {
		return "LineajeMeta [lineaje=" + lineage + "]";
	}
	
	

}
