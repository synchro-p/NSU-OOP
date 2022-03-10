package nsu.fit.synchro.threads;

import java.util.ArrayList;
import java.util.HashMap;

public class Shared {
    private final ArrayList<Integer> numbers;
    private final HashMap<Integer, Boolean> result = new HashMap<>();
    private Integer current = 0;
    private Boolean overFlag = false;
    public Shared(ArrayList<Integer> numberArray) {
        numbers = numberArray;
    }

    /**
     * Gets the next unprocessed number with synchronization (for multithreading)
     * @return the next number for a thread to process or -1, if numbers are depleted
     */
    public synchronized Integer getCurrent() {
        if (current == numbers.size() || overFlag)
            return -1;
        return (numbers.get(current++));
    }

    public synchronized Boolean foundNonPrime(){
        return overFlag;
    }

    /**
     * Puts new result into a map
     */
    public synchronized void updateResults(Integer number, Boolean isPrime) {
        if (!isPrime) {
            overFlag = true;
        }
        result.put(number, isPrime);
    }

    /**
     * Gets boolean values for each number from input in order
     * @return ArrayList with said boolean values
     */
    public ArrayList<Boolean> getResult() {
        ArrayList<Boolean> formattedResult = new ArrayList<>();
        for (Integer number : numbers) {
            formattedResult.add(result.get(number));
        }
        return formattedResult;
    }
}
