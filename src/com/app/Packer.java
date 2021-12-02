package com.app;

import java.util.concurrent.Exchanger;

import javax.swing.text.PlainDocument;

/**
* Packer
*/
public class Packer implements Runnable {
	private final Exchanger<Box> exchanger;
	private Box box;

	public Packer(Exchanger<Box> exchanger) {
		this.exchanger = exchanger;
		this.box = new Box(true);
	}

	public void removeBulb(Box box_a) {
		System.out.println("The packer pack the L "+box_a.remove());
	}

	@Override
	public void run() {
		try {
			
			while (true) {
				removeBulb(this.box);
				
				if(this.box.isEmpty()) {
					System.out.println("The packer gives a empty box");
					this.box = this.exchanger.exchange(box);
					System.out.println("The packer recives a full box");
				}
			}
		} catch ( InterruptedException e) {e.printStackTrace();}
	}
}
