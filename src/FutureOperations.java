import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FutureOperations {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        checkingException();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Runnable target;
        Thread t = new Thread(() -> System.out.println("qwerty"));
        t.start();
        CompletableFuture
                .runAsync(() -> System.out.println("Bishal"))
                .thenRun(() -> System.out.println("jaiswal"))
                .thenRun(() -> new Throwable("Exception 2"));

        System.out.println("---------------------------------------------------------");
        List<Future> allFutures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
//            Future<Integer> future = executorService.submit(new Print(i));
            AtomicInteger atomicInteger = new AtomicInteger(i);
//            CompletableFuture.supplyAsync(() -> new Print(atomicInteger.get()));
            allFutures.add(CompletableFuture.supplyAsync(() -> new Print(atomicInteger.get())));
        }

        allFutures
                .forEach(future -> {
                    try {
                        System.out.println(future.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static String  checkingException() {
        try{
            return "Bishal";
        }finally {
            System.out.println("Finally executed");
        }
    }
}

class Print implements Callable<Integer> {
    private int i;

    public Print(int i) {
        this.i = i;
    }

    @Override
    public Integer call()throws Exception {
        System.out.println("Bishal: " + Thread.currentThread().getName());
        return i;
    }
}
