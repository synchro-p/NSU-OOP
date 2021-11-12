package nsu.fit.oop;

public class DivideFactory implements OperationFactory{
    public Operation createOperation() {
        return new Divide();
    }
}
