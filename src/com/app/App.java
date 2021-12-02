package com.app;

import java.util.concurrent.Exchanger;


class App {
	public static void main(String[] args) throws InterruptedException { 
		final Exchanger<Boolean> exchanger = new Exchanger<Boolean>();
		Box box = new Box();

		Thread.sleep(400);

		Checker tc = new Checker(box,exchanger);
		Packer tp = new Packer(box, exchanger);

		tc.start();
		tp.start();

		Thread.sleep(8000);

		tc.stop();
		tp.stop();
	} 
}
