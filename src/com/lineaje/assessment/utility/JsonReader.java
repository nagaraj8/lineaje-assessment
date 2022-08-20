/**
 * 
 */
package com.lineaje.assessment.utility;

import java.io.File;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lineaje.assessment.exception.LineajeException;
import com.lineaje.assessment.model.LineajeMeta;

/**
 * @author HegdeNagaraj
 *
 */
public class JsonReader {
	
	Logger logger = Logger.getLogger(JsonReader.class.getName());
	
	public LineajeMeta readJsonFile(String fileName) throws Exception {
		LineajeMeta lineaje = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			File file = new File(fileName);
			lineaje = mapper.readValue(file, LineajeMeta.class);
		} catch(Exception e) {
			logger.warning("Error while reading the json file: "+ e.getCause());
			throw new LineajeException(e);
		}
		
		return lineaje;
	}
	
	

}
