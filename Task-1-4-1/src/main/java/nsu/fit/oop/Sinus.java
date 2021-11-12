package nsu.fit.oop;

import java.util.ArrayList;

public class Sinus extends Operation {
    public Sinus(){
        arity = 1;
    }
    @Override
    double doIt(ArrayList<Double> arguments) {
        return Math.sin(arguments.get(0));
    }
}
