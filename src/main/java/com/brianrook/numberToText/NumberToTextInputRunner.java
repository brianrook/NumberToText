package com.brianrook.numberToText;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.brianrook.numberToText.exception.InvalidNumberException;

public class NumberToTextInputRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"spring/numberToText.xml");
		NumberProcessor proc = (NumberProcessor) appContext
				.getBean("numberProcessor");

		String output;
		String input = null;
		while (true) {

			try {
				Scanner reader = new Scanner(System.in);
				System.out.println("Enter the number");
				input = reader.next();
				if ("end".equals(input)) {
					break;
				}
				output = proc.numberToText(input);
				System.out.print(output + "\n");
			} catch (InvalidNumberException e) {
				if (null != e.getMessage()) {
					System.out.println("Exception: " + e.getMessage());
				}
				System.out
						.println("Invalid number.  Cannot process this input.  Please enter a number (i.e. 32,000.04, 200, 123456.999)\n");
			}
		}
		System.out.println("done");
	}

}
