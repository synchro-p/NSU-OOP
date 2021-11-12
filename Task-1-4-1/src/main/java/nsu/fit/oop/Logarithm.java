package nsu.fit.oop;

import java.util.ArrayList;

public class Logarithm extends Operation {
    public Logarithm(){
        arity = 1;
    }

    @Override
    double doIt(ArrayList<Double> arguments) {
        return Math.log(arguments.get(0));
    }
}
