public class CountDownLatch {

    int size;
    boolean interrupt  = false;

    CountDownLatch(int size){
        this.size = size;
    }

    synchronized void await() throws InterruptedException {
        wait();
    }

    synchronized void countDown(){
        size--;
        if (size == 0)
            notify();
    }
}

class Worker implements Runnable{

    private CountDownLatch countDownLatch;
    Worker(String threadName, CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
        Thread.currentThread().setName(threadName);
    }

    @Override
    public void run(){
        System.out.println("Thread Name: " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }
}



class Mains{
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        Runnable target;
        Worker worker1 = new Worker("Thread-1", countDownLatch);
        Worker worker2 = new Worker("Thread-2", countDownLatch);
        Worker worker3 = new Worker("Thread-3", countDownLatch);
        Worker worker4 = new Worker("Thread-4", countDownLatch);
        Thread Thread1 = new Thread(worker1);
        Thread Thread2 = new Thread(worker2);
        Thread Thread3 = new Thread(worker3);
        Thread Thread4 = new Thread(worker4);

        Thread1.start();
        Thread2.start();
        Thread3.start();
        Thread4.start();

        countDownLatch.await();
        System.out.println("Latch Released");



    }
}
