package nsu.fit.oop;

import java.util.ArrayList;

public class Cosinus extends Operation{
    private Cosinus(){
        arity = 1;
    }
    double doIt(ArrayList<Double> arguments) {
        return Math.cos(arguments.get(0));
    }
    public static OperationFactory getFactory() {
        return Cosinus::new;
    }
}
