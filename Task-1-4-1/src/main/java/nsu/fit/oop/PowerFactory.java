package nsu.fit.oop;

public class PowerFactory implements OperationFactory{
    public Operation createOperation() {
        return new Power();
    }
}
