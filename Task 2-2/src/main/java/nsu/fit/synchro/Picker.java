package nsu.fit.synchro;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Picker {
    public static Integer chooseNext(ArrayList<AtomicBoolean> frees, ArrayList<Integer> experiences) {
        int res = -1;
        int maxExp = 0;
        for (int i = 0; i < frees.size(); i++) {
            if (experiences.get(i) > maxExp && frees.get(i).get()) {
                maxExp = experiences.get(i);
                res = i;
            }
        }
        return res;
    }
}
