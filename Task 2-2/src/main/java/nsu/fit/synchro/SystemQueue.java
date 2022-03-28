package nsu.fit.synchro;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class SystemQueue extends SourceRunnable {
    @Override
    public void setChannel(ArrayBlockingQueue<Integer> channel) {
        super.channel = channel;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int cnt = 0;
        while (scanner.hasNext()) {
            if (scanner.next().equals(".")) {
                channel.add(-1);
                break;
            }
            channel.add(cnt++);
        }
    }
}
