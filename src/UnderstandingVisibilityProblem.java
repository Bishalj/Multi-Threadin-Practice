public class UnderstandingVisibilityProblem{
    public static void main(String[] args) throws InterruptedException {
        CONST c = new CONST();
        Thread a = new A(c);
        Thread b = new B(c);
        a.start();

//        Thread.sleep(5);
        b.start();
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println("END");

    }

}


 class CONST{
    public  int counter = 1;
}

class A extends Thread{
    CONST CONST;
    A(CONST consta){
        CONST = consta;
    }
    @Override
    public void run() {
        int count=0;

        while (CONST.counter != 2) {
            System.out.println("A :" + count++);
        }
    }
}

class B extends Thread{

    CONST CONST;
    B(CONST consta){
        CONST = consta;
    }

    @Override
    public void run() {
        System.out.println("B: " + CONST.counter);
        CONST.counter++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}