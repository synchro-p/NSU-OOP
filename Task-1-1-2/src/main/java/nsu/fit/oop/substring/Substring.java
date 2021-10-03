package nsu.fit.oop.substring;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Substring {
    public static void main(String[] args) throws IOException {
        String input;
        Scanner sys = new Scanner(System.in);

        input = sys.nextLine();
        File file = new File(input);
        FileReader r = new FileReader(file);

        ArrayList<Integer> res;
        input = sys.nextLine();
        res = search(r,input);

        String output;
        output = res.toString();
        output = output. replace("[", "{"). replace("]", "}"). replaceAll(", ", ",");
        System.out.println(output);
    }
    /**
     * Searches for all occurrences of a String in a Reader and returns them as an ArrayList of indices, using the
     * Boyer-Moore-Horspool algorithm
     * @param r Reader to be used
     * @param query A string to search for
     * @return ArrayList of all indices from which the given string begins (starting with 0)
     * @throws IOException When an I/O error occurs while trying to update buffer
     */
    public static ArrayList<Integer> search(Reader r, String query) throws IOException {
        if (r == null || !r.ready()) throw new IllegalArgumentException("Bad input - unavailable or null reader");
        if (query == null || query.equals("")) throw new IllegalArgumentException("Bad input - null or empty string");
        ArrayList<Integer> res = new ArrayList<>();
        ArrayList<Character> buff = new ArrayList<>();
        HashMap<String, Integer> shiftMap;
        BufferedReader reader = new BufferedReader(r);
        shiftMap = createShiftMap(query);
        int skipped = 0;
        for (int i = 0; i<query.length(); i++){
            buff.add((char)reader.read());
        }
        while(true){
            int unequal = -1;
            for (int i = query.length()-1; i>=0; i--){
                if (buff.get(i) != query.charAt(i)){
                    unequal = i;
                    break;
                }
            }
            if (unequal == -1){
                res.add(skipped);
                skipped++;
                try{
                    buff = moveBuffer(buff, reader, 1);
                } catch(EOFException e){
                    break;
                }
            }
            else {
                char stopSymbol = buff.get(unequal);
                int move = getShiftFromMap(shiftMap, stopSymbol, query.length());
                skipped += move;
                try{
                    buff = moveBuffer(buff, reader, move);
                } catch(EOFException e){
                    break;
                }
            }
        }
        return res;
    }

    /**
     * Creates a HashMap which contains Boyer-Moore-Horspool shifts for each symbol in a string, as if it is
     * a pattern string for the said algorithm
     * @param s Pattern string for a Boyer-Moore-Horspool algorithm
     * @return HashMap, containing individual symbols as keys and their respective shifts as values
     */
    static HashMap<String,Integer> createShiftMap(String s){
        int l = s.length();
        HashMap<String,Integer> res = new HashMap<>();
        int i = 0;
        while (s.length()>1){
            char nextSymbol = s.charAt(0);
            res.put(Character.toString(nextSymbol),l-(i++)-1);
            s = s.substring(1);
        }
        return res;
    }

    /**
     * Gets a shift for a specified symbol from a shift map created earlier by createShiftMap method
     * @param map HashMap, containing 1-symbol Strings as keys and Integers (shifts for key symbols) as values
     * @param ss Symbol that the shift needs to be gotten for
     * @param plug Value to return if symbol is not in a shift table/not in a pattern string upon which the shift map
     *             was built (for correct execution, this needs to be equal to pattern String length)
     * @return Either a shift value for symbol received from a Map or plug if no such symbol is in the map's keys
     */
    static int getShiftFromMap(HashMap<String,Integer> map, char ss, int plug){
        if (!map.containsKey(Character.toString(ss))){
            return plug;
        }
        return map.get(Character.toString(ss));
    }

    /**
     * Gets new symbols from the buffered reader and updates the buffer accordingly to fit all the new symbols and
     * 1+ of the previous ones if it is needed
     * @param buffer ArrayList buffer, which gets updated
     * @param r BufferedReader, from which new symbols are received
     * @param shift how many new symbols have to be written in buffer
     * @return new value for buffer
     * @throws IOException If an I/O error occurs when trying to read a symbol
     */
    static ArrayList<Character> moveBuffer(ArrayList<Character> buffer, BufferedReader r, int shift)
            throws IOException {
        ArrayList<Character> bufferNew = new ArrayList<>();
        if (shift < buffer.size()){
            bufferNew.addAll(buffer.subList(shift, buffer.size()));
        }
        for (int i = 0; i<shift; i++){
            int c = r.read();
            if (c == -1){
                throw new EOFException();
            }
            bufferNew.add((char)c);
        }
        return bufferNew;
    }
}
