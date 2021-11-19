package nsu.fit.oop;

import java.util.ArrayList;

public class Logarithm extends Operation {
    private Logarithm(){
        arity = 1;
    }
    double doIt(ArrayList<Double> arguments) {
        return Math.log(arguments.get(0));
    }
    public static OperationFactory getFactory(){
        return Logarithm::new;
    }
}
