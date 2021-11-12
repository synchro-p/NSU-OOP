import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Gradebook {
    private final HashMap<String, Subject> subjectMap;

    public Gradebook() {
        this.subjectMap = new HashMap<>();
    }

    /**
     * Creates a copy of a Gradebook passed as a parameter
     * @param another Gradebook to copy
     */
    public Gradebook(Gradebook another) {
        this.subjectMap = new HashMap<>(another.subjectMap);
    }

    /**
     * Adds a subject to the subject map
     * @param name the name of the subject
     */
    public void addSubject(String name) {
        subjectMap.put(name, new Subject());
    }

    /**
     * Adds a mark to the gradebook
     * @param subjectName the name of subject for which the mark needs to be added
     * @param semester the number of semester to add the mark
     * @param mark the mark to be placed
     */
    public void addMark(String subjectName, int semester, int mark) {
        subjectMap.get(subjectName).addMark(semester, mark);
    }

    /**
     * Shows whether student with this exact gradebook will have/had stipend in some semester
     * @param semester the number of semester to determine stipend receiving
     * @return true if stipend is given, false otherwise
     */
    public boolean isStipend(int semester) {
        ArrayList<Subject> collection = new ArrayList<>(subjectMap.values().stream().toList());
        for (Subject subject : collection) {
            if (subject.getBySemester(semester) < 4 && subject.getBySemester(semester) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get average of all marks in the gradebook
     * @return average of all marks
     */
    public double getAverage() {
        double res = 0;
        int cnt = 0;
        ArrayList<Subject> collection = new ArrayList<>(subjectMap.values().stream().toList());
        for (Subject s : collection) {
            res += s.getAvg() * s.notNullSemesters();
            cnt += s.notNullSemesters();
        }
        return Math.round(res / (double) cnt * 1000) / (double) 1000;
    }

    /**
     * Shows whether student with this gradebook is going to have an excellent diploma
     * @return true if student will have excellent diploma, false otherwise
     */
    public boolean isExcellentDiploma() {
        //нет итоговых троек + 75%
        int excellentCount = 0;
        int allCount = 0;
        ArrayList<Subject> collection = new ArrayList<>(subjectMap.values().stream().toList());
        for (Subject s : collection) {
            if (s.getLast() > 0 && s.getLast() < 3) {
                return false;
            }
            allCount++;
            if (s.getLast() == 5) {
                excellentCount++;
            }
        }
        if ((double) excellentCount / (double) allCount < 0.75) {
            return false;
        }
        //квалификационная на отл
        if (!subjectMap.containsKey("Final paper")) {
            throw new NoSuchElementException("No final paper present!");
        } else {
            return !(subjectMap.get("Final paper").getAvg() < 5);
        }
    }
}
