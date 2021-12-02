package com.app;

import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicReference;


/**
* Packer
*/
public class Packer extends Thread {
	private final Exchanger<Boolean> exchanger;
	private final AtomicReference<Boolean> flag = new AtomicReference<Boolean>(true); 
	private boolean alive;
	private Box box;
	private int counter_box;

	public Packer(Box box, Exchanger<Boolean> exchanger) {
		this.exchanger = exchanger;
		this.box = box;
		this.alive = true;
		this.counter_box = 0;
	}

	public void removeBulb(Box box_a) {
		while (!box_a.isEmpty()) {
			System.out.println("The packer pack the 💡 "+box_a.remove());
		}
		this.counter_box++;
		System.out.printf("Complete num %d box 📦", this.counter_box);
		System.out.println();
	}

	public void stopPacker() {
		this.alive = false;
	}

	@Override
	public void run() {
		try {
			while (this.alive) {
				this.flag.set(this.exchanger.exchange(this.flag.get()));
				if(this.flag.get()) {
					System.out.println("Exchange the box 📦");
					removeBulb(this.box);
					Thread.sleep(1000);
				}
			}
		} catch ( InterruptedException e) {e.printStackTrace();}
	}
}
