package nsu.fit.oop.substring;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Substring {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sys = new Scanner(System.in);
        String filename = sys.nextLine();
        String searchFor = sys.nextLine();
        String res = search(filename,searchFor);
        System.out.println(res);
    }

    /**
     * Search for all occurrences of a given substring in given file
     * @param filename String that contains name of file where search is conducted
     * @param query the substring for search for
     * @return string in format {r1,r2,r3...} with ri being an index of the first element
     * of the i-th occurrence of substring
     */
    public static String search(String filename, String query) throws FileNotFoundException {
        if (filename == null) throw new IllegalArgumentException("null filename");
        if (filename.equals("")) throw new IllegalArgumentException("empty filename");
        if (query==null) throw new IllegalArgumentException("empty query");
        if (query.equals("")) throw new IllegalArgumentException("empty query");

        int skipped = 0;
        File file = new File (filename);
        try {
            Scanner scanner = new Scanner(file);
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("no such file in directory");
        }
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("");

        StringBuilder s = new StringBuilder();
        s.append("{");
        while (scanner.hasNext()){
            char c = scanner.next().charAt(0);
            if (c != query.charAt(0)){
                skipped++;
                continue;
            }
            int coinciding = 1;
            for (int i = 1; i<query.length()&& scanner.hasNext(); i++){
                c = scanner.next().charAt(0);
                if  (c == query.charAt(i)){
                    coinciding++;
                }
            }

        }
        s.append("}");
        String str = s.toString();
        return str;
    }
}
