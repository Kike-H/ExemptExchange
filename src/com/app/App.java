package com.app;

import java.util.concurrent.Exchanger;


class App {
	public static void main(String[] args) throws InterruptedException { 
		final Exchanger<Box> exchanger = new Exchanger<Box>();
		final Box box_e = new Box(10);
		final Box box_f = new Box(10);

		new Checker(box_f, exchanger).start();

		Thread.sleep(11000);

		new Packer(box_e, exchanger).start();
	} 
}
