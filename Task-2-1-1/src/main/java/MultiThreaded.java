import java.io.File;
import java.util.ArrayList;

public class MultiThreaded implements Runnable {
    private Integer threads = 1;

    public void setThreads(Integer newThreads) {
        threads = newThreads;
    }

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
        //System.out.println(shared.getResult());
    }
}
