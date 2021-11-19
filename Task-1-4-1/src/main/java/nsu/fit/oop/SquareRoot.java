package nsu.fit.oop;

import java.util.ArrayList;

public class SquareRoot extends Operation{
    private SquareRoot(){
        arity = 1;
    }
    double doIt(ArrayList<Double> arguments) {
        return Math.sqrt(arguments.get(0));
    }
    public static OperationFactory getFactory(){
        return SquareRoot::new;
    }
}
