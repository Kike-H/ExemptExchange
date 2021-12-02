package com.app;

import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicReference;


/**
* Packer
*/
public class Packer implements Runnable {
	private final Exchanger<Boolean> exchanger;
	private final AtomicReference<Boolean> flag = new AtomicReference<Boolean>(true); 
	private Box box;

	public Packer(Box box, Exchanger<Boolean> exchanger) {
		this.exchanger = exchanger;
		this.box = box;
	}

	public void removeBulb(Box box_a) {
		while (!box_a.isEmpty()) {
			System.out.println("The packer pack the L "+box_a.remove());
		}
	}

	@Override
	public void run() {
		try {
			
			while (true) {
				System.out.println("The packer gives a empty box");
				this.flag.set(this.exchanger.exchange(this.flag.get()));
				System.out.println("The packer recives a full box");
				
				if(this.flag.get()) {
					removeBulb(this.box);
					Thread.sleep(500);
				}
			}
		} catch ( InterruptedException e) {e.printStackTrace();}
	}
}
