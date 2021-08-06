package instance;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

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
    public void run() {
        try {
            while (!warehouse.isEmpty()) {
                barrier.await();
                buy();
                barrier.await();
            }
        } catch (InterruptedException e) {
            System.out.println("Внимание! Во время работы программы возникла ошибка завершения потока");
        } catch (BrokenBarrierException e) {
            System.out.println("Внимание! Во время работы программы возникла ошибка многопоточности");
        }
        printResult();
    }

    private void buy() {
        Random random = new Random();
        int count = random.nextInt(10) + 1;
        int currentAmount = warehouse.sell(count);
        if (currentAmount != 0) {
            totalAmount += currentAmount;
            numberOfPurchases++;
        }
    }

    public void printResult() {
        System.out.println("Номер покупателя: " + customerNumber + ". Количество покупок " + numberOfPurchases +
                " на общую сумму " + totalAmount);
    }
}
