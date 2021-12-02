package com.app;

import java.util.concurrent.Exchanger;


class App {
	public static void main(String[] args) throws InterruptedException { 
		final Exchanger<Box> exchanger = new Exchanger<Box>();

		Thread.sleep(400);

		new Thread(new Checker(exchanger)).start();
		new Thread(new Packer(exchanger)).start();
	} 
}
