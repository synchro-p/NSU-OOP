class Subject {
    int[] r = new int[8];

    /**
     * Adds a mark to a particular semester. Overwrites previous mark assigned to that semester
     * @param semester a number from 1 to 8 representing the semester
     * @param mark a number from 2 to 5 representing the mark to add
     * @throws IllegalArgumentException if either semester or mark are out of given bounds
     */
    void addMark(int semester, int mark) {
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
    int getLast() {
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
    double getAvg() {
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
    int getBySemester(int semester) {
        return r[semester - 1];
    }

    /**
     * Counts all semesters for which mark is non-zero
     * @return amount of semesters for which the mark was given
     */
    int notNullSemesters() {
        int res = 0;
        for (int i : r) {
            if (i != 0) res++;
        }
        return res;
    }
}
