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

	public Packer(Box box, Exchanger<Boolean> exchanger) {
		this.exchanger = exchanger;
		this.box = box;
		this.alive = true;
	}

	public void removeBulb(Box box_a) {
		while (!box_a.isEmpty()) {
			System.out.println("The packer pack the 💡 "+box_a.remove());
		}
	}

	public void stopPacker() {
		this.alive = false;
	}

	@Override
	public void run() {
		try {
			
			while (this.alive) {
				System.out.println("The packer gives a empty box 📦🔜");
				this.flag.set(this.exchanger.exchange(this.flag.get()));
				System.out.println("The packer recives a full box 📦🔝");
				
				if(this.flag.get()) {
					removeBulb(this.box);
					Thread.sleep(1000);
				}
			}
		} catch ( InterruptedException e) {e.printStackTrace();}
	}
}
