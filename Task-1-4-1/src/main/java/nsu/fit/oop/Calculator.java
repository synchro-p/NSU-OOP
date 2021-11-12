package nsu.fit.oop;

import java.util.Scanner;

public class Calculator {
    static double parseExpression(Scanner s){
        if (!s.hasNextDouble()) {
            String operation = s.next();
            switch (operation) {
                case "+":
                    return parseExpression(s)+parseExpression(s);
                case "-":
                    return parseExpression(s)-parseExpression(s);
                case "*":
                    return parseExpression(s)*parseExpression(s);
                case "/":
                    return parseExpression(s)/parseExpression(s);
                case "pow":
                    return Math.pow(parseExpression(s),parseExpression(s));
                case "sqrt":
                    return Math.sqrt(parseExpression(s));
                case "sin":
                    return Math.sin(parseExpression(s));
                case "cos":
                    return Math.cos(parseExpression(s));
                case "log":
                    return Math.log(parseExpression(s));
            }
        }
        else {
            return s.nextDouble();
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        double res = parseExpression(s);
        System.out.println(res);
    }
}
