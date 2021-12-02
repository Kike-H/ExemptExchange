package com.app;

import java.util.concurrent.Exchanger;

/**
* Checker
*/
public class Checker implements Runnable {
	private final Exchanger<Box> exchanger; 
	private Box box;
	private int counter;

	public Checker(Exchanger<Box> exchanger) {
		this.exchanger = exchanger;
		this.box = new Box(true);
		this.counter = 1;
	}

	public void addBuld(Box box_a) {
		box_a.add(counter);
		System.out.println("The checker set the L: "+counter);
		counter++;
	}

	@Override
	public void run() {
		try {
			while (counter <= this.box.getSize()) {

				addBuld(this.box);

				if(this.box.isEmpty()) {
					System.out.println("The checker gives a full box");
					this.box = exchanger.exchange(this.box);
					System.out.println("The checker recives a empty box");
					this.counter = 1;
				}
			}
		} catch (InterruptedException e) {e.printStackTrace();}
	}
}
