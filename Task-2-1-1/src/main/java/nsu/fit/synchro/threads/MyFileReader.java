package nsu.fit.synchro.threads;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyFileReader {
    /**
     * Reads all Integer numbers from file and writes them into an array
     *
     * @param input file to be scanned
     * @return ArrayList with Integers from input
     */
    public ArrayList<Integer> read(File input) {
        ArrayList<Integer> numbers = new ArrayList<>();
        try {
            Scanner inputScanner = new Scanner(input);
            while (inputScanner.hasNextInt()) {
                numbers.add(inputScanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return numbers;
    }
}
