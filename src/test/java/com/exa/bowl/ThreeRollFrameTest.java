package com.exa.bowl;

import static org.junit.Assert.*;

import org.junit.Test;

public class ThreeRollFrameTest {

	@Test
	public void testIsComplete() {
		Frame fr = new ThreeRollFrame();
		fr.roll(4);
		assertTrue(!fr.isComplete());
		
		fr.roll(3);
		// frame complete after 2 rolls adding less than 10
		assertTrue(fr.isComplete());
	}
	
	@Test
	public void testStrike() {
		
		// if you have a strike you need all 3 rolls.
		Frame fr = new ThreeRollFrame();
		fr.roll(10);
		assertTrue(!fr.isComplete());
		fr.roll(10);
		assertTrue(!fr.isComplete());
		fr.roll(10);
		assertTrue(fr.isComplete());
	}
	
	@Test
	public void testSpare() {
		
		// if you have a spare you need all 3 rolls.
		Frame fr = new ThreeRollFrame();
		fr.roll(5);
		assertTrue(!fr.isComplete());
		fr.roll(5);
		assertTrue(!fr.isComplete());
		fr.roll(10);
		assertTrue(fr.isComplete());
	}

}
