public class Main {
    public static void main(String[] args) {
        SingleThreaded single = new SingleThreaded();
        long start = System.currentTimeMillis();
        single.run();
        long total = System.currentTimeMillis() - start;
        System.out.println("Single takes (in ms) " + total);

        MultiThreaded multi = new MultiThreaded();
        for (int i = 1; i <= Runtime.getRuntime().availableProcessors(); i++) {
            multi.setThreads(i);
            start = System.currentTimeMillis();
            multi.run();
            total = System.currentTimeMillis() - start;
            System.out.println("Multi-" + i + " takes (in ms) " + total);
        }

        ParallelStream parallelStream = new ParallelStream();
        start = System.currentTimeMillis();
        parallelStream.run();
        total = System.currentTimeMillis() - start;
        System.out.println("Parallel takes (in ms) " + total);
    }
}
