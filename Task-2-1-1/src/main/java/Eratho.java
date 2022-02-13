import java.util.ArrayList;

public class Eratho {
    public boolean isPrime(Integer number) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 2; i <= number; i++) {
            res.add(i);
        }
        for (int j = 0; j < res.size(); j++) {
            Integer nextPrime = res.get(j);
            if (nextPrime > Math.sqrt(number.doubleValue())) {
                break;
            }
            for (int i = 2; i * nextPrime <= number; i++) {
                Integer notPrime = i * nextPrime;
                if (notPrime.equals(number)) return false;
                res.remove(notPrime);
            }
        }
        return true;
    }
}
