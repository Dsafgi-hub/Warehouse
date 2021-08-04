package service;

import instance.Customer;
import instance.Warehouse;

import java.util.concurrent.*;

public class WarehouseService {

    public static void main(String[] args)  {
        if (args.length > 0) {
            CyclicBarrier barrier = new CyclicBarrier(Integer.parseInt(args[0]));
            Warehouse warehouse = new Warehouse(1000);

            for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                new Thread(new Customer(i, barrier, warehouse)).start();
            }

        } else {
            System.out.println("Внимание! Укажите количество покупателей во входных параметрах");
        }
    }
}
