package com.brianrook.numberToText;

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
		NumberProcessor proc = (NumberProcessor) appContext
				.getBean("numberProcessor");

		String output;
		try {
			output = proc.numberToText(args[0]);
			System.out.print(output);
		} catch (InvalidNumberException e) {
			System.out.println("Invalid number.  Cannot process this input");
		}

	}

}
