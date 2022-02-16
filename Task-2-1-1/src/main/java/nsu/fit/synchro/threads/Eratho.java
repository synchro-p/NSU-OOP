package nsu.fit.synchro.threads;

public class Eratho {
    /**
     * Checks a number for primeness using the naive approach
     * @param number number to be checked
     * @return a boolean that indicates whether number is prime (true) or not (false)
     */
    public boolean isPrime(Integer number) {
        for (int i = 2; i <= Math.sqrt(number.doubleValue()); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
