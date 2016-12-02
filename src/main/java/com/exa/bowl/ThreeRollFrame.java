package com.exa.bowl;

/**
 * This class implements the last frame of the bowling game.
 * If the first one is a strike or the second a spare, then this frame
 * adds one more bonus roll to the frame.
 * @author sunilsonnad
 *
 */
public class ThreeRollFrame extends Frame {

	@Override
	public boolean isComplete() {
		if(isStrike() || isSpare()) {
			return rolls.size() == 3;
		}
		return rolls.size() == 2;
	}
	
	@Override
	protected int pinsKnockedInTwoRolls() {
		
		// if at least two rolls are not done.
		if(rolls.size() < 2) {
			return INCOMPLETE_FRAME;
		}
		
		return rolls.get(0) + rolls.get(1);
	}

	@Override
	public int score() {
		int sum = 0;

		// if we have already calculated the score for this frame.
		if(this.score != INCOMPLETE_FRAME) {
			return this.score;
		}
		
		// get the score from previous frame.
		if(this.previous != null) {
			sum += this.previous.score();
		}
		
		// if frame not complete, use previous score.
		if(!isComplete()) {
			return sum;
		}
		
		// add pins from this frame.
		sum += pinsKnocked();
		
		return this.score = sum;
	}

	/**
	 * For the last frame each roll must have 10 or less pins.
	 * And if the first roll is not 10 then sum of 2 rolls should be 10 or less.
	 */
	@Override
	protected void validatePinsInFrame(int noOfPins) {
		validateNoOfPins(noOfPins);
		if(rolls.size() == 1 && rolls.get(0) < 10) {
			validateNoOfPins(rolls.get(0) + noOfPins);
		}
	}
}
