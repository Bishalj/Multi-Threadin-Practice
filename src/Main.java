public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello World!");

        Runnable target;
        Thread t1 = new Thread(() -> {
            System.out.println("Thread Started: " + Thread.currentThread().getName());
//            throw new RuntimeException("Hello");
        });

//        t1.setUncaughtExceptionHandler((thread, exception) -> System.out.println("Thread Name: " + thread.getName() + "\n Exception: " + exception.getMessage()));
//        Thread.sleep(1000);
        System.out.println("Before starting: " + Thread.currentThread().getName());
        t1.start();
        System.out.println("After starting: " + Thread.currentThread().getName());
    }
}
