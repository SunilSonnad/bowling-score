package com.exa.bowl;

public class ThreeRollFrame extends Frame {

	protected boolean isComplete() {
		if(isStrike() || isSpare()) {
			return rolls.size() == 3;
		}
		return rolls.size() == 2;
	}
	
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
}
