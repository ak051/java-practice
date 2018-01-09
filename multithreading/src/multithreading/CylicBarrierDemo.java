package multithreading;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CylicBarrierDemo {

	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Cyclic Barrier executed =" + Thread.currentThread().getName());				
			}
		});
		
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		
		for(int i = 0 ; i<10; i++) {
			executorService.submit(new Thread(new MyThread(barrier), "MyThread " + i));
			try {
				//Thread.sleep(300);
				TimeUnit.SECONDS.sleep(2); 
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		
		executorService.shutdown();
	}

}
