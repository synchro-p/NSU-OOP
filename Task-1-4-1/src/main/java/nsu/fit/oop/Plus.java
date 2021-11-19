package nsu.fit.oop;

import java.util.ArrayList;

public class Plus extends Operation{
    private Plus(){
        arity = 2;
    }
    public static OperationFactory getFactory(){
        return Plus::new;
    }
    double doIt(ArrayList<Double> arguments) {
        return arguments.get(0)+arguments.get(1);
    }
}
