package com.brianrook.numberToText;

import com.brianrook.numberToText.exception.InvalidNumberException;

/**
 * Inteface to the number processor
 * 
 * @author B.Rook
 * 
 */
public interface NumberProcessorInterface {

	/**
	 * Takes a numeric representation of a number and converts it into text.
	 * 
	 * @param number
	 *            the number to convert
	 * @return a text representation of the number
	 * @throws InvalidNumberException
	 */
	public abstract String numberToText(String number)
			throws InvalidNumberException;

}