package com.exa.bowl;

import java.util.ArrayList;
import java.util.List;

public abstract class Frame {

	Frame next = null;
	Frame previous = null;

	protected static final int MAX_PINS = 10;
	protected static final int INCOMPLETE = -1;

	// Max 3 rolls per frame.
	List<Integer> rolls = new ArrayList<>(3);
	protected int score = INCOMPLETE;

	/**
	 * returns true if the next frame has started.
	 * @return
	 */
	protected boolean hasNext() {
		// check if there is a next frame.
		if(this.next == null) {
			return false;
		}
		return true;
	}

	/**
	 * Returns true if it is a strike
	 * @return
	 */
	protected abstract boolean isStrike();

	/**
	 * Returns true if it is a spare.
	 * @return
	 */
	protected abstract boolean isSpare();

	/**
	 * Returns true if it is a strike or all rolls are done.
	 * @return
	 */
	protected abstract boolean isComplete();

	/**
	 * sum of pins knocked in this frame
	 */
	protected int pinsKnocked() {
		int sum = 0;
		for(Integer r : rolls) {
			sum += r;
		}
		return sum;
	}

	/**
	 * Returns pins knocked in the first roll.
	 * @return
	 */
	protected int pinsKnockedInFirstRoll() {
		return rolls.get(0);
	}

	/**
	 * pins knocked down in this roll
	 * @param noOfPins
	 */
	public void roll(int noOfPins) {
		rolls.add(noOfPins);
	}

	/**
	 * Returns the score for this frame
	 * If strike then includes sum of pins knocked in next two rolls.
	 * If spare then includes sum of pins knocked in next roll.
	 * else just sum of pins knocked in this frame.
	 */
	public abstract int score();

	/**
	 * Returns the pins knocked in the next two rolls.
	 * If this frame has a strike then get scroe from first
	 * roll of next frame as well.
	 * @return
	 */
	protected abstract int pinsKnockedInTwoRolls();

}
