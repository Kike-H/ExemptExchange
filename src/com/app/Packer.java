package com.app;

import java.util.concurrent.Exchanger;

/**
* Packer
*/
public class Packer extends Thread {
	private final Exchanger<Box> exchanger;
	private Box box;

	public Packer(Box box, Exchanger<Box> exchanger) {
		this.box = box;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
			try {
				while (true) {
					Box aux = box;
					if(box.isEmpty()) {
						System.out.println("The packer gives a empty box");
						box = exchanger.exchange(aux);
						System.out.println("The packer recives a full box");
					}
					Thread.sleep(1000);
					System.out.println("The packer pack the L "+aux.remove());
				}
			} catch ( InterruptedException e) {e.printStackTrace();}
	}
}
