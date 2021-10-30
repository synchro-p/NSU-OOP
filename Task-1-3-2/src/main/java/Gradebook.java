import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Gradebook {
    /**
     *
     */
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
     * Inner class that stores all marks for a given subject (8 semesters)
     * in an integer array and implements methods to work with them
     */
    private static class Subject {
        int[] r = new int[8];

        /**
         * Adds a mark to a particular semester. Overwrites previous mark assigned to that semester
         * @param semester a number from 1 to 8 representing the semester
         * @param mark a number from 2 to 5 representing the mark to add
         * @throws IllegalArgumentException if either semester or mark are out of given bounds
         */
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

        /**
         * @return last non-zero mark from the array or zero
         */
        private int getLast() {
            for (int i = 7; i>=0; i--){
                if (r[i] != 0){
                    return r[i];
                }
            }
            return 0;
        }

        /**
         * @return average of all non-zero marks in the array or zero if no marks are present
         */
        private double getAvg() {
            int res = 0;
            int cnt = 0;
            for (int i : r) {
                res += i;
                if (i != 0) cnt++;
            }
            if (cnt == 0){
                return 0;
            }
            return (double) res / (double) cnt;
        }

        /**
         * Gets exactly a mark for the given semester from an array. If no mark was written there beforehand,
         * returns 0
         * @param semester a number from 1 to 8 representing which semester to get mark from
         * @return mark for that semester
         */
        private int getBySemester(int semester) {
            return r[semester - 1];
        }

        /**
         * Counts all semesters for which mark is non-zero
         * @return amount of semesters for which the mark was given
         */
        private int notNullSemesters() {
            int res = 0;
            for (int i : r) {
                if (i != 0) res++;
            }
            return res;
        }
    }

    private final HashMap<String, Subject> subjectMap;

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
