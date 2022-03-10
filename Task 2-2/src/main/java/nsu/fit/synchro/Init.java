package nsu.fit.synchro;

import java.util.ArrayList;

public class Init {
    Info info;
    Warehouse warehouse;

    public Init(Info info, Warehouse warehouse) {
        this.info = info;
        this.warehouse = warehouse;
    }

    public ArrayList<Thread> initCouriers() {
        ArrayList<Thread> result = new ArrayList<>();
        for (int i = 0; i < info.getCouriers(); i++) {
            Thread newThread = new Thread(new CourierExample(warehouse));
            result.add(newThread);
            newThread.start();
        }
        return result;
    }

    public ArrayList<CookExample> initCooks() {
        ArrayList<CookExample> result = new ArrayList<>();
        for (int i = 0; i < info.getCooks(); i++) {
            Integer exactExperience = info.getExp().get(i);
            CookExample newCook = new CookExample(true, exactExperience, warehouse, i);
            result.add(newCook);
        }
        return result;
    }
}
