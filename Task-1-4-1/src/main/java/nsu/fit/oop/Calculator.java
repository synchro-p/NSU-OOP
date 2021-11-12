package nsu.fit.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Calculator {
    static HashMap<String, OperationFactory> factoryHashMap = initHashMap();

    static HashMap<String, OperationFactory> initHashMap(){
        HashMap<String, OperationFactory> res = new HashMap<>();
        res.put("+", new PlusFactory());
        res.put("-", new MinusFactory());
        res.put("*", new MultiplyFactory());
        res.put("/", new DivideFactory());
        res.put("sin", new SinusFactory());
        res.put("cos", new CosinusFactory());
        res.put("pow", new PowerFactory());
        res.put("log", new LogarithmFactory());
        res.put("sqrt", new SquareRootFactory());
        return res;
    }

    static double parseExpr(Scanner s){
        if (s.hasNextDouble()){
            return s.nextDouble();
        }
        else {
            OperationFactory factory = factoryHashMap.get(s.next());
            Operation operation = factory.createOperation();
            ArrayList<Double> newArguments = new ArrayList<>();
            for (int i = 0; i < operation.arity; i++) {
                newArguments.add(parseExpr(s));
            }
            return operation.doIt(newArguments);
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println(parseExpr(s));
    }
}
