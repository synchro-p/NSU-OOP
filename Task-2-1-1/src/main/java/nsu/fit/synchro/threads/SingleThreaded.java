package nsu.fit.synchro.threads;

import java.io.File;
import java.util.ArrayList;

public class SingleThreaded implements Runnable {
    @Override
    public void run() {
        File input = new File("input.txt");
        ArrayList<Integer> numbers = new MyFileReader().read(input);
        ArrayList<Boolean> result = new ArrayList<>();
        numbers.forEach(x -> result.add(new Eratho().isPrime(x)));
        //System.out.println(result);
    }
}
