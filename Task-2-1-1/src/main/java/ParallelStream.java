import java.io.File;
import java.util.ArrayList;

public class ParallelStream implements Runnable {
    @Override
    public void run(){
        File input = new File("input.txt");
        ArrayList<Integer> numbers = new MyFileReader().read(input);
        ArrayList<Boolean> result = new ArrayList<>();
        numbers.parallelStream().forEach(x -> result.add(new Eratho().isPrime(x)));
        //System.out.println(result);
    }
}
