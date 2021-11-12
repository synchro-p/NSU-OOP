package nsu.fit.oop;

public class LogarithmFactory implements OperationFactory{
    @Override
    public Operation createOperation() {
        return new Logarithm();
    }
}
