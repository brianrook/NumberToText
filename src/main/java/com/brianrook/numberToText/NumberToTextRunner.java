package com.brianrook.numberToText;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.brianrook.numberToText.exception.InvalidNumberException;

public class NumberToTextRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"spring/numberToText.xml");
		NumberProcessorInterface proc = (NumberProcessorInterface) appContext
				.getBean("numberProcessor");

		String output;
		try {
			// make sure that they passed in something
			if ((args.length != 1) || (StringUtils.isEmpty(args[0]))) {
				throw new InvalidNumberException();
			}
			output = proc.numberToText(args[0]);
			System.out.print(output);
		} catch (InvalidNumberException e) {
			System.out.println("Invalid number.  Cannot process this input.  Please enter a number (i.e. 32,000.04, 200, 123456.999)");
		}

	}

}
