package nsu.fit.synchro;

import java.util.concurrent.atomic.AtomicBoolean;

public class CookExample implements Runnable {
    private final AtomicBoolean isFree;
    private final Integer exp;
    private final Integer id;
    private final PizzaWarehouse warehouse;
    private Integer order;

    public CookExample(boolean isFree, Integer exp, PizzaWarehouse warehouse, Integer id) {
        this.isFree = new AtomicBoolean(isFree);
        this.warehouse = warehouse;
        this.id = id;
        this.exp = exp;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public AtomicBoolean isFree() {
        return isFree;
    }

    public Integer getExp() {
        return exp;
    }

    @Override
    public void run() {
        System.out.println("Cook " + this.id + " started order " + this.order);
        try {
            Thread.sleep(30000 / exp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Cook " + this.id + " finished order " + this.order);
        warehouse.putOrder(order);
        System.out.println("Cook " + this.id + " stored order " + this.order);
        isFree.set(true);
    }
}
