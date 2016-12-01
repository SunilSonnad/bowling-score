package com.exa.bowl;

public interface BowlingGame {

	/**
	 * records the number of pins knocked down.
	 * @param noOfPins
	 */
	public void roll(int noOfPins);
	
	/**
	 * returns the score at a given instance.
	 * @return
	 */
	public int score();
	
}
