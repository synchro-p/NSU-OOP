
import nsu.fit.oop.MyStack;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MyStackTests {
    @ParameterizedTest
    @MethodSource("presetPrimitiveTest")
    public void testPrimitive(ArrayList<Integer> input) {
        MyStack<Integer> r = new MyStack<>();
        for (Integer integer : input) {
            r.push(integer);
        }
        assertEquals(r.size(), input.size());
        for (int i = input.size() - 1; i >= 0; i--) {
            int check = r.pop();
            assertEquals(check, input.get(i));
        }
    }

    public static Stream<Arguments> presetPrimitiveTest() {
        ArrayList<Integer> a = new ArrayList<>(List.of(1, 3, 15, 34643, 256, 798));
        ArrayList<Integer> b = new ArrayList<>(List.of(-1233, 783, 9165, 123));
        ArrayList<Integer> c = new ArrayList<>(List.of(981, -9, 1823, 256));
        return Stream.of(
                Arguments.of(a),
                Arguments.of(b),
                Arguments.of(c)
        );
    }

    @Test
    public void testPopFromEmpty() {
        MyStack<Object> a = new MyStack<>(Integer.class);
        assertEquals(0, a.size());
        assertThrows(EmptyStackException.class, a::pop);
    }

    @ParameterizedTest
    @MethodSource("createTwoStacks")
    public void testTwoStacksConversion(MyStack<Integer> s1, MyStack<Integer> s2) {
        MyStack<Integer> a = new MyStack<>(Integer.class);
        int size = s1.size() + s2.size();
        a.pushStack(s1);
        a.pushStack(s2);
        assertEquals(size, a.size());
    }

    public static ArrayList<Arguments> createTwoStacks() {
        ArrayList<Arguments> res = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            Random r = new Random();
            int size1 = r.nextInt(10000);
            int size2 = r.nextInt(10000);
            MyStack<Integer> s1 = new MyStack<>(Integer.class);
            MyStack<Integer> s2 = new MyStack<>(Integer.class);
            for (int j = 0; j < size1; j++) {
                s1.push(r.nextInt());
            }
            for (int j = 0; j < size2; j++) {
                s2.push(r.nextInt());
            }
            res.add(Arguments.of(s1, s2));
        }
        return res;
    }

    @Test
    public void testPopStack() {
        MyStack<Integer> s = new MyStack<>(Integer.class);
        s.push(12);
        s.push(25);
        s.push(3492034);
        MyStack<Integer> rec = s.popStack(2);
        assertEquals(rec.size(), 2);
        assertEquals(rec.pop(), 3492034);
        assertEquals(rec.pop(), 25);
    }

    @Test
    public void testTypeDiversity(){
        MyStack<String> s1 = new MyStack<>(String.class);
        MyStack<String> s2 = new MyStack<>(String.class);
        s1.push("Some");
        s1.push("sort");
        s1.push("of");
        s1.push("sentence");
        s2.push("Not much here");
        assertEquals(s2.pop(), "Not much here");
        s2 = s1.popStack(2);
        assertEquals(s2.pop(), "sentence");
        assertEquals(s2.pop(), "of");
        assertEquals(s1.pop(), "sort");
    }

    @Test
    public void testIterable(){
        MyStack<Integer> s = new MyStack<>();
        s.push(1);
        s.push(2);
        s.push(1212);
        s.push(-123894);
        ArrayList<Integer> iterableArray = new ArrayList<>();
        ArrayList<Integer> popArray = new ArrayList<>();
        for (Integer i : s){
            iterableArray.add(i);
        }
        while (s.size()>0){
            popArray.add(s.pop());
        }
        assertEquals(iterableArray,popArray);
    }
}