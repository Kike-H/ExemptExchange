package com.app;

import java.util.concurrent.Exchanger;

/**
* Checker
*/
public class Checker extends Thread {
	private final Exchanger<Box> exchanger;
	private Box box;
	private int counter;

	public Checker(Box box, Exchanger<Box> exchanger) {
		this.box = box;
		this.exchanger = exchanger;
		this.counter = 1;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Box aux = box;
				Thread.sleep(1000);
				aux.add(counter);
				System.out.println("The checker set the L: "+counter);
				counter++;
				if(box.isFull()) {
					System.out.println("The checker gives a full box");
					box = exchanger.exchange(aux);
					System.out.println("The checker recives a empty box");
					counter = 1;
				}
			}
		} catch (InterruptedException e) {e.printStackTrace();}
	}
}
