package com.brianrook.numberToText;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.brianrook.numberToText.exception.InvalidNumberException;
import com.brianrook.translator.numberToText.translator.NumberBuilder;
import com.brianrook.translator.numberToText.translator.NumberCleaner;

@Component
@PropertySource("nameKey.properties")
public class NumberProcessor {
	private static final String CURRENCY_KEY = "currency";
	@Autowired
	NumberBuilder numBuilder;
	@Autowired
	private Environment environment;

	public String getCurrency() {
		return environment.getProperty(CURRENCY_KEY);
	}

	public String numberToText(String number) throws InvalidNumberException {
		String returnText = null;
		// clean input text
		String strippedNumber = NumberCleaner.stripNumber(number);
		// verify number
		String leftOfDecimal = StringUtils.substringBefore(strippedNumber, ".");
		String rightOfDecimal = null;
		if (strippedNumber.contains(".")) {
			if (StringUtils.countMatches(strippedNumber, ".") > 1) {
				throw new InvalidNumberException();
			}
			int decimal = strippedNumber.indexOf(".");
			rightOfDecimal = strippedNumber.substring(decimal);
		}
		if (NumberUtils.isDigits(leftOfDecimal)) {
			// convert number to text
			StringBuilder numText = new StringBuilder();
			numText.append(numBuilder.processNumber(leftOfDecimal));
			
			if (NumberUtils.isNumber(rightOfDecimal)) {
				numText.append(StringUtils.SPACE + "and" + StringUtils.SPACE);
				numText.append(numBuilder.processDecimal(rightOfDecimal));

			}

			numText.append(StringUtils.SPACE);
			numText.append(getCurrency());

			String capFix = StringUtils.capitalize(numText.toString());
			return capFix;

		} else {
			// TODO throw exception?
			throw new InvalidNumberException();
		}

	}
}
