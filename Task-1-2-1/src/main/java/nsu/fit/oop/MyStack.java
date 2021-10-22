package nsu.fit.oop;

import java.lang.reflect.Array;
import java.util.*;


public class MyStack<T> implements Iterable<T> {
    private final Class<?> tClass;
    private T[] array;
    private int size = 0;

    @Override
    public Iterator<T> iterator() {
        return (new Iterator<>() {
            final T[] arrayL = array;
            int sizeL = size;

            @Override
            public boolean hasNext() {
                return sizeL > 0;
            }

            @Override
            public T next() {
                if (sizeL <= 0) {
                    throw new NoSuchElementException();
                } else {
                    return arrayL[--sizeL];
                }
            }
        });
    }

    public MyStack(Class<?> t) {
        tClass = t;
        array = (T[]) Array.newInstance(t, 10);
    }

    /**
     * Equivalent to MyStack(Integer.class) - for convenience
     */
    public MyStack(){
        tClass = Integer.class;
        array = (T[]) Array.newInstance(Integer.class, 10);
    }

    /**
     * Pushes an Object into MyStack. If there is not enough space for a new element,
     * increases MyStack's size
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
     * Gets current size of stack (how many elements are in it)
     *
     * @return stack size
     */
    public int size() {
        return this.size;
    }

    /**
     * Deletes several elements from MyStack and returns them as another MyStack
     *
     * @param n how many elements to delete
     * @return MyStack, containing n deleted elements in order that they were initially
     */
    public MyStack<T> popStack(int n) {
        MyStack<T> s = new MyStack<>(tClass);
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
     * Pushes all elements from one MyStack to another, deleting them from the first one
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
