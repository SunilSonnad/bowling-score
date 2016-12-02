package com.exa.bowl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ThreeRollFrameTest {

	ThreeRollFrame frame;
	
	@Before
	public void setup() {
		frame = new ThreeRollFrame();
	}
	
	@Test
	public void testIsComplete() {
		frame.roll(4);
		assertTrue(!frame.isComplete());
		
		frame.roll(3);
		// frame complete after 2 rolls adding less than 10
		assertTrue(frame.isComplete());
	}
	
	@Test
	public void testStrike() {
		
		// if you have a strike you need all 3 rolls.
		frame.roll(10);
		assertTrue(!frame.isComplete());
		frame.roll(10);
		assertTrue(!frame.isComplete());
		frame.roll(10);
		assertTrue(frame.isComplete());
	}
	
	@Test
	public void testSpare() {
		
		// if you have a spare you need all 3 rolls.
		frame.roll(5);
		assertTrue(!frame.isComplete());
		frame.roll(5);
		assertTrue(!frame.isComplete());
		frame.roll(10);
		assertTrue(frame.isComplete());
	}
	
	@Test(expected=RuntimeException.class)
	public void testMoreThan10PinsInStrike() {
		frame.roll(15);
	}
	
	@Test(expected=RuntimeException.class)
	public void testMoreThan10Pins() {
		frame.roll(5);
		frame.roll(8);
	}
	
	@Test(expected=RuntimeException.class)
	public void testNegativePins() {
		frame.roll(-7);
	}

}
