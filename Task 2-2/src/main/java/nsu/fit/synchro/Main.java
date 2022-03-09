package nsu.fit.synchro;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Json json = new Json();
        Info info = json.read("in.json");
        Warehouse warehouse = new Warehouse(info);
        Thread dispatch = new Thread(new Dispatch(info, warehouse));
        dispatch.start();
        Thread dispatchCourier = new Thread(new DispatchCourier(info, warehouse));
        dispatchCourier.start();
    }
}
