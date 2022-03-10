package nsu.fit.synchro;

public class CourierExample implements Runnable {
    Warehouse warehouse;

    public CourierExample(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Courier free");
            Integer ordNum = warehouse.getOrder();
            if (ordNum == null) {
                return;
            }
            System.out.println("Courier started delivering order " + ordNum);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                return;
            }
            System.out.println("Courier delivered order " + ordNum);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
