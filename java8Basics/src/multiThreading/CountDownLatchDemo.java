package multiThreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

	public static void main(String[] args) {
		
		CountDownLatch countDownLatch = new CountDownLatch(5);
		
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		for(int i = 0; i<5; i++) {
			executorService.submit(new Latch(i+1,countDownLatch));
		}
		executorService.shutdown();
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All tasks finished");

	}

}

class Latch implements Runnable {
	
	private int id;
	private CountDownLatch countDownLatch;
	
	public Latch(int id, CountDownLatch countDownLatch) {
		this.id = id;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		
		process();
	}

	private void process() {
		System.out.println("Currently working on the thread with Id: " + this.id);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Task completed for Id: " + this.id);
		this.countDownLatch.countDown();
		
	}
	
}
