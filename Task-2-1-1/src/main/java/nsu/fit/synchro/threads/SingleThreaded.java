package nsu.fit.synchro.threads;

import java.io.File;
import java.util.ArrayList;

public class SingleThreaded implements Runnable {
    /**
     * Runs a test of simple, one-threaded solution
     */
    @Override
    public void run() {
        File input = new File("input.txt");
        ArrayList<Integer> numbers = new MyFileReader().read(input);
        for (Integer number : numbers) {
            if (!new Eratho().isPrime(number)) {
                //System.out.println("Found non-prime");
                return;
            }
        }
        //System.out.println("All numbers prime");
    }
}
