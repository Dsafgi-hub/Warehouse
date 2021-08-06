package instance;

import java.util.concurrent.atomic.AtomicInteger;

public class Warehouse {
    private final AtomicInteger capacity;

    public Warehouse(int capacity) {
        this.capacity = new AtomicInteger(capacity);
    }

    public synchronized boolean isEmpty() {
        return capacity.get() == 0;
    }

    public synchronized int sell(int count) {
        int sellQuantity = Math.min(capacity.get(), count);
        if (capacity.get() <= count) {
            capacity.set(0);
        } else {
            capacity.set(capacity.get() - count);
        }
        return sellQuantity;
    }
}
