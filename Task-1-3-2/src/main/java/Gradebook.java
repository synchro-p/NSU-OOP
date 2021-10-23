import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Gradebook {
    public Gradebook(){
        this.subjectMap = new HashMap<>();
    }
    public Gradebook(Gradebook another){
        this.subjectMap = new HashMap<>(another.subjectMap);
    }

    private static class Subject {
        int[] r = new int[8];

        private void addMark(int semester, int mark) {
            if (mark < 2 || mark > 5) {
                throw new IllegalArgumentException("illegal mark");
            }
            semester--;
            if (semester < 0 || semester > 7) {
                throw new IllegalArgumentException("illegal semester");
            }
            r[semester] = mark;
        }

        private int getLast(){
            int res = 0;
            for (int i = 0; i<8; i++){
                if (r[i] != 0){
                    res = r[i];
                }
            }
            return res;
        }
        private double getAvg() {
            int res = 0;
            int cnt = 0;
            for (int i : r) {
                res += i;
                if (i != 0) cnt++;
            }
            return (double) res / (double) cnt;
        }

        private int getBySemester(int semester) {
            return r[semester-1];
        }

        private int notNullSemesters() {
            int res = 0;
            for (int i : r) {
                if (i != 0) res++;
            }
            return res;
        }
    }

    private final HashMap<String, Subject> subjectMap;

    public void addSubject(String name) {
        subjectMap.put(name, new Subject());
    }

    public void addMark(String subjectName, int semester, int mark) {
        subjectMap.get(subjectName).addMark(semester, mark);
    }

    public boolean isStipend(int semester) {
        ArrayList<Subject> collection = new ArrayList<>(subjectMap.values().stream().toList());
        for (Subject subject : collection) {
            if (subject.getBySemester(semester) < 4 && subject.getBySemester(semester) != 0) {
                return false;
            }
        }
        return true;
    }

    public double getAverage() {
        double res = 0;
        int cnt = 0;
        ArrayList<Subject> collection = new ArrayList<>(subjectMap.values().stream().toList());
        for (Subject s : collection) {
            res += s.getAvg() * s.notNullSemesters();
            cnt += s.notNullSemesters();
        }
        return Math.round(res/(double)cnt * 1000)/(double)1000;
    }

    public boolean isExcellentDiploma() {
        //нет итоговых троек + 75%
        int excellentCount = 0;
        int allCount = 0;
        ArrayList<Subject> collection = new ArrayList<>(subjectMap.values().stream().toList());
        for (Subject s : collection) {
            if (s.getLast()>0 && s.getLast()<3){
                return false;
            }
            allCount++;
            if (s.getLast() == 5){
                excellentCount++;
            }
        }
        if ((double)excellentCount/(double)allCount < 0.75){
            return false;
        }
        //квалификационная на отл
        if (!subjectMap.containsKey("Final paper")){
            throw new NoSuchElementException("No final paper present!");
        }
        else {
            return !(subjectMap.get("Final paper").getAvg() < 5);
        }
    }
}
