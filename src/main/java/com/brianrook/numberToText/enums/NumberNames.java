package com.brianrook.numberToText.enums;

/**
 * Class used to store the names of numbers
 * 
 * @author B.Rook
 * 
 */
public enum NumberNames {

	ZERO(0, "number.zero"),

	ONE(1, "number.one"),

	TWO(2, "number.two"),

	THREE(3, "number.three"),

	FOUR(4, "number.four"),

	FIVE(5, "number.five"),

	SIX(6, "number.six"),

	SEVEN(7, "number.seven"),

	EIGHT(8, "number.eight"),

	NINE(9, "number.nine"),

	TEN(10, "number.ten"),

	ELEVEN(11, "number.eleven"),

	TWELVE(12, "number.twelve"),

	THIRTEEN(13, "number.thirteen"),

	FOURTEEN(14, "number.fourteen"),

	FIFTEEN(15, "number.fifteen"),

	SIXTEEN(16, "number.sixteen"),

	SEVENTEEN(17, "number.seventeen"),

	EIGHTEEN(18, "number.eighteen"),

	NINETEEN(19, "number.nineteen"),

	TWENTY(20, "number.twenty"),

	THIRTY(30, "number.thirty"),

	FOURTY(40, "number.fourty"),

	FIFTY(50, "number.fifty"),

	SIXTY(60, "number.sixty"),

	SEVENTY(70, "number.seventy"),

	EIGHTY(80, "number.eighty"),

	NINETY(90, "number.ninety");

	private int intValue;
	private String nameKey;

	private NumberNames(final int intValueIn, final String nameKeyIn) {
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
	 * retrieve the key for a number.
	 * 
	 * @param num
	 *            the number to retrieve the key for
	 * @return the key of the string from the properties file
	 */
	public static String findWord(final int num) {
		String out = "";
		for (NumberNames thisNumber : values()) {
			if (num == thisNumber.getIntValue()) {
				out = thisNumber.getNameKey();

				break;
			}
		}
		return out;
	}

}
