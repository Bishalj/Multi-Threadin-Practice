import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerPractice {
    volatile int item = 0;
    final int MAX_SIZE = 5;
    Queue<Integer> queue = new LinkedList();
    ReentrantLock lock = new ReentrantLock();
    Condition full = lock.newCondition();
    Condition blank = lock.newCondition();
    void produce() throws InterruptedException {

        List<String> liarrst = new ArrayList<>();
        while(true){
                Thread.sleep(1000);
                lock.lock();
                while(queue.size() == MAX_SIZE)
                    full.await();
                System.out.println("Produced Item: " + ++item);
                queue.add(item);
                blank.signalAll();
                lock.unlock();
            }
        }


    void consume() throws InterruptedException {
        while (true) {
            lock.lock();
                Thread.sleep(1000);
                while (queue.size() == 0)
                    blank.await();
                System.out.println("Consumed Item: " + queue.remove());
                full.signalAll();
            lock.unlock();
            }
        }


    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerPractice producerConsumerPractice = new ProducerConsumerPractice();
        Thread produce1 = new Thread(() -> {
            try {
                producerConsumerPractice.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread produce2 = new Thread(() -> {
            try {
                producerConsumerPractice.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread produce3 = new Thread(() -> {
            try {
                producerConsumerPractice.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread consume = new Thread(() -> {
            try {
                producerConsumerPractice.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread consumer2 = new Thread(() -> {
            try {
                producerConsumerPractice.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        produce1.start();
        produce2.start();
        consume.start();
        produce3.start();
        consumer2.start();
    }
}