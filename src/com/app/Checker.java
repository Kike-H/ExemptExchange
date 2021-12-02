package com.app;

import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicReference;

/**
* Checker
*/
public class Checker implements Runnable {
	private final Exchanger<Boolean> exchanger;
	private final AtomicReference<Boolean> flag = new AtomicReference<Boolean>(false); 
	private Box box;
	

	public Checker(Box box, Exchanger<Boolean> exchanger) {
		this.exchanger = exchanger;
		this.box = box;
	}

	public void addBuld(Box box_a) {
		for (int i = 0; i < this.box.getSize(); i++) {
			System.out.println("The checker set the L: "+i);
			box_a.add(i);
		}
	}

	@Override
	public void run() {
		try {
			while (true) {

				System.out.println("The checker gives a full box");
				this.flag.set(exchanger.exchange(this.flag.get()));
				System.out.println("The checker recives a empty box");

				if(flag.get()) {
					addBuld(this.box);
					Thread.sleep(500);
				}
			}
		} catch (InterruptedException e) {e.printStackTrace();}
	}
}
