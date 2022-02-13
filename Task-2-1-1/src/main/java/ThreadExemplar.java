public class ThreadExemplar implements Runnable {
    private final Shared shared;

    public ThreadExemplar(Shared newShared) {
        shared = newShared;
    }

    @Override
    public void run() {
        Integer toCheck = shared.getCurrent();
        while (toCheck > -1) {
            shared.updateResults(toCheck, new Eratho().isPrime(toCheck));
            toCheck = shared.getCurrent();
        }
    }
}
