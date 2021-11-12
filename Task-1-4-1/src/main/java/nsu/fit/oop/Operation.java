package nsu.fit.oop;

import java.util.ArrayList;

public abstract class Operation {
    public int arity;
    abstract double doIt(ArrayList<Double> arguments);
}
