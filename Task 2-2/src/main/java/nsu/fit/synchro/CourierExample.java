package nsu.fit.synchro;

public class CourierExample implements Runnable{
    Warehouse warehouse;

    public CourierExample(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
    @Override
    public void run(){
        int cnt = 0;
        while (cnt < 5) {
            System.out.println("Courier init");
            Integer ordNum = warehouse.getOrder();
            System.out.println("Courier started delivering order " + ordNum);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Courier delivered order " + ordNum);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Courier free");
            cnt++;
        }
        System.out.println("Courier gone");
    }
}
