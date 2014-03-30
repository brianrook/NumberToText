package com.brianrook.numberToText;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.brianrook.numberToText.exception.InvalidNumberException;
import com.brianrook.numberToText.translator.NumberBuilder;

@ContextConfiguration({ "classpath:spring/numberToText.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class NumberProcessorTest {
	@Autowired
	NumberProcessorInterface numProc;

	@Test(expected = InvalidNumberException.class)
	public void testNumberProcessor() throws InvalidNumberException {
		String num = "abcdef123";
		String returnText = numProc.numberToText(num);
		Assert.fail("expect exception");

	}

	@Test
	public void testNumberProcessor1() throws InvalidNumberException {
		String num = "0";
		String returnText = numProc.numberToText(num);
		assertEquals("Zero dollars", returnText);

	}

	@Test
	public void testNumberProcessor2() throws InvalidNumberException {
		String num = ",123";
		String returnText = numProc.numberToText(num);
		assertEquals("One hundred twenty-three dollars", returnText);

	}

	@Test
	public void testNumberProcessor3() throws InvalidNumberException {
		String num = "3,123";
		String returnText = numProc.numberToText(num);
		assertEquals("Three thousand one hundred twenty-three dollars", returnText);

	}

	@Test
	public void testNumberProcessor4() throws InvalidNumberException {
		String num = "3,1,23";
		String returnText = numProc.numberToText(num);
		assertEquals("Three thousand one hundred twenty-three dollars", returnText);

	}

	@Test
	public void testNumberProcessor5() throws InvalidNumberException {
		String num = "2523.04";
		String returnText = numProc.numberToText(num);
		assertEquals(
				"Two thousand five hundred twenty-three and 04/100 dollars",
				returnText);

	}
	
	@Test
	public void testProcessNumber6() throws InvalidNumberException {
		String num = "1000015";
		String text = numProc.numberToText(num);
		assertEquals("One million fifteen dollars", text);
	}
}
