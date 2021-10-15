
import nsu.fit.oop.MyStack;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MyStackTests {
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

    @Test
    public void testPopFromEmpty() {
        MyStack<Object> a = new MyStack<>();
        assertEquals(0, a.size());
        assertNull(a.pop());
    }

    @ParameterizedTest
    @MethodSource("createTwoStacks")
    public void testTwoStacksConversion(MyStack<Integer> s1, MyStack<Integer> s2) {
        MyStack<Integer> a = new MyStack<>();
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
            MyStack<Integer> s1 = new MyStack<>();
            MyStack<Integer> s2 = new MyStack<>();
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
}