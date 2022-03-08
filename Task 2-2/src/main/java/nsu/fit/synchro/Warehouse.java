package nsu.fit.synchro;

import java.util.concurrent.ArrayBlockingQueue;

public class Warehouse {
    ArrayBlockingQueue<Integer> queue;
    public Warehouse(Info info) {
        queue = new ArrayBlockingQueue<>(info.getCapacity());
    }

    public synchronized void putOrder(Integer num) {
        if (queue.remainingCapacity() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            queue.put(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Warehouse capacity is " + queue.remainingCapacity());
    }

    public synchronized Integer getOrder() {
        if (queue.remainingCapacity() == queue.size()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.poll();
    }
}
