package com.exa.bowl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TwoRollFrameTest {

	TwoRollFrame frame;
	
	@Before
	public void setup() {
		frame = new TwoRollFrame();
	}
	
	@Test
	public void testIsComplete() {
		frame.roll(4);
		assertTrue(!frame.isComplete());
		
		frame.roll(3);
		assertTrue(frame.isComplete());
	}
	
	@Test
	public void testStrike() {
		
		// if you have a strike.
		frame.roll(10);
		assertTrue(frame.isComplete());
	}
	
	@Test
	public void testSpare() {
		
		// if you have a spare.
		frame.roll(5);
		assertTrue(!frame.isComplete());
		frame.roll(5);
		assertTrue(frame.isComplete());
	}

	@Test(expected=RuntimeException.class)
	public void testMoreThan10PinsInStrike() {
		frame.roll(19);
	}
	
	@Test(expected=RuntimeException.class)
	public void testMoreThan10Pins() {
		frame.roll(5);
		frame.roll(9);
	}
	
	@Test(expected=RuntimeException.class)
	public void testNegativePins() {
		frame.roll(-9);
	}
}
