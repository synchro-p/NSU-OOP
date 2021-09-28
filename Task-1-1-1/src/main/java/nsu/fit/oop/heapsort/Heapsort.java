package nsu.fit.oop.heapsort;

import java.util.*;
import java.lang.*;

import static java.lang.Integer.parseInt;

public class Heapsort {

    public static void main(String[] args) {
        Scanner sys = new Scanner (System.in);
        String inp;
        inp = sys.next();

        Integer[] arr = parseToArray(inp);
        heapsort(arr);

        String res = Arrays.toString(arr);
        res = res.substring(1,res.length()-1);
        res = "{" + res + "}";
        res = res.replaceAll(" ","");
        System.out.println(res);
    }

    /**
     * Parses a string in format {a1,..an} into an Integer[]
     * @param inp Parsed string
     * @return Integer[] with values from string
     * @throws IllegalArgumentException Invalid arguments or formatting
     */
    public static Integer[] parseToArray(String inp){
        if (inp == null || inp.length()==0){
            throw new IllegalArgumentException("Bad input - null/empty string");
        }
        if (inp.charAt(0) != '{' || inp.charAt(inp.length()-1) != '}'){
            throw new IllegalArgumentException("Bad input - no curly braces on ends");
        }
        inp = inp.substring(1,inp.length()-1);
        if (inp.charAt(0) == ',' || !Character.isDigit(inp.charAt(inp.length()-1))){
            throw new IllegalArgumentException("Bad input - wrong delimiter or value");
        }
        Scanner ints = new Scanner (inp);
        ints.useDelimiter (",");

        ArrayList<Integer> array = new ArrayList<>();
        try{
            while (ints.hasNext()){
                array.add(parseInt(ints.next()));
            }
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Bad input - wrong delimiter or value");
        }
        return array.toArray (new Integer [0]);
    }

    /**
     * Heapsort for an Integer[] array
     * @param arr array to be sorted
     */
    public static void heapsort(Integer[] arr){
        if (arr == null) throw new IllegalArgumentException("null array");
        heapBuild (arr);
        int end = arr.length-1;
        while (end>=0){
            swap (arr, end, 0);
            end--;
            heapify(arr,0,end);
        }
    }

    /**
     * Builds a max-heap from a given Integer[] array
     * Root element is at index 0, children of i-th element are 2*i and 2*i+1
     * @param arr Integer[] array from which the max-heap is built
     */
    private static void heapBuild(Integer[] arr){
        for (int i = arr.length/2; i>=0; i--){
            heapify(arr, i, arr.length-1);
        }
    }

    /**
     * Restores max-heap properties in an array
     * @param arr Integer[] array where max-heap properties could have been violated
     * @param root the biggest element in which properties should be restored
     * @param end the last current element of max-heap
     */
    private static void heapify(Integer[] arr, int root, int end){
        int left = 2*root;
        int right = 2*root+1;
        int largest = root;

        if (left<=end && arr[left]>arr[largest]){
            largest = left;
        }
        if (right<=end && arr[right]>arr[largest]){
            largest = right;
        }
        if (largest != root){
            swap(arr, largest, root);
            heapify(arr, largest, end);
        }
    }

    /**
     * swaps two elements of an Integer[] array using a temporary variable
     * @param a array form which elements are taken
     * @param i index of the first element to swap
     * @param j index of the second element to swap
     */
    static void swap (Integer[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
