package service;

import instance.Customer;
import instance.Warehouse;

import java.util.concurrent.*;

public class WarehouseService {
    private static final int WAREHOUSE_CAPACITY = 1000;

    public static void main(String[] args)  {
        if (args.length > 0) {
            try {
                CyclicBarrier barrier = new CyclicBarrier(Integer.parseInt(args[0]));
                Warehouse warehouse = new Warehouse(WAREHOUSE_CAPACITY);
                for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                    new Thread(new Customer(i, barrier, warehouse)).start();
                }
            } catch (NumberFormatException e) {
                System.out.println("Внимание! Количество покупателей должно быть числом");
            }
        } else {
            System.out.println("Внимание! Укажите количество покупателей во входных параметрах");
        }
    }
}
