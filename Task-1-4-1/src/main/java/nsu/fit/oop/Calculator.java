package nsu.fit.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Calculator {
    private static final HashMap<String, OperationFactory> factoryHashMap = new HashMap<>();

    public static void addOperation(String s,OperationFactory factory) {
        factoryHashMap.put(s,factory);
    }
    public static void addDefault(){
        addOperation("+", Plus.getFactory());
        addOperation("-", Minus.getFactory());
        addOperation("*", Multiply.getFactory());
        addOperation("/", Divide.getFactory());
        addOperation("sin", Sinus.getFactory());
        addOperation("cos", Cosinus.getFactory());
        addOperation("pow", Power.getFactory());
        addOperation("log", Logarithm.getFactory());
        addOperation("sqrt", SquareRoot.getFactory());
    }

    static double parseExpr(Scanner s){
        if (s.hasNextDouble()){
            return s.nextDouble();
        }
        else {
            if (!s.hasNext()) {
                throw new IllegalArgumentException("Not enough arguments");
            }
            String operationString = s.next();
            if (!factoryHashMap.containsKey(operationString)) {
                throw new IllegalArgumentException("Bad input or operation unavailable");
            }
            OperationFactory factory = factoryHashMap.get(operationString);
            Operation operation = factory.createOperation();
            ArrayList<Double> newArguments = new ArrayList<>();
            for (int i = 0; i < operation.arity; i++) {
                newArguments.add(parseExpr(s));
            }
            return operation.doIt(newArguments);
        }
    }
}
