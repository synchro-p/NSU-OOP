import nsu.fit.synchro.PizzaWarehouse;

public class PizzaWarehouseTestProducer implements Runnable {
    Integer iterations;
    PizzaWarehouse warehouse;
    public PizzaWarehouseTestProducer(Integer iterations, PizzaWarehouse warehouse) {
        this.iterations = iterations;
        this.warehouse = warehouse;
    }

    @Override
    public void run(){
        for (int i = 0; i < iterations; i++) {
            warehouse.putOrder(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
