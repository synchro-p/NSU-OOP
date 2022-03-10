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
        if (queue.isEmpty()) {
            try {
                queue.put(num);
                System.out.println("Storage place left: " + queue.remainingCapacity());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notify();
        } else {
            try {
                queue.put(num);
                System.out.println("Storage place left: " + queue.remainingCapacity());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized Integer getOrder() {
        if (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                return null;
            }
        }
        if (queue.remainingCapacity() == 0) {
            System.out.println("Storage place left: " + (queue.remainingCapacity() + 1));
            Integer res = queue.poll();
            notify();
            return res;
        } else {
            System.out.println("Storage place left: " + (queue.remainingCapacity() + 1));
            return queue.poll();
        }
    }
}
