package nsu.fit.synchro.solution;

import nsu.fit.synchro.threads.MultiThreaded;
import nsu.fit.synchro.threads.ParallelStream;
import nsu.fit.synchro.threads.SingleThreaded;

import java.util.ArrayList;

public class TimeMeasurer {
    /**
     * Measures the amount of time in ms different solutions take
     * (averaged by 1000 approaches)
     *
     * @return ArrayList with results of measurements
     */
    public ArrayList<Double> measure() {
        long start;
        long total;
        ArrayList<Long> singleTimes = new ArrayList<>();
        ArrayList<Double> points = new ArrayList<>();

        SingleThreaded single = new SingleThreaded();
        for (int i = 0; i < 200; i++) {
            start = System.currentTimeMillis();
            single.run();
            total = System.currentTimeMillis() - start;
            singleTimes.add(total);
        }
        Double res = singleTimes.stream().mapToLong(a -> a).average().getAsDouble();
        points.add(res);
        singleTimes.clear();
        System.out.println(res);

        MultiThreaded multi = new MultiThreaded();
        for (int i = 1; i <= Runtime.getRuntime().availableProcessors(); i++) {
            multi.setThreads(i);
            for (int j = 0; j < 200; j++) {
                start = System.currentTimeMillis();
                multi.run();
                total = System.currentTimeMillis() - start;
                singleTimes.add(total);
            }
            res = singleTimes.stream().mapToLong(a -> a).average().getAsDouble();
            points.add(res);
            singleTimes.clear();
            System.out.println(res);
        }

        ParallelStream parallelStream = new ParallelStream();
        for (int i = 0; i < 200; i++) {
            start = System.currentTimeMillis();
            parallelStream.run();
            total = System.currentTimeMillis() - start;
            singleTimes.add(total);
        }
        res = singleTimes.stream().mapToLong(a -> a).average().getAsDouble();
        points.add(res);
        singleTimes.clear();
        System.out.println(res);
        return points;
    }
}
