package com.brianrook.numberToText.translator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.brianrook.numberToText.translator.NumberTranslator;

@ContextConfiguration
(
  {
   "classpath:spring/numberToText.xml"
  }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class NumberTranslatorTest {
	@Autowired 
	private NumberTranslator numTrans;

	@Test
	public void testGetName1(){
		assertEquals("one", numTrans.getName(1));
	}
	@Test
	public void testGetName20(){
		assertEquals("twenty", numTrans.getName(20));
	}
	@Test
	public void testGetName43(){
		
		assertNull(numTrans.getName(43));
	}
}
