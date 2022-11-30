package com.example.masterclass.concurrency;

public class Main {
	
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";        
	public static final String ANSI_CYAN = "\u001B[36m";

	/**
	 * 2 ways to run threads:
	 * 1. Use Thread or a class that extends Thread
	 * 2. Use a class that implements the Runnable interface
	 * 
	 * Must use the start() method to run threads concurrently (run() method runs in order)
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Im the main thread.");
		
		// Create a thread object if it is a re-used process. Otherwise anonymous class would be better.
		Thread anotherThread = new OtherThread();
		anotherThread.start();	// Cannot restart. Will throw a runtime exception.
		
		System.out.println("Hello again from the main thread.");
		
		new Thread() {
			public void run() {
				System.out.println("I'm an anonymous thread.");
			}
		}.start();
		
		Thread myRunnableThread = new Thread(new MyRunnable());
		myRunnableThread.start();
		//anotherThread.interrupt();
		
		
		Thread myRunnableThread2 = new Thread(new MyRunnable() {
			@Override
			public void run() {
				System.out.println("Hello from anonymous Runnable class");

				// Join to anotherThread. This ensures that execution will not continue until anotherThread completes.
				// Set a time-out in join to ensure this thread completes even if joined thread never terminates.
				try {
					anotherThread.join(2000);
					System.out.println("myRunnableThread2 is running again");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
		
		myRunnableThread2.start();

	}

}
