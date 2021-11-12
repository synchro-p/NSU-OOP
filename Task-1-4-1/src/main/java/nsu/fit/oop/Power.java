package nsu.fit.oop;

import java.util.ArrayList;

public class Power extends Operation{
    public Power(){
        arity = 2;
    }
    @Override
    public double doIt(ArrayList<Double> arguments) {
        return Math.pow(arguments.get(0),arguments.get(1));
    }
}
