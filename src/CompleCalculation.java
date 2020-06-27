import javax.swing.*;
import java.math.BigInteger;
import java.util.concurrent.Callable;

class ComplexCalculation {
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        BigInteger result;
        PowerCalculatingThread p1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread p2 = new PowerCalculatingThread(base2, power2);
        Thread t1 = p1;
        Thread t2 = p2;
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result = p1.getResult().add(p2.getResult());
        return result;
    }

      public static void main(String[] args) throws InterruptedException {
          ComplexCalculation complexCalculation = new ComplexCalculation();
          BigInteger b1 = new BigInteger("20");
          BigInteger b2 = new BigInteger("10");
          BigInteger p1 = new BigInteger("10");
          BigInteger p2 = new BigInteger("10");
          System.out.println(complexCalculation.calculateResult(b1,p1,b2,p2).toString());
      }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            this.result = base.pow(power.intValue());
        }

        public BigInteger getResult() { return result; }
    }
}


class ff implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        return null;
    }
}