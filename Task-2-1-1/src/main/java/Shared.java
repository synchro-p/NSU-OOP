import java.util.ArrayList;
import java.util.HashMap;

public class Shared {
    private final ArrayList<Integer> numbers;
    private final HashMap<Integer, Boolean> result = new HashMap<>();
    private Integer current = 0;

    public Shared(ArrayList<Integer> numberArray) {
        numbers = numberArray;
    }

    public synchronized Integer getCurrent() {
        if (current == numbers.size())
            return -1;
        return (numbers.get(current++));
    }

    public synchronized void updateResults(Integer number, Boolean isPrime) {
        result.put(number, isPrime);
    }

    public ArrayList<Boolean> getResult() {
        ArrayList<Boolean> formattedResult = new ArrayList<>();
        for (Integer number : numbers) {
            formattedResult.add(result.get(number));
        }
        return formattedResult;
    }
}
