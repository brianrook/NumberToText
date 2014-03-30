package com.brianrook.numberToText.enums;

/**
 * Stores the position names for the text representation
 * 
 * @author B.Rook
 * 
 */
public enum PositionNames {
	HUNDRED(0, "number.hundred"),

	THOUSAND(1, "number.thousand"),

	MILLION(2, "number.million"),

	BILLION(3, "number.billion");

	private int intValue;
	private String nameKey;

	private PositionNames(final int intValueIn, final String nameKeyIn) {
		this.intValue = intValueIn;
		this.nameKey = nameKeyIn;
	}

	public int getIntValue() {
		return this.intValue;
	}

	public String getNameKey() {
		return nameKey;
	}

	/**
	 * retrieve the key of the position name from the index (chunk id)
	 * 
	 * @param num
	 * @return
	 */
	public static String findWord(final int num) {
		String out = "";
		for (PositionNames thisNumber : values()) {
			if (num == thisNumber.getIntValue()) {
				out = thisNumber.getNameKey();

				break;
			}
		}
		return out;
	}

}
