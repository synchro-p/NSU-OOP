package nsu.fit.synchro;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class TimedRandomQueue extends SourceThread{
    @Override
    public void setChannel(ArrayBlockingQueue<Integer> channel) {
        super.channel = channel;
    }

    @Override
    public void run(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write number of orders to give");
        int quantity = scanner.nextInt();
        Random randomizer = new Random();
        for (int i = 0; i < quantity; i++) {
            try {
                Thread.sleep(randomizer.nextInt(1000)*10);
                channel.add(i);
                System.out.println("Order queued");
            } catch (InterruptedException e) {
                break;
            }
        }
        channel.add(-1);
    }
}
