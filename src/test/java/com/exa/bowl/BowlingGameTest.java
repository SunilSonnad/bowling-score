package com.exa.bowl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Use the tool as http://www.bowlinggenius.com/
 * to calculate the score.
 * 
 * @author sunilsonnad
 *
 */
public class BowlingGameTest {

	BowlingGame game;
			
	@Before
	public void setup() {
		game = new BowlingGameImpl();
	}
	
	@Test
	public void testSimpleFirstFrame() {
		game.roll(3);//1 frame
		game.roll(4);
		
		assertEquals(7, game.score());
	}
	
	/* After a strike, wait for 2 more rolls to get the score.
	 * else get the previous valid score.
	 */
	@Test
	public void testStrikeIncomplete() {
		game.roll(10);
		// cannot be calculated yet
		assertEquals(0, game.score());
	}

	/*
	 *  After a spare, wait for 1 more roll to get the score.
	 *  else get the previous valid score.
	 */
	@Test
	public void testSpareIncomplete() {
		game.roll(2);
		game.roll(8);
		// cannot be calculated yet
		assertEquals(0, game.score());
	}
	
	@Test
	public void testSpareComplete() {
		game.roll(2);//1
		game.roll(8);
		
		game.roll(5);//2
		//score for first frame
		assertEquals(15, game.score());
		
		game.roll(4);
		// score after two frames.
		assertEquals(24, game.score());
	}
	
	@Test
	public void testStrikeComplete() {
		game.roll(10);//1
		
		game.roll(8);//2
		
		// after strike + 1 roll
		assertEquals(0, game.score());
		
		game.roll(1);
		// after strike + 2 rolls
		assertEquals(28, game.score());
	}
	
	@Test
	public void testCombination() {
		game.roll(10);//1
		game.roll(2);//2
		game.roll(8);
		game.roll(1);//3
		game.roll(2);
		assertEquals(34, game.score());
	}
	
	@Test
	public void testCombinationIncompleteFrame() {
		game.roll(10);//1
		
		game.roll(2);//2
		game.roll(8);
		
		game.roll(4);//3
		game.roll(5);
		
		game.roll(6);//4
		game.roll(3);
		
		game.roll(4);//5
		game.roll(6);
		
		game.roll(7);//6 - incomplete
		
		assertEquals(69, game.score());
	}
	
	@Test
	public void testCombinationWithLastFrame() {
		game.roll(10); //1
		
		game.roll(10); //2
		assertEquals(0, game.score());
		
		game.roll(2); // 3
		assertEquals(22, game.score());
		game.roll(8);
		assertEquals(42, game.score());
		
		game.roll(7); // 4
		game.roll(3);
		//get last computed total.
		assertEquals(59, game.score());
		
		game.roll(4); // 5
		game.roll(6);
		
		game.roll(5); // 6
		game.roll(5);
		
		game.roll(10); // 7
		assertEquals(108, game.score());
		
		game.roll(10); // 8
		
		game.roll(10); // 9
		
		game.roll(1); // 10
		game.roll(9);
		assertEquals(179, game.score());
		game.roll(8);
		assertEquals(197, game.score());
		
	}
	
	/*
	 * All 10 frames are gutter.
	 */
	@Test
	public void testAllZero() {
		game.roll(0);
		game.roll(0);
		game.roll(0);
		game.roll(0);
		game.roll(0);
		game.roll(0);
		game.roll(0);
		game.roll(0);
		game.roll(0);
		game.roll(0);
		game.roll(0);
		game.roll(0);
		game.roll(0);
		game.roll(0);
		game.roll(0);
		game.roll(0);
		game.roll(0);
		game.roll(0);
		game.roll(0);
		game.roll(0);
		assertEquals(0, game.score());
	}
	
	/*
	 * All frames are strikes.
	 */
	@Test
	public void testAllStrike() {
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		assertEquals(120, game.score());
		
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		assertEquals(300, game.score());
	}
	
	@Test(expected=RuntimeException.class)
	public void testGameOver() {
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
		game.roll(10);
	}
	
	@Test(expected=RuntimeException.class)
	public void testMoreThan10PinsStrike() {
		game.roll(11);
	}
	
	@Test(expected=RuntimeException.class)
	public void testMoreThan10Pins() {
		game.roll(4);
		game.roll(8);
	}
}
