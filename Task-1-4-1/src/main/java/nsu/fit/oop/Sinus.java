package nsu.fit.oop;

import java.util.ArrayList;

public class Sinus extends Operation {
    private Sinus(){
        arity = 1;
    }
    double doIt(ArrayList<Double> arguments) {
        return Math.sin(arguments.get(0));
    }
    public static OperationFactory getFactory(){
        return Sinus::new;
    }
}
