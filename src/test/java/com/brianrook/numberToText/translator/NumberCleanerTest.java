package com.brianrook.numberToText.translator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.brianrook.numberToText.translator.NumberCleaner;

public class NumberCleanerTest {

	@Test
	public void testCleanNumber(){
		assertEquals("1000", NumberCleaner.stripNumber("1,000"));
	}
	@Test
	public void testCleanNumber1(){
		assertEquals("1000000", NumberCleaner.stripNumber("1,000,000"));
	}
	@Test
	public void testCleanNumber2(){
		assertEquals("1000000", NumberCleaner.stripNumber("-1,000,000"));
	}
	@Test
	public void testCleanNumber3(){
		assertEquals("1000000", NumberCleaner.stripNumber("+1,000,000"));
	}
	@Test
	public void testCleanNumber4(){
		assertEquals("1000000.000", NumberCleaner.stripNumber("1,000,000.000"));
	}
}
