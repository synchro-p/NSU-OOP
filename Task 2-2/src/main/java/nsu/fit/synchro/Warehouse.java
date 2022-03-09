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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notify();
            System.out.println("notified");
        }
        else {
            try {
                queue.put(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized Integer getOrder() {
        if (queue.isEmpty()) {
            try {
                System.out.println("waiting");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("unwaited");
        if (queue.remainingCapacity() == 0) {
            int res = queue.poll();
            notify();
            return res;
        }
        else return queue.poll();
    }
}
