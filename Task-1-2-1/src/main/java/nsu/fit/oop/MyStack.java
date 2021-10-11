package nsu.fit.oop;

import java.util.Arrays;

public class MyStack<T> {
    private T[] array = (T[]) new Object[10];
    private int size = 10;
    private int count = 0;
    
    /**
     * Pushes an Object into Stack. If there is not enough space for a new element,
     * increases Stack's size
     *
     * @param a Object to be pushed
     */
    public void push(T a) {
        resizeIfNeeded();
        array[count++] = a;
    }

    /**
     * Deletes the Object that was pushed last from stack
     *
     * @return deleted Object
     */
    public T pop() {
        if (count == 0) {
            return null;
        }
        return array[--count];
    }

    /**
     * Gets current size of stack (how many elements are in it) and prints it into console
     *
     * @return stack size
     */
    public int getCount() {
        System.out.println("count equals "+this.count);
        return this.count;
    }

    /**
     * Deletes several elements from Stack and returns them another Stack
     *
     * @param n how many elements to delete
     * @return Stack, containing n deleted elements in order that they were initially
     */
    public MyStack<T> popS(int n) {
        MyStack<T> s = new MyStack<>();
        T[] nArr = (T[]) new Object[this.count];
        for (int i = n - 1; i >= 0; i--) {
            nArr[i] = this.pop();
        }
        for (int i = 0; i < n; i++) {
            s.push(nArr[i]);
        }
        return s;
    }

    /**
     * Pushes all elements from one Stack to another, deleting them
     *
     * @param s Stack to receive elements from
     */
    public void pushS(MyStack<T> s) {
        int l = s.getCount();
        T[] nArr = (T[]) new Object[l];
        for (int i = l - 1; i >= 0; i--) {
            nArr[i] = s.pop();
        }
        for (int i = 0; i < l; i++) {
            this.push(nArr[i]);
        }
    }

    /**
     * Doubles the current capacity of elements if it is required
     */
    private void resizeIfNeeded() {
        if (size == count) {
            size = size * 2;
            array = Arrays.copyOf(array, size);
        }
    }


    public static void main(String[] args) {
        MyStack<Integer> s = new MyStack<>();
        s.push(2);
        s.push(7);
        MyStack<Integer> add = new MyStack<>();
        add.push(4);
        add.push(8);
        s.pushS(add);
        s.pop();
        s.popS(2);
        s.getCount();
        System.out.println(s.pop());
    }
}
