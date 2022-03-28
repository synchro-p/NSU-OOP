package nsu.fit.synchro;

import java.util.ArrayList;

public class Info {
    private ArrayList<Integer> cooksExp;
    private ArrayList<Integer> couriersTrunk;
    private ArrayList<Integer> couriersSpeed;
    private Integer capacity;

    public Info() {
    }

    public Info(ArrayList<Integer> cooksExp, ArrayList<Integer> couriersTrunk,
                ArrayList<Integer> couriersSpeed,Integer capacity) {
        this.couriersSpeed = couriersSpeed;
        this.couriersTrunk = couriersTrunk;
        this.cooksExp = cooksExp;
        this.capacity = capacity;
    }

    public ArrayList<Integer> getCouriersSpeed() {
        return couriersSpeed;
    }

    public ArrayList<Integer> getCouriersTrunk() {
        return couriersTrunk;
    }

    public ArrayList<Integer> getCooksExp() {
        return cooksExp;
    }

    public Integer getCapacity() {
        return capacity;
    }
}
