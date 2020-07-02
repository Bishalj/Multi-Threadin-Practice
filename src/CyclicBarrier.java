import com.sun.org.apache.bcel.internal.ExceptionConstants;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrier {

    volatile int size;
    CyclicBarrier(int size){
        this.size = size;
    }

    void await() throws InterruptedException {
        synchronized (this) {
            size--;
            if (size == 0)
                notifyAll();
            else
                wait();
        }
    }
}

class Operation{
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        Thread t1 = new Thread(new Task(cyclicBarrier, "T1"));
        Thread t2 = new Thread(new Task(cyclicBarrier, "T2"));
        Thread t3 = new Thread(new Task(cyclicBarrier, "T3"));
        t1.start();
        t2.start();
        t3.start();
    }
}

class Task implements Runnable{
    private CyclicBarrier cyclicBarrier;

    Task(CyclicBarrier cyclicBarrier, String name){
        this.cyclicBarrier = cyclicBarrier;
        Thread.currentThread().setName(name);
    }

    @Override
    public void run() {
        try {

            Thread.sleep(1000);
            System.out.println("Before: " + Thread.currentThread().getName());
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("After: " +Thread.currentThread().getName());

    }
}
