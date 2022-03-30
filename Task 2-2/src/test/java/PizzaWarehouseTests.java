import nsu.fit.synchro.PizzaWarehouse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

public class PizzaWarehouseTests {
    @Test
    public void capacityTestMoreProduced() throws InterruptedException {
        PizzaWarehouse warehouse = new PizzaWarehouse(20);
        PizzaWarehouseTestProducer producer = new PizzaWarehouseTestProducer(10, warehouse);
        PizzaWarehouseTestConsumer consumer = new PizzaWarehouseTestConsumer(10, warehouse);
        Thread producerThread = new Thread (producer);
        Thread producerThread2 = new Thread (producer);
        Thread consumerThread = new Thread (consumer);
        producerThread.start();
        producerThread2.start();
        consumerThread.start();
        Thread.sleep(11000);
        assertEquals(warehouse.getQueue().remainingCapacity(), 10);
    }
    @Test
    public void capacityTestMoreConsumed() throws InterruptedException {
        PizzaWarehouse warehouse = new PizzaWarehouse(20);
        PizzaWarehouseTestProducer producer = new PizzaWarehouseTestProducer(10, warehouse);
        PizzaWarehouseTestConsumer consumer = new PizzaWarehouseTestConsumer(10, warehouse);
        Thread producerThread = new Thread (producer);
        Thread consumerThread2 = new Thread (producer);
        Thread consumerThread = new Thread (consumer);
        producerThread.start();
        consumerThread2.start();
        consumerThread.start();
        Thread.sleep(11000);
        assertEquals(warehouse.getQueue().remainingCapacity(), 10);
    }
}
