package com.brianrook.translator.numberToText.translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.brianrook.enums.NumberNames;
import com.brianrook.enums.PositionNames;

@Component
@PropertySource("nameKey.properties")
public class NumberTranslator {
	@Autowired
	private Environment environment;

	public String getNameFromKey(String key) {
		return environment.getProperty(key);
	}

	public String getName(int number) {
		return getNameFromKey(NumberNames.findWord(number));
	}
	public String getPositionName(int number) {
		return getNameFromKey(PositionNames.findWord(number));
	}
}
