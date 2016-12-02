package com.exa.bowl;

public class BowlingGameImpl implements BowlingGame {
	
	//doubly linked list.
	private Frame frame;
	
	//count the frames.
	private int currentFrameNumber = 0;

	public BowlingGameImpl() {
		frame = new TwoRollFrame();
		currentFrameNumber++;
	}
	
	public void roll(int noOfPins) {
		if(frame.isComplete()) {
			createFrame();
		}
		frame.roll(noOfPins);
	}
	
	public int score() {
		return frame.score();
	}

	private void createFrame() {
		Frame current = frame;
		frame = newFrame();
		
		current.next = frame;
		frame.previous = current;
		currentFrameNumber++;
	}

	private boolean isLastFrame() {
		return currentFrameNumber == 9;
	}
	
	private boolean isGameOver() {
		return currentFrameNumber >= 10;
	}
	
	/*
	 * This method returns the appropriate implementation
	 * of the frame.
	 */
	private Frame newFrame() {
		if(isGameOver()) {
			throw new RuntimeException("GameOver!");
		}
		
		if(isLastFrame()) {
			return new ThreeRollFrame();
		}
		else {
			return new TwoRollFrame();
		}
	}
	
}
