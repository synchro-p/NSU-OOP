package nsu.fit.oop;

import java.util.ArrayList;

public class Power extends Operation{
    private Power(){
        arity = 2;
    }
    public static OperationFactory getFactory(){
        return () -> new Power();
    }
    double doIt(ArrayList<Double> arguments) {
        return Math.pow(arguments.get(0),arguments.get(1));
    }
}
