package nsu.fit.synchro;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class Dispatch implements Runnable {
    ArrayList<CookExample> cooks;

    public Dispatch(ArrayList<CookExample> cooks) {
        this.cooks = cooks;
    }

    @Override
    public void run() {
        ArrayList<AtomicBoolean> frees = new ArrayList<>();
        ArrayList<Integer> experiences = new ArrayList<>();
        for (CookExample cook : cooks) {
            frees.add(cook.isFree());
            experiences.add(cook.getExp());
        }
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) newCachedThreadPool();
        ArrayBlockingQueue<Integer> channel = new ArrayBlockingQueue<>(10000);
        Thread systemQueue = new Thread(new SystemQueue(channel));
        systemQueue.start();
        while (true) {
            if (channel.isEmpty()) {
                continue;
            }
            Integer ordNum = channel.poll();
            if (ordNum == -1) {
                break;
            }
            Integer i = Picker.chooseNext(frees,experiences);
            while (i == -1) {
                i = Picker.chooseNext(frees,experiences);
            }
            cooks.get(i).setOrder(ordNum);
            frees.get(i).set(false);
            threadPool.execute(cooks.get(i));
        }
        threadPool.shutdown();
    }
}
