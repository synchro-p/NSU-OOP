package nsu.fit.oop;

public class SinusFactory implements OperationFactory{

    @Override
    public Operation createOperation() {
        return new Sinus();
    }
}
