package com.exa.bowl;

import static org.junit.Assert.*;

import org.junit.Test;

public class TwoRollFrameTest {

	@Test
	public void testIsComplete() {
		Frame fr = new TwoRollFrame();
		fr.roll(4);
		assertTrue(!fr.isComplete());
		
		fr.roll(3);
		assertTrue(fr.isComplete());
	}
	
	@Test
	public void testStrike() {
		
		// if you have a strike.
		Frame fr = new TwoRollFrame();
		fr.roll(10);
		assertTrue(fr.isComplete());
	}
	
	@Test
	public void testSpare() {
		
		// if you have a spare.
		Frame fr = new TwoRollFrame();
		fr.roll(5);
		assertTrue(!fr.isComplete());
		fr.roll(5);
		assertTrue(fr.isComplete());
	}

}
