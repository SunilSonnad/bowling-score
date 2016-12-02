package com.exa.bowl;

/**
 * The BowlingGame interface
 * @author sunilsonnad
 *
 */
public interface BowlingGame {

	/**
	 * records the number of pins knocked down.
	 * @param noOfPins
	 */
	public void roll(int noOfPins);
	
	/**
	 * returns the score at a given instance. If a frame is not
	 * complete, then returns the previous valid score.
	 * @return
	 */
	public int score();
	
}
