package nsu.fit.oop;

public class PlusFactory implements OperationFactory{
    public Operation createOperation() {
        return new Plus();
    }
}
