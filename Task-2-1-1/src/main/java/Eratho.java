public class Eratho {
    public boolean isPrime(Integer number) {
        for (int i = 2; i <= Math.sqrt(number.doubleValue()); i++) {
            if (number%i == 0) return false;
        }
        return true;
    }
}
