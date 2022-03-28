package nsu.fit.synchro;

import java.util.concurrent.ArrayBlockingQueue;

public abstract class SourceThread implements Runnable {
    ArrayBlockingQueue<Integer> channel;
    abstract public void setChannel (ArrayBlockingQueue<Integer> channel);
}
