import nsu.fit.synchro.PizzaWarehouse;

public class PizzaWarehouseTestConsumer implements Runnable {
    Integer iterations;
    PizzaWarehouse warehouse;
    public PizzaWarehouseTestConsumer(Integer iterations, PizzaWarehouse warehouse){
        this.iterations = iterations;
        this.warehouse = warehouse;
    }
    @Override
    public void run() {

        for (int i = 0; i < iterations; i++) {
            warehouse.getOrder(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
