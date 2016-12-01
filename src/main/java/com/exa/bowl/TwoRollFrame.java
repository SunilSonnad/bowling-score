package com.exa.bowl;

public class TwoRollFrame extends Frame {

	/**
	 * Returns true if it is a strike
	 * @return
	 */
	protected boolean isStrike() {
		if(rolls.size() < 1) {
			return false;
		}
		return rolls.get(0) == MAX_PINS;
	}

	/**
	 * Returns true if it is a spare.
	 * @return
	 */
	protected boolean isSpare() {
		return rolls.size() == 2 && pinsKnocked() == MAX_PINS;
	}

	/**
	 * Returns true if it is a strike or no:of rolls is 2.
	 * @return
	 */
	protected boolean isComplete() {
		return rolls.size() == 2 || isStrike();
	}

	@Override
	public int score() {
		int sum = 0;

		// if we have already calculated the score for this frame.
		if(this.score != INCOMPLETE) {
			return this.score;
		}

		// if frame not complete, score cannot be calculated.
		if(!isComplete()) {
			return INCOMPLETE;
		}
		
		if((isStrike() || isSpare()) && !hasNext()) {
			return INCOMPLETE;
		}
		
		// if this frame is a strike
		if(isStrike()) {
			sum = this.next.pinsKnockedInTwoRolls();
		}
		// if this frame is a spare
		else if(isSpare()) {
			sum = this.next.pinsKnockedInFirstRoll();
		}
		
		// cannot calculate yet.
		if(sum == INCOMPLETE) {
			return INCOMPLETE;
		}
		
		// get the score from previous frame to add to it.
		if(this.previous != null) {
			sum += this.previous.score();
		}
		
		// add pins from this frame.
		sum += pinsKnocked();
		
		return this.score = sum;
	}

	@Override
	protected int pinsKnockedInTwoRolls() {
		
		if(!isComplete()) {
			return INCOMPLETE;
		}
		
		if(!isStrike()) {
			return pinsKnocked();
		}
		
		// if it is a strike.
		if(hasNext()) {
			return pinsKnocked() + this.next.pinsKnockedInFirstRoll();
		}
		else {
			// no next, cannot yet calculate.
			return INCOMPLETE;
		}
		
	}

}
