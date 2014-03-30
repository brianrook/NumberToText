package com.brianrook.numberToText.translator;

import org.springframework.util.StringUtils;

/**
 * Strips out any characters that will prevent processing
 * 
 * @author B.Rook
 * 
 */
public class NumberCleaner {

	/**
	 * Removes any symbols from the string for processing.
	 * 
	 * @param input
	 *            the string to clean
	 * @return the cleaned string
	 */
	public static String stripNumber(String input) {
		return StringUtils.deleteAny(input, ",-+");
	}
}
