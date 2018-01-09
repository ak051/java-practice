package multiThreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierDemo {

	public static CyclicBarrier cylicBarrier;

	public static void main(String[] args) {
		cylicBarrier = new CyclicBarrier(3);
		System.out.println("Parties required to trip the barrier: " + cylicBarrier.getParties());
		
		Thread t1 = new Thread(new Task1());
		
		Thread t2 = new Thread(new Task2());
		
		t1.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		t1.interrupt();
		t2.start();
			
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Parties waiting at the barrier: " + cylicBarrier.getNumberWaiting());
		
		System.out.println("Is Barrier Broken: " + cylicBarrier.isBroken());
		
		try {
			cylicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		
		System.out.println("Parties waiting at the barrier: " + cylicBarrier.getNumberWaiting());
		
		System.out.println("All tasks finished");
		
		cylicBarrier.reset();
		
		System.out.println("Cylic Barrier Reset Successful !!");
		
		System.out.println("Parties required to trip the barrier: " + cylicBarrier.getParties());
		
	}

}

class Task1 implements Runnable {

	@Override
	public void run() {
		System.out.println("Task 1 started");
		System.out.println("Task 1 finished");
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		try {
			CyclicBarrierDemo.cylicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}

		/*try {
			CyclicBarrierDemo.cylicBarrier.await(300,TimeUnit.MICROSECONDS);
		} catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
			e.printStackTrace();
		}*/
		System.out.println("Task1 crossed the barrier");

	}
}

class Task2 implements Runnable {

	@Override
	public void run() {
		System.out.println("Task 2 started");
	

		System.out.println("Task 2 finished");

		try {
			CyclicBarrierDemo.cylicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("Task2 crossed the barrier");

	}
}