package multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyThread implements Runnable {

	final CyclicBarrier barrier;

	public MyThread(CyclicBarrier barrier) {
		this.barrier = barrier;

	}

	@Override
	public void run() {
		try {
			barrier.await();
			Thread.sleep(300);
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + " is executing ---");
	}

}
