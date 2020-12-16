package com.pwc.digipulse.core;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

public class ReaderUtils {
	
	static private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public static JSONObject readJSONFile(Path fileName) {

		JSONObject config = null;
		log.log(Level.INFO, "Reading JSON File : " + fileName.toString());
		try {
	        String contents = new String(Files.readAllBytes(fileName));
            config = new JSONObject(contents);
            System.out.println(config);
		}  catch (Exception e) {
			log.log(Level.SEVERE, "Error occured during readin a json file '" + fileName);
			throw new RuntimeException("Error occured during readin a json file '" + fileName + "'\n" + e.getMessage());
        } 
		
		return config;
	}
}
