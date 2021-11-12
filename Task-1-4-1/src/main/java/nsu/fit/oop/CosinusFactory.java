package nsu.fit.oop;

public class CosinusFactory implements OperationFactory{
    @Override
    public Operation createOperation() {
        return new Cosinus();
    }
}
