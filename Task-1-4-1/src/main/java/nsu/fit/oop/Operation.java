package nsu.fit.oop;

import java.util.ArrayList;

public abstract class Operation {
    int arity;
    abstract double doIt(ArrayList<Double> arguments);
    public static OperationFactory getFactory(){
        return null;
    }
}
