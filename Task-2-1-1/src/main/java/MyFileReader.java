import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// BufferedReader??
public class MyFileReader {
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
