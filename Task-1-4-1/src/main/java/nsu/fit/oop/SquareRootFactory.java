package nsu.fit.oop;

public class SquareRootFactory implements OperationFactory{
    @Override
    public Operation createOperation() {
        return new SquareRoot();
    }
}
