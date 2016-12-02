package com.exa.bowl;

public class TwoRollFrame extends Frame {

	protected boolean isComplete() {
		return rolls.size() == 2 || isStrike();
	}
	
	protected int pinsKnockedInTwoRolls() {
		if(!isComplete()) {
			return INCOMPLETE_FRAME;
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
}
