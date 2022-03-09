package nsu.fit.synchro.threads;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class ParallelStream implements Runnable {
    /**
     * Runs a test implementing parallel streaming
     */
    @Override
    public void run() {
        File input = new File("input.txt");
        ArrayList<Integer> numbers = new MyFileReader().read(input);
        ArrayList<Boolean> result = Collections.
                synchronizedList(numbers).
                parallelStream().
                map(x -> new Eratho().isPrime(x)).
                collect(Collectors.toCollection(ArrayList::new));
    }
}
