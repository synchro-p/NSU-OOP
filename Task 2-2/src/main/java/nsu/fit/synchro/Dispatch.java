package nsu.fit.synchro;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class Dispatch implements Runnable {
    Info info;
    Warehouse warehouse;
    public Dispatch(Info info, Warehouse warehouse) {
        this.info = info;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        ArrayList<Thread> cooks = new ArrayList<>();
        ArrayList<SynchronousQueue<Integer>> queues = new ArrayList<>();
        for (int i = 0; i < info.getCooks(); i++) {
            SynchronousQueue<Integer> newQueue = new SynchronousQueue<>();
            Thread newThread = new Thread(new ThreadExample(i, info.getExp().get(i), newQueue, warehouse));
            queues.add(newQueue);
            cooks.add(newThread);
            newThread.start();
        }
        Scanner scanner = new Scanner(System.in);
        int cnt = 0;
        while (true) {
            if (scanner.hasNext()) {
                if (scanner.next().equals(".")) {
                    break;
                } else {
                    try {
                        queues.get(cnt++ % 3).put(cnt);
                        System.out.println("Order " + cnt + " queued for worker " + (cnt - 1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        for (SynchronousQueue<Integer> queue : queues) {
            try {
                queue.put(-1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
