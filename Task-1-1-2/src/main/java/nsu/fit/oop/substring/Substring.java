package nsu.fit.oop.substring;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Substring {
    public static void main(String[] args) {
        Scanner sys = new Scanner(System.in);
        String filename,searchFor;
        filename = sys.nextLine();
        searchFor = sys.nextLine();
        String res = search(filename,searchFor);
        System.out.println(res);
    }
    /**
     * Search for all occurrences of a given substring in given file
     * @param filename String that contains name of file where search is conducted
     * @param query the substring for search
     * @return string in format {r1,r2,r3...} with ri being an index of the first element
     * of the i-th occurrence of substring
     */
    public static String search(String filename, String query){
        if (filename == null) throw new IllegalArgumentException("null filename");
        if (filename.equals("")) throw new IllegalArgumentException("empty filename");
        if (query==null) throw new IllegalArgumentException("null query");
        if (query.equals("")) throw new IllegalArgumentException("empty query");

        int l = query.length();
        int skipped = -1*l+1;
        char[] toCheck = new char[l];
        StringBuilder res = new StringBuilder("{");
        boolean firstSymbol = true;
        File file = new File (filename);
        try {
            Scanner s = new Scanner(file);
            s.useDelimiter("");
            while (s.hasNext()){
                char next = s.next().charAt(0);
                if ((int)toCheck[l-1] > 0 && (int)toCheck[l-1] < 20 && (int)next > 0 && (int)next<20){
                    continue;
                }
                System.arraycopy(toCheck, 1, toCheck, 0, l - 1);
                toCheck[l-1] = next;
                if (Arrays.equals(query.toCharArray(), toCheck)){
                    if (firstSymbol){
                        res.append(skipped);
                        firstSymbol = false;
                    }
                    else {
                        res.append(", ").append(skipped);
                    }
                }
                skipped++;
            }
            res.append("}");
        } catch (FileNotFoundException e){
            throw new IllegalArgumentException("no such file in directory");
        }
        return res.toString();
    }
}
