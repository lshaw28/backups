package com.spd.cq.searspartsdirect.common.helpers;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dmartinez
 * Sears Parts Direct server side validation class
 */

public class PartsDirectValidator {
	
	protected static Logger log = LoggerFactory.getLogger(PartsDirectValidator.class);
	
	/*
	 * Verify if String parameter is a number
	 */
	public boolean isAllNumeric(String str){
		return str.matches("[+-]?\\d*(\\.\\d+)?");
	}
	
	/*
	 * Verify if String parameter is an alphanumeric value
	 */
	public boolean isAlphanumeric(String str){
		return str.matches("^[a-zA-Z0-9_]*$");

	}
	
	/*
	 * Verify if length of String parameter is shorter than indicated integer value
	 */
	public boolean isNoLongerThan(String str, int limit){
		return (str.length() <= limit);
	}

	/*
	 * Verify if String parameter contains no SQL words, ignoring case (based on ANSI SQL 2003 SQL reserved words)
	 */
	public boolean containsNoSqlWords(String str){
		Set<String> reservedWords = SqlReservedWord.getWords();
		String[] wordsPerWhitespace = str.split("[^A-Za-z_]+");
		for (int i = 0 ; i < wordsPerWhitespace.length; i++) {
			if (reservedWords.contains(wordsPerWhitespace[i].toUpperCase())) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Verify if initial String parameter does not contain various other String values within it, ignores case
	 */
	public boolean containsNoneOf(String initial, String ... strings){
		for(String s: strings){
			if(initial.toLowerCase().contains(s.toLowerCase())){
				return false;
			}
		}
		return true;
	}
	
	// add more? ...
	
}
