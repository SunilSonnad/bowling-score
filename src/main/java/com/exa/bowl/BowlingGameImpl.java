package com.exa.bowl;

import java.util.LinkedList;

public class BowlingGameImpl implements BowlingGame {
	
	//should be a doubly linked list.
	private LinkedList<Frame> frames = new LinkedList<>();

	public BowlingGameImpl() {
		frames.addFirst(new TwoRollFrame());
	}
	
	public void roll(int noOfPins) {
		
		if(frames.getLast().isComplete()) {
			
			Frame current = frames.getLast();
			
			if(frames.size() < 9) {
				frames.add(new TwoRollFrame());
			}
			else {
				frames.add(new ThreeRollFrame());
			}
			
			current.next = frames.getLast();
			frames.getLast().previous = current;
		}
		
		frames.getLast().roll(noOfPins);
	}

	public int score() {

		return frames.getLast().score();
	}

}
