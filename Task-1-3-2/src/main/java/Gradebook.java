import java.util.ArrayList;
import java.util.HashMap;

public class Gradebook {
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

        /*private int getLast(){
            int res = 0;
            for (int i = 0; i<8; i++){
                if (r[i] != 0){
                    res = r[i];
                }
            }
            return res;
        }*/
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

    private final HashMap<String, Subject> subjectMap = new HashMap<>();

    public void addSubject(String name) {
        subjectMap.put(name, new Subject());
    }

    public void addMark(String subjectName, int semester, int mark) {
        subjectMap.get(subjectName).addMark(semester, mark);
    }

    public boolean isRaisedStipend(int semester) {
        boolean res = true;
        ArrayList<Subject> collection = new ArrayList<>(subjectMap.values().stream().toList());
        for (Subject subject : collection) {
            if (subject.getBySemester(semester) < 4 && subject.getBySemester(semester) != 0) {
                res = false;
                break;
            }
        }
        return res;
    }

    public double getAverage() {
        double res = 0;
        int cnt = 0;
        ArrayList<Subject> collection = new ArrayList<>(subjectMap.values().stream().toList());
        for (Subject s : collection) {
            res += s.getAvg() * s.notNullSemesters();
            cnt += s.notNullSemesters();
        }
        return res / (double) cnt;
    }

    public static void main(String[] args) {
        Gradebook myGradebook = new Gradebook();
        myGradebook.addSubject("MathAn");
        myGradebook.addMark("MathAn", 1, 4);
        myGradebook.addMark("MathAn", 2, 3);
        myGradebook.addSubject("Discrete");
        myGradebook.addMark("Discrete", 1, 5);
        myGradebook.addMark("Discrete", 2, 5);
        myGradebook.addSubject("Declarative");
        myGradebook.addMark("Declarative", 1, 3);
        myGradebook.addMark("Declarative", 2, 3);
        myGradebook.addSubject("Imperative");
        myGradebook.addMark("Imperative", 1, 5);
        myGradebook.addMark("Imperative", 2, 4);
        myGradebook.addSubject("English");
        myGradebook.addMark("English", 2, 4);
        myGradebook.addSubject("History");
        myGradebook.addMark("History", 1, 4);
        myGradebook.addSubject("OKR");
        myGradebook.addMark("OKR", 1, 5);
        myGradebook.addSubject("Computational");
        myGradebook.addMark("Computational", 2, 4);

        System.out.println(myGradebook.isRaisedStipend(1));
        System.out.println(myGradebook.isRaisedStipend(2));
        System.out.printf("%.3f",myGradebook.getAverage());
    }
}
