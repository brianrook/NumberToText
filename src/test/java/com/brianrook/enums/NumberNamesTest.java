package com.brianrook.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration
(
  {
   "classpath:spring/numberToText.xml"
  }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class NumberNamesTest {

	@Test
	public void testRetrieveName(){
		String one = NumberNames.findWord(1);
		assertEquals("number.one", one);
	}
}
