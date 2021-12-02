package com.app;

import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicReference;

/**
* Checker
*/
public class Checker extends Thread {
	private final Exchanger<Boolean> exchanger;
	private final AtomicReference<Boolean> flag = new AtomicReference<Boolean>(false);
	private boolean alive; 
	private Box box;
	

	public Checker(Box box, Exchanger<Boolean> exchanger) {
		this.exchanger = exchanger;
		this.box = box;
		this.alive = true;
	}

	public void addBuld(Box box_a) {
		for (int i = 0; i < this.box.getSize(); i++) {
			System.out.println("The checker set the 💡: "+i);
			box_a.add(i);
		}
	}

	public void stopChecker() {
		this.alive = false;
	}

	@Override
	public void run() {
		try {
			while (this.alive) {

				System.out.println("The checker gives a full box 📦🔝");
				this.flag.set(exchanger.exchange(this.flag.get()));
				System.out.println("The checker recives a empty box 📦🔜");

				if(flag.get()) {
					addBuld(this.box);
					Thread.sleep(1000);
				}
			}
		} catch (InterruptedException e) {e.printStackTrace();}
	}
}
