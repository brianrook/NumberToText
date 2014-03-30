package com.brianrook.numberToText.translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.brianrook.numberToText.enums.NumberNames;
import com.brianrook.numberToText.enums.PositionNames;

/**
 * Uses the key from the enumeration to look up the text representation from the
 * properties file.
 * 
 * @author B.Rook
 * 
 */
@Component
@PropertySource("nameKey.properties")
public class NumberTranslator {
	@Autowired
	private Environment environment;

	public String getNameFromKey(String key) {
		return environment.getProperty(key);
	}

	/**
	 * Get the string of the number from the id.
	 * 
	 * @param number
	 *            the int representation of the number
	 * @return a text representation of the number
	 */
	public String getName(int number) {
		return getNameFromKey(NumberNames.findWord(number));
	}

	/**
	 * get the string of the position from the id (chunk id)
	 * 
	 * @param number
	 *            the chunk index id
	 * @return the string representation of the position
	 */
	public String getPositionName(int number) {
		return getNameFromKey(PositionNames.findWord(number));
	}
}
