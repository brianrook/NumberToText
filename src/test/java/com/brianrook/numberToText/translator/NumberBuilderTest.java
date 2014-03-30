package com.brianrook.numberToText.translator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.brianrook.numberToText.exception.InvalidNumberException;
import com.brianrook.numberToText.translator.NumberBuilder;

@ContextConfiguration({ "classpath:spring/numberToText.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class NumberBuilderTest {
	@Autowired
	NumberBuilder numBuild;

	@Test
	public void testHundreds() {
		String num = "100";
		String text = numBuild.processHundred(num);
		assertEquals("one hundred", text);
	}

	@Test
	public void testHundreds1() {
		String num = "200";
		String text = numBuild.processHundred(num);
		assertEquals("two hundred", text);
	}

	@Test
	public void testHundreds2() {
		String num = "550";
		String text = numBuild.processHundred(num);
		assertEquals("five hundred", text);
	}

	@Test
	public void testHundreds3() {
		String num = "909";
		String text = numBuild.processHundred(num);
		assertEquals("nine hundred", text);
	}

	@Test
	public void testHundreds4() {
		String num = "009";
		String text = numBuild.processHundred(num);
		assertEquals("", text);
	}

	@Test
	public void testTens() {
		String num = "105";
		String text = numBuild.processTens(num);
		assertEquals("five", text);
	}

	@Test
	public void testTens1() {
		String num = "110";
		String text = numBuild.processTens(num);
		assertEquals("ten", text);
	}

	@Test
	public void testTens2() {
		String num = "115";
		String text = numBuild.processTens(num);
		assertEquals("fifteen", text);
	}

	@Test
	public void testTens3() {
		String num = "123";
		String text = numBuild.processTens(num);
		assertEquals("twenty-three", text);
	}

	@Test
	public void testTens4() {
		String num = "160";
		String text = numBuild.processTens(num);
		assertEquals("sixty", text);
	}

	@Test
	public void testTens5() {
		String num = "184";
		String text = numBuild.processTens(num);
		assertEquals("eighty-four", text);
	}

	@Test
	public void testProcessChunk() {
		String num = "105";
		String text = numBuild.processChunk(num);
		assertEquals("one hundred five", text);
	}

	@Test
	public void testProcessChunk1() {
		String num = "110";
		String text = numBuild.processChunk(num);
		assertEquals("one hundred ten", text);
	}

	@Test
	public void testProcessChunk2() {
		String num = "015";
		String text = numBuild.processChunk(num);
		assertEquals("fifteen", text);
	}

	@Test
	public void testProcessChunk3() {
		String num = "023";
		String text = numBuild.processChunk(num);
		assertEquals("twenty-three", text);
	}

	@Test
	public void testProcessChunk4() {
		String num = "660";
		String text = numBuild.processChunk(num);
		assertEquals("six hundred sixty", text);
	}

	@Test
	public void testProcessChunk5() {
		String num = "384";
		String text = numBuild.processChunk(num);
		assertEquals("three hundred eighty-four", text);
	}

	@Test
	public void testProcessNumber() throws InvalidNumberException {
		String num = "1105";
		String text = numBuild.processNumber(num);
		assertEquals("one thousand one hundred five", text);
	}

	@Test
	public void testProcessNumber1() throws InvalidNumberException {
		String num = "23110";
		String text = numBuild.processNumber(num);
		assertEquals("twenty-three thousand one hundred ten", text);
	}

	@Test
	public void testProcessNumber2() throws InvalidNumberException {
		String num = "513015";
		String text = numBuild.processNumber(num);
		assertEquals("five hundred thirteen thousand fifteen", text);
	}

	@Test
	public void testProcessNumber3() throws InvalidNumberException {
		String num = "1012023";
		String text = numBuild.processNumber(num);
		assertEquals("one million twelve thousand twenty-three", text);
	}

	@Test
	public void testProcessNumber4() throws InvalidNumberException {
		String num = "10001660";
		String text = numBuild.processNumber(num);
		assertEquals("ten million one thousand six hundred sixty", text);
	}

	@Test
	public void testProcessNumber5() throws InvalidNumberException {
		String num = "1115909384";
		String text = numBuild.processNumber(num);
		assertEquals(
				"one billion one hundred fifteen million nine hundred nine thousand three hundred eighty-four",
				text);
	}

	@Test
	public void testProcessNumber6() throws InvalidNumberException {
		String num = "1000015";
		String text = numBuild.processNumber(num);
		assertEquals("one million fifteen", text);
	}
	@Test(expected = InvalidNumberException.class)
	public void testProcessNumber7() throws InvalidNumberException {
		String num = "123123123123123123";
		String text = numBuild.processNumber(num);
		fail("expected exception");
	}

	@Test
	public void testProcessDecimal() {
		String num = ".04";
		String text = numBuild.processDecimal(num);
		assertEquals("04/100", text);
	}

	@Test
	public void testProcessDecimal1() {
		String num = ".4";
		String text = numBuild.processDecimal(num);
		assertEquals("40/100", text);
	}

	@Test
	public void testProcessDecimal2() {
		String num = ".104";
		String text = numBuild.processDecimal(num);
		assertEquals("10/100", text);
	}
}
