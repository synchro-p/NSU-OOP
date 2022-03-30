package nsu.fit.synchro;

import java.util.ArrayList;

public class CourierExample implements Runnable {
    private final PizzaWarehouse warehouse;
    private final Integer trunk;
    private final Integer speed;

    public Integer getTrunk() {
        return trunk;
    }

    public CourierExample(PizzaWarehouse warehouse, Integer trunk, Integer speed) {
        this.speed = speed;
        this.trunk = trunk;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Courier free");
            ArrayList<Integer> orders = warehouse.getOrder(this.trunk);
            if (orders == null) {
                return;
            }
            System.out.println("Courier started delivering orders " + orders);
            long timeUnit = (long)(20000.0/(double)this.speed);
            System.out.println(timeUnit);
            try {
                Thread.sleep(timeUnit*2);
                System.out.println("Courier on delivering track");
                for (Integer order : orders) {
                    Thread.sleep(timeUnit);
                    System.out.println("Courier delivered order " + order);
                }
                System.out.println("Courier stopped delivering, coming back");
                Thread.sleep(timeUnit*2);
            } catch (InterruptedException e) {
                return;
            }
            System.out.println("Courier delivered orders " + orders);
        }
    }
}
