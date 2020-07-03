public class DeadLockPractice {
    static Object object1 = new Object();
    static Object object2 = new Object();

    static class  ThreadOne implements Runnable{

        @Override
        public void run() {
            synchronized (object1){
                System.out.println("Inside thread 1: object 1 lock");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (object2){
                    System.out.println("Inside thread 1: object 2 lock");
                }
                System.out.println("Inside thread 1: object 2 lock released");
            }
            System.out.println("Inside thread 1: object 1 lock released");
        }
    }

    static class  ThreadTwo implements Runnable{

        @Override
        public void run() {
            synchronized (object2){
                System.out.println("Inside thread 2: object 2 lock");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (object1){
                    System.out.println("Inside thread 2: object 1 lock");
                }
                System.out.println("Inside thread 2: object 1 lock released");
            }
            System.out.println("Inside thread 2: object 2 lock released");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadOne());
        Thread t2 = new Thread(new ThreadTwo());
        t1.start();
        t2.start();

    }
}
