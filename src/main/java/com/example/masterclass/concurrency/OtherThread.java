package com.example.masterclass.concurrency;

public class OtherThread extends Thread {
	
	@Override 
	public void run() {
		System.out.println(Main.ANSI_GREEN+"I'm the Other thread");
		
		try {
			// put thread to sleep for 5 seconds
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println(Main.ANSI_GREEN+"Another thread woke me up");
			return;
		}
		
		System.out.println(Main.ANSI_GREEN+"I am now awake.");
	}

}
