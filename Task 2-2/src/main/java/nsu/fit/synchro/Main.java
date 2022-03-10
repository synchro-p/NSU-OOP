package nsu.fit.synchro;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Json json = new Json();
        Info info = json.read("in.json");
        Warehouse warehouse = new Warehouse(info);
        Init init = new Init(info, warehouse);
        ArrayList<Thread> couriers = init.initCouriers();
        ArrayList<CookExample> cooks = init.initCooks();
        Dispatch dispatch = new Dispatch(cooks);
        dispatch.run();
        for (Thread courier : couriers) {
            courier.interrupt();
        }
    }
}
