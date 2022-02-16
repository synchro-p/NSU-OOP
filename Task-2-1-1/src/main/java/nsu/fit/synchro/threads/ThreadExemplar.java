package nsu.fit.synchro.threads;

public class ThreadExemplar implements Runnable {
    private final Shared shared;

    public ThreadExemplar(Shared newShared) {
        shared = newShared;
    }

    /**
     * Checks integers from 'shared' for primeness in synchronized fashion
     */
    @Override
    public void run() {
        Integer toCheck = shared.getCurrent();
        while (toCheck > -1) {
            shared.updateResults(toCheck, new Eratho().isPrime(toCheck));
            toCheck = shared.getCurrent();
        }
    }
}
