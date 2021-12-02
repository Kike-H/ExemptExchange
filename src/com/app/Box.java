package com.app;

import java.util.Stack;

/**
* Box
*/
public class Box {
	private final Stack<Integer> stack = new Stack<Integer>();
	private final static int MAX_SIZE = 10;

	public Box() {}

	public  void add(int bulb) {
		if(!isFull()) {
			stack.push(bulb);
		}
	}

	public  Integer remove() {
		if(!isEmpty()) {
			return stack.pop();
		} 
		return null;
	}

	public  boolean isFull() {
		return (stack.size() == MAX_SIZE) ? true : false;
	}

	public  boolean isEmpty() {
		return stack.isEmpty();
	}

	public  Integer getSize() {
		return this.MAX_SIZE;
	}
}
