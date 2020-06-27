public class DeadLock {

    static Object object1 = new Object();
    static Object object2 = new Object();
    public static class ThreadOne extends Thread{

        @Override
        public void run() {
            synchronized (object1) {
                System.out.println("Inside thread 1 -> object 1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 1: Waiting for ObjectLock 2");
                synchronized (object2) {
                    System.out.println("Inside thread 1 -> object 2");
                }
            }
        }
    }

    public static class ThreadTwo extends Thread{
        @Override
        public void run() {
            synchronized (object2) {
                System.out.println("Inside thread 2 -> object 2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 2: Waiting for ObjectLock 1");
                synchronized (object1) {
                    System.out.println("Inside thread 2 -> object 1");
                }
            }
        }
    }
}

class Execute{
    public static void main(String[] args) {
        Thread threadOne = new DeadLock.ThreadOne();
        Thread threadTwo = new DeadLock.ThreadTwo();

        threadOne.start();
        threadTwo.start();
    }
}
