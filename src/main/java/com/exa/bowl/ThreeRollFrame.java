package com.exa.bowl;

public class ThreeRollFrame extends TwoRollFrame {

	/**
	 * Returns true if it is a strike or no:of rolls is 2.
	 * @return
	 */
	@Override
	protected boolean isComplete() {
		if(isStrike() || isSpare()) {
			return rolls.size() == 3;
		}
		return rolls.size() == 2;
	}

	@Override
	public int score() {
		int sum = 0;

		// if we have already calculated the score for this frame.
		if(this.score != INCOMPLETE_FRAME) {
			return this.score;
		}
		
		// get the score from previous frame to add to it.
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

	@Override
	protected int pinsKnockedInTwoRolls() {
		
		// if at least two rolls are not done.
		if(rolls.size() < 2) {
			return INCOMPLETE_FRAME;
		}
		
		return rolls.get(0) + rolls.get(1);
	}
}
