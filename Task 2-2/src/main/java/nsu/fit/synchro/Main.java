package nsu.fit.synchro;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JsonParser jsonParser = new JsonParser();
        Info info = jsonParser.read("in.json");
        PizzaWarehouse warehouse = new PizzaWarehouse(info.getCapacity());
        WorkerInitializer init = new WorkerInitializer(info, warehouse);
        ArrayList<Thread> couriers = init.initCouriers();
        ArrayList<CookExample> cooks = init.initCooks();
        //TimedRandomQueue source = new TimedRandomQueue();
        SourceRunnable source = new SystemQueue();
        Dispatcher dispatcher = new Dispatcher(cooks, source);
        dispatcher.run();
        for (Thread courier : couriers) {
            courier.interrupt();
        }
    }
}
