package nsu.fit.oop;

import java.util.ArrayList;

public class SquareRoot extends Operation{
    public SquareRoot(){
        arity = 1;
    }

    @Override
    double doIt(ArrayList<Double> arguments) {
        return Math.sqrt(arguments.get(0));
    }
}
