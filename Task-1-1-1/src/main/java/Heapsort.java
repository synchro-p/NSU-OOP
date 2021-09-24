import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.*;

import static java.lang.Integer.parseInt;

public class Heapsort {

    public static void main(String[] args) {
        Scanner sys = new Scanner (System.in);

        String inp;

        inp = sys.next();

        Integer[] arr = heapsort(inp);

        String res = Arrays.toString(arr);
        res = res.substring(1,res.length()-1);

        res = "{" + res + "}";
        res = res.replaceAll(" ","");
        System.out.println(res);
    }

    static Integer[] heapsort(String inp){
        if (inp.charAt(0) != '{' || inp.charAt(inp.length()-1) != '}'){
            throw new IllegalArgumentException("no curly braces");
        }
        inp = inp.substring(1,inp.length()-1);
        Scanner ints = new Scanner (inp);
        ints.useDelimiter(",");

        ArrayList<Integer> array = new ArrayList<>();

        try{
            while (ints.hasNext()){
                array.add(parseInt(ints.next()));
            }
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Bad input - wrong delimiter or value");
        }

        Integer[] arr = array.toArray(new Integer[0]);


        heapBuild (arr);
        int end = arr.length-1;
        while (end>=0){
            int t=arr[0];
            arr[0] = arr[end];
            arr[end] = t;
            end--;
            heapify(arr,0,end);
        }
        return arr;
    }

    static void heapBuild(Integer[] arr){
        for (int i = arr.length/2; i>=0; i--){
            heapify(arr, i, arr.length-1);
        }
    }

    static void heapify(Integer[] arr, int root, int end){
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
            int t = arr[root];
            arr[root] = arr[largest];
            arr[largest] = t;
            heapify(arr, largest, end);
        }
    }
}
