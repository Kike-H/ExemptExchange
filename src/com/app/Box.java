package com.app;

import java.util.Stack;

/**
* Box
*/
public class Box {
	private final Stack<Integer> stack = new Stack<Integer>();
	private final static int MAX_SIZE = 3;

	public Box(boolean isFill) {
		if(isFill) {
			for (int i = 0; i < MAX_SIZE; i++) {
				add(i);
			}
		}
	}

	public synchronized void add(int bulb) {
		if(!isFull()) {
			stack.push(bulb);
		}
	}

	public synchronized Integer remove() {
		if(!isEmpty()) {
			return stack.pop();
		} 
		return null;
	}

	public synchronized boolean isFull() {
		return stack.size() == MAX_SIZE ? true : false;
	}

	public synchronized boolean isEmpty() {
		return stack.isEmpty();
	}

	public synchronized Integer getSize() {
		return this.MAX_SIZE;
	}
}
