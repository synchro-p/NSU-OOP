package nsu.fit.synchro;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Info {
    Integer cooks;
    Integer couriers;
    ArrayList<Integer> exp;
    Integer capacity;

    public Info() {
    }

    public Info(Integer cooks, Integer couriers, ArrayList<Integer> exp) {
        this.cooks = cooks;
        this.couriers = couriers;
        this.exp = exp;
    }

    public Integer getCooks() {
        return cooks;
    }

    public Integer getCouriers() {
        return couriers;
    }

    public ArrayList<Integer> getExp() {
        return exp;
    }

    public Integer getCapacity() {
        return capacity;
    }
}
