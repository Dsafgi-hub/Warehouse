package instance;

import java.util.concurrent.atomic.AtomicInteger;

public class Warehouse {
    // public static AtomicInteger capacity = new AtomicInteger(1000);

    private final AtomicInteger capacity;

    public Warehouse(int capacity) {
        this.capacity = new AtomicInteger(capacity);
    }

    public boolean isEmpty() {
        return capacity.get() == 0;
    }

    public int sell(int count) {
        int tmp = Math.min(capacity.get(), count);
        if (capacity.get() <= count) {
            capacity.set(0);
        } else {
            capacity.set(capacity.get() - count);
        }
        return tmp;
    }

}
