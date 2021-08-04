package instance;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Customer implements Runnable {

    private final CyclicBarrier barrier;
    private final Warehouse warehouse;

    private final int customerNumber;
    private int numberOfPurchases;
    private int totalAmount;

    public Customer(int customerNumber, CyclicBarrier barrier, Warehouse warehouse) {
        this.customerNumber = customerNumber;
        this.barrier = barrier;
        this.warehouse = warehouse;
    }

    @Override
    public synchronized void run() {
        Random random = new Random();
        int count = random.nextInt(10) + 1;

        while (!warehouse.isEmpty()) {
           totalAmount += warehouse.sell(count);
           numberOfPurchases++;
           try {
               barrier.await(5, TimeUnit.SECONDS);
           } catch (InterruptedException e) {
               System.out.println("Внимание! Во время работы программы возникла ошибка завершения потока");

           } catch (BrokenBarrierException e) {
               System.out.println("Внимание! Во время работы программы возникла ошибка многопоточности");
           } catch (TimeoutException e) {
               System.out.println("Внимание! Во время работы программы возникла временная ошибка у одного из потоков");
           }
        }


        resetBarrier(barrier);

        printResult();
    }

    public void printResult() {
        System.out.println("Номер покупателя: " + customerNumber + ". Количество покупок " + numberOfPurchases +
                " на общую сумму " + totalAmount);
    }

    public void resetBarrier(CyclicBarrier barrier) {
        if (barrier.isBroken()) {
           barrier.reset();
        }
    }
}
