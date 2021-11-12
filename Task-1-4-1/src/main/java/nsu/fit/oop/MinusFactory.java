package nsu.fit.oop;

public class MinusFactory implements OperationFactory{
    public Operation createOperation(){
        return new Minus();
    }
}
