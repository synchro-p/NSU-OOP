package nsu.fit.synchro;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class PizzaWarehouse {
    private final ArrayBlockingQueue<Integer> queue;

    public PizzaWarehouse(Integer capacity) {
        queue = new ArrayBlockingQueue<>(capacity);
    }

    public ArrayBlockingQueue<Integer> getQueue(){
        return queue;
    }

    public synchronized void putOrder(Integer num) {
        while (queue.remainingCapacity() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            queue.put(num);
            System.out.println("Storage place left: " + queue.remainingCapacity());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();
    }

    public synchronized ArrayList<Integer> getOrder(Integer trunk) {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                return null;
            }
        }
        ArrayList<Integer> ordersTaken = new ArrayList<>(trunk);
        int toTake = Integer.min(trunk, queue.size());
        for (int i = 0; i < toTake; i++) {
            ordersTaken.add(queue.poll());
        }
        System.out.println("Storage place left: " + (queue.remainingCapacity()));
        notifyAll();
        return ordersTaken;
    }
}
