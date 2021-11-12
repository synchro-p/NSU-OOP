package nsu.fit.oop;

import java.util.ArrayList;

public class Plus extends Operation{
    public Plus(){
        arity = 2;
    }
    public double doIt(ArrayList<Double> arguments) {
        return arguments.get(0)+arguments.get(1);
    }
}
