package nsu.fit.oop;

import java.util.ArrayList;

public class Minus extends Operation{
    private Minus(){
        arity = 2;
    }
    double doIt(ArrayList<Double> arguments) {
        return arguments.get(0)-arguments.get(1);
    }
    public static OperationFactory getFactory(){
        return Minus::new;
    }
}
