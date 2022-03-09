package nsu.fit.synchro.threads;

import java.io.File;
import java.util.ArrayList;

public class MultiThreaded implements Runnable {
    private Integer threads = 1;

    /**
     * Sets the number of threads to use when performing simulation
     * @param newThreads how many threads to create and utilize in run()
     */
    public void setThreads(Integer newThreads) {
        threads = newThreads;
    }

    /**
     * Runs a test with 1 or multiple threads, receiving data from file "input.txt"
     */
    public void run() {
        ArrayList<Thread> threadsList = new ArrayList<>();
        File input = new File("input.txt");
        ArrayList<Integer> numbers = new MyFileReader().read(input);
        Shared shared = new Shared(numbers);
        for (int i = 0; i < threads; i++) {
            Thread newThread = new Thread(new ThreadExemplar(shared));
            threadsList.add(newThread);
            newThread.start();
        }
        for (Thread thread : threadsList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
