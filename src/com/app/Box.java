package com.app;

import java.util.Stack;

/**
* Box
*/
public class Box {
	private final Stack<Integer> stack = new Stack<Integer>();
	private final int size;

	public Box(int size) {
		this.size = size;
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
		return stack.size() == size ? true : false;
	}

	public synchronized boolean isEmpty() {
		return stack.isEmpty();
	}

	public synchronized Integer getSize() {
		return this.size;
	}
}
