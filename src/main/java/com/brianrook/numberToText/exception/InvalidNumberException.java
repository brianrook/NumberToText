package com.brianrook.numberToText.exception;

/**
 * Exception used to identify when a number cannot be processed.
 * 
 * @author B.Rook
 * 
 */
public class InvalidNumberException extends Exception {
	public InvalidNumberException() {
		super();
	}

	public InvalidNumberException(String string) {
		super(string);
	}

}
