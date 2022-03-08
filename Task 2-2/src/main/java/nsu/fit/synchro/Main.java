package nsu.fit.synchro;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Json json = new Json();
        Info info = json.read("in.json");
        Dispatch disp = new Dispatch(info);
        disp.run();
    }
}
