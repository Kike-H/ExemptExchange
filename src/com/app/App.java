package com.app;

import java.util.concurrent.Exchanger;


class App {
	public static void main(String[] args) throws InterruptedException { 
		final Exchanger<Boolean> exchanger = new Exchanger<Boolean>();
		Box box = new Box();

		Thread.sleep(400);

		new Thread(new Checker(box,exchanger)).start();
		new Thread(new Packer(box, exchanger)).start();
	} 
}
