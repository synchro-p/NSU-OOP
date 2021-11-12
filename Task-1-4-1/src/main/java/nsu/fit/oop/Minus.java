package nsu.fit.oop;

import java.util.ArrayList;

public class Minus extends Operation{
    public Minus(){
        arity = 2;
    }
    public double doIt(ArrayList<Double> arguments) {
        return arguments.get(0)-arguments.get(1);
    }
}
