package nsu.fit.synchro;

import java.util.ArrayList;

public class WorkerInitializer {
    private final Info info;
    private final PizzaWarehouse warehouse;

    public WorkerInitializer(Info info, PizzaWarehouse warehouse) {
        this.info = info;
        this.warehouse = warehouse;
    }

    public ArrayList<Thread> initCouriers() {
        ArrayList<Thread> result = new ArrayList<>();
        for (int i = 0; i < info.getCouriersTrunk().size(); i++) {
            Thread newThread = new Thread(new CourierExample(warehouse, info.getCouriersTrunk().get(i),
                    info.getCouriersSpeed().get(i)));
            result.add(newThread);
            newThread.start();
        }
        return result;
    }

    public ArrayList<CookExample> initCooks() {
        ArrayList<CookExample> result = new ArrayList<>();
        for (int i = 0; i < info.getCooksExp().size(); i++) {
            Integer exactExperience = info.getCooksExp().get(i);
            CookExample newCook = new CookExample(true, exactExperience, warehouse, i);
            result.add(newCook);
        }
        return result;
    }
}
