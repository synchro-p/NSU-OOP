package nsu.fit.synchro;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class ThreadExample implements Runnable {
    Integer exp;
    Integer id;
    SynchronousQueue<Integer> queue;
    Warehouse warehouse;

    public ThreadExample(Integer id, Integer exp, SynchronousQueue<Integer> queue, Warehouse warehouse) {
        this.warehouse = warehouse;
        this.queue = queue;
        this.id = id;
        this.exp = exp;
    }

    @Override
    public void run() {
        while (true) {
            Integer ordNum = null;
            try {
                ordNum = queue.poll(2000, TimeUnit.DAYS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assert ordNum != null;
            if (ordNum.equals(-1)) {
                break;
            }
            System.out.println("Worker " + this.id + " started order " + ordNum);
            double toSleep = 30000.0 / exp;
            try {
                Thread.sleep((long) toSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Worker " + this.id + " finished making order " + ordNum);
            warehouse.putOrder(ordNum);
            System.out.println("Worker " + this.id + " put order " + ordNum + " into warehouse");
        }
    }
}
