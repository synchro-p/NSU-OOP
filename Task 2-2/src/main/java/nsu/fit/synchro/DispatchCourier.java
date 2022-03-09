package nsu.fit.synchro;

import java.util.ArrayList;

public class DispatchCourier implements Runnable {
    Info info;
    Warehouse warehouse;
    public DispatchCourier (Info info, Warehouse warehouse) {
        this.info = info;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        ArrayList<Thread> couriers = new ArrayList<>();
        for (int i = 0; i < info.getCouriers(); i++) {
            Thread newThread = new Thread(new CourierExample(warehouse));
            couriers.add(newThread);
            newThread.start();
        }
    }
}
