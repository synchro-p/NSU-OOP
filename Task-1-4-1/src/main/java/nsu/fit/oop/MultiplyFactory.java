package nsu.fit.oop;

public class MultiplyFactory implements OperationFactory{
    public Operation createOperation() {
        return new Multiply();
    }
}
