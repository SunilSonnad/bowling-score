package com.exa.bowl;

import java.util.ArrayList;
import java.util.List;

/**
 * The abstract class Frame has the generic methods of the frame.
 * @author sunilsonnad
 *
 */
public abstract class Frame {

	// the previous and the next frames.
	protected Frame next = null;
	protected Frame previous = null;

	protected static final int MAX_PINS = 10;
	protected static final int INCOMPLETE_FRAME = -1;

	// Max 3 rolls per frame.
	List<Integer> rolls = new ArrayList<>(3);
	protected int score = INCOMPLETE_FRAME;

	/**
	 * Returns true if the next frame has started.
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
	 * Returns true if it is a strike or all rolls are done.
	 * @return
	 */
	public abstract boolean isComplete();

	/**
	 * Returns true if it is a spare.
	 * @return
	 */
	protected boolean isSpare() {
		return rolls.size() >= 2 && (rolls.get(0) + rolls.get(1)) == MAX_PINS;
	}
	
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
	 * Returns sum of pins knocked in this frame
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
	 * Returns the pins knocked in the next two rolls.
	 * If this frame has a strike then get score from first
	 * roll of next frame as well.
	 * Returns -1 if the frames are not complete.
	 * @return
	 */
	protected abstract int pinsKnockedInTwoRolls();

	/**
	 * pins knocked down in this roll
	 * @param noOfPins
	 */
	public void roll(int noOfPins) {
		rolls.add(noOfPins);
	}

	/**
	 * Returns the score for this frame.
	 * If strike then includes sum of pins knocked in next two rolls.
	 * If spare then includes sum of pins knocked in next roll.
	 * Else just sum of pins knocked in this frame.
	 */
	public abstract int score();

}
