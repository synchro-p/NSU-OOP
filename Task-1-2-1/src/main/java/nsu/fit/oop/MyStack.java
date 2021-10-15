package nsu.fit.oop;

import java.util.Arrays;
import java.util.EmptyStackException;

public class MyStack<T> {
    private T[] array = (T[]) new Object[10];
    private int size = 0;

    /**
     * Pushes an Object into Stack. If there is not enough space for a new element,
     * increases Stack's size
     *
     * @param a Object to be pushed
     */
    public void push(T a) {
        resizeIfNeeded();
        array[size++] = a;
    }

    /**
     * Deletes the Object that was pushed last from stack
     *
     * @return deleted Object
     */
    public T pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return array[--size];
    }

    /**
     * Gets current size of stack (how many elements are in it) and prints it into console
     *
     * @return stack size
     */
    public int size() {
        System.out.println("size equals " + this.size);
        return this.size;
    }

    /**
     * Deletes several elements from Stack and returns them another Stack
     *
     * @param n how many elements to delete
     * @return Stack, containing n deleted elements in order that they were initially
     */
    public MyStack<T> popStack(int n) {
        MyStack<T> s = new MyStack<>();
        T[] nArr = (T[]) new Object[this.size];
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
    public void pushStack(MyStack<T> s) {
        int l = s.size();
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
        if (array.length == size) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }
}
