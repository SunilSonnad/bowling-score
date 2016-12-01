package com.exa.bowl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BowlingGameTest {

	private static final int INCOMPLETE = -1;
	BowlingGame game;
			
	@Before
	public void setup() {
		game = new BowlingGameImpl();
	}
	
	@Test
	public void testSimpleFirstFrame() {
		game.roll(3);
		game.roll(4);
		
		assertEquals(7, game.score());
	}
	
	@Test
	public void testStrikeIncomplete() {
		game.roll(10);
		assertEquals(INCOMPLETE, game.score());
	}

	@Test
	public void testSpareIncomplete() {
		game.roll(2);
		game.roll(8);
		assertEquals(INCOMPLETE, game.score());
	}
	
	@Test
	public void testSpareComplete() {
		game.roll(2);
		game.roll(8);
		game.roll(5);
		game.roll(4);
		assertEquals(24, game.score());
	}
	
	@Test
	public void testStrikeComplete() {
		game.roll(10);
		game.roll(8);
		game.roll(1);
		assertEquals(28, game.score());
	}
	
	@Test
	public void testAllStrike() {
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		assertEquals(300, game.score());
	}
}
