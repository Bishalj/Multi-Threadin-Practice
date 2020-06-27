import sun.rmi.rmic.iiop.ValueType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HackerVaultProblem {
    static  int MAX_PASSWORD = 9999;
    public static class Vault{
        private int password;

        public  Vault(int password){
            this.password = password;
        }

        public boolean isPasswordCorrect(int password){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return password == this.password;
        }
    }

    public static class Police extends Thread{
        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Police: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.exit(0);
        }
    }
    public static abstract class HackerThread extends Thread{
        protected Vault vault;

        HackerThread(Vault vault){
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public synchronized void start() {
            System.out.println("Thread Started: " + this.getName());
            super.start();
        }
    }

    public static class AscendingHacker extends HackerThread{

        AscendingHacker(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int i = 0; i < MAX_PASSWORD; i++) {
                if(vault.isPasswordCorrect(i)){
                    System.out.println("Ascending Password is: " + i);
                    System.exit(0);
                }
            }
        }
    }

    public static class DesscendingHacker extends HackerThread{

        DesscendingHacker(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int i = MAX_PASSWORD; i >= 0; i--) {
                if(vault.isPasswordCorrect(i)){
                    System.out.println("Descending Password is: " + i);
                    System.exit(0);
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("I'm going for a walk");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("I'm going to swim");
            }
        });

        thread1.start();
        thread2.start();
        System.out.println("I'm going home");
        Thread police = new Police();
        Random random = new Random();
        Vault vault = new Vault(random.nextInt(MAX_PASSWORD+1));
        Thread asc = new AscendingHacker(vault);
        Thread desc = new DesscendingHacker(vault);

        List<Thread> threads = new ArrayList<>();
        threads.add(asc);
        threads.add(desc);
        threads.add(police);

        threads.forEach(
                t -> t.start()
        );
    }


}
