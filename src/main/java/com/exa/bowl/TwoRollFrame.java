package com.exa.bowl;

/**
 * This class implements the regular frame of the bowling game.
 * Each frame consists of two rolls, unless the first one is a strike,
 * in which case it is one roll.
 * @author sunilsonnad
 *
 */
public class TwoRollFrame extends Frame {

	@Override
	public boolean isComplete() {
		return rolls.size() == 2 || isStrike();
	}
	
	@Override
	protected int pinsKnockedInTwoRolls() {
		if(!isComplete()) {
			return INCOMPLETE_FRAME;
		}
		
		if(!isStrike()) {
			return pinsKnocked();
		}
		
		// if it is a strike, then get next(2nd) roll from next frame.
		if(hasNext()) {
			return pinsKnocked() + this.next.pinsKnockedInFirstRoll();
		}
		else {
			// no next, cannot yet calculate.
			return INCOMPLETE_FRAME;
		}
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

		// if frame not complete, return previous score.
		if(!isComplete()) {
			return sum;
		}
		
		if((isStrike() || isSpare()) && !hasNext()) {
			return sum;
		}
		
		// if this frame is a strike
		if(isStrike()) {
			int extra = this.next.pinsKnockedInTwoRolls();
			if(extra == INCOMPLETE_FRAME) {
				return sum;
			}
			sum += extra;
		}
		// if this frame is a spare
		else if(isSpare()) {
			sum += this.next.pinsKnockedInFirstRoll();
		}
		
		// add pins from this frame.
		sum += pinsKnocked();
		
		return this.score = sum;
	}

	/**
	 * In a regular frame, the sum of two rolls should not be greater than 10.
	 */
	@Override
	protected void validatePinsInFrame(int noOfPins) {
		validateNoOfPins(this.pinsKnocked() + noOfPins);
	}
}
