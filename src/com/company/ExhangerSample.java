package com.company;

import java.util.concurrent.Exchanger;

public class ExhangerSample implements Sample {
    @Override
    public void runSample()  {
        Exchanger exchanger = new Exchanger();

        ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger, "A");
        ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger, "B");

        new Thread(exchangerRunnable1).start();
        new Thread(exchangerRunnable2).start();
    }

    class ExchangerRunnable implements Runnable{
        Exchanger exchanger = null;
        Object object = null;
        public ExchangerRunnable(Exchanger exchanger, Object a) {
            this.exchanger = exchanger;
            this.object = a;
        }

        @Override
        public void run() {
            try {
                Object previous = this.object;
                this.object = this.exchanger.exchange(this.object);
                System.out.println(Thread.currentThread().getName() +
                        "exchanged " + previous + " for"  + this.object);
            }catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

