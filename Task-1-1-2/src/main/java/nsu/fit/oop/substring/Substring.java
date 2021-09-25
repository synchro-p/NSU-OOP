package nsu.fit.oop.substring;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Substring {
    public static void main(String[] args) {
        Scanner sys = new Scanner(System.in);
        String filename = sys.nextLine();
        String searchFor = sys.nextLine();
        String res = search(filename,searchFor);
        System.out.println(res);
    }

    public static String search(String filename, String query){
        if (filename == null) throw new IllegalArgumentException("null filename");
        if (filename.equals("")) throw new IllegalArgumentException("empty filename");
        if (query==null) throw new IllegalArgumentException("empty query");
        if (query.equals("")) throw new IllegalArgumentException("empty query");

        int l = query.length();
        int skipped = -1*l+1;
        char[] toCheck = new char[l];
        String res = "{";
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
                        res = res + skipped;
                        firstSymbol = false;
                    }
                    else {
                        res = res + ", " + skipped;
                    }
                }
                skipped++;
            }
            res = res+"}";
        } catch (FileNotFoundException e){
            throw new IllegalArgumentException("no such file in directory");
        }

        return res;
    }
}
