package nsu.fit.synchro;

import java.util.concurrent.ArrayBlockingQueue;

public abstract class SourceRunnable implements Runnable {
    protected ArrayBlockingQueue<Integer> channel;
    public void setChannel (ArrayBlockingQueue<Integer> channel){
        this.channel = channel;
    }
}
