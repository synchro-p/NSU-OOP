package nsu.fit.oop;

import java.util.ArrayList;

public class Divide extends Operation{
    private Divide(){
        arity = 2;
    }
    double doIt(ArrayList<Double> arguments) {
        return arguments.get(0)/arguments.get(1);
    }
    public static OperationFactory getFactory() {
        return Divide::new;
    }
}
