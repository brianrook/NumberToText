package com.brianrook.numberToText.translator;

import org.springframework.util.StringUtils;

public class NumberCleaner {

	public static String stripNumber(String input){
		return StringUtils.deleteAny(input, ",-+");
	}
}
