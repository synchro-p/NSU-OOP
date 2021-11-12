package nsu.fit.oop;

import java.util.ArrayList;

public class Cosinus extends Operation{
    public Cosinus(){
        arity = 1;
    }
    @Override
    double doIt(ArrayList<Double> arguments) {
        return Math.cos(arguments.get(0));
    }
}
