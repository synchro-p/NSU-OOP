package nsu.fit.synchro;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class Dispatcher implements Runnable {
    private final ArrayList<CookExample> cooks;
    private final SourceRunnable source;

    public Dispatcher(ArrayList<CookExample> cooks, SourceRunnable source) {
        this.cooks = cooks;
        this.source = source;
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
        source.setChannel(channel);
        Thread sourceThread = new Thread(source);
        sourceThread.start();
        Picker picker = new Picker();
        while (true) {
            Integer orderNumber = null;
            try {
                orderNumber = channel.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (orderNumber == null) {
                throw new IllegalArgumentException();
            }
            if (orderNumber == -1) {
                break;
            }
            Integer i;
            do {
                i = picker.chooseNext(frees, experiences);
            } while (i == -1);
            cooks.get(i).setOrder(orderNumber);
            frees.get(i).set(false);
            threadPool.execute(cooks.get(i));
        }
        threadPool.shutdown();
    }
}
