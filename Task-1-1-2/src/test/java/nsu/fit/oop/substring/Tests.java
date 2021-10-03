package nsu.fit.oop.substring;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    @ParameterizedTest
    @MethodSource ("searchTestGenerator")
    public void testSearchNormal(String filename, String query, ArrayList<Integer> expected) throws IOException {
        File file = new File(filename);
        FileReader r = new FileReader(file);
        assertEquals (expected, Substring.search(r,query));
    }

    static Stream<Arguments> searchTestGenerator(){
        ArrayList<Integer> expected1 = new ArrayList<>();
        expected1.add(18);
        expected1.add(43);
        ArrayList<Integer> expected2 = new ArrayList<>();
        expected2.add(7);
        expected2.add(21);
        ArrayList<Integer> expected3 = new ArrayList<>();
        expected3.add(8192);
        /*ArrayList<Integer> expected4 = new ArrayList<>();
        expected4.add(7);*/
        return Stream.of(
                Arguments.of("input.txt", "re ", expected1),
                Arguments.of("multiline.txt", "line", expected2),
                Arguments.of("bigdata.txt", "s", expected3)/*,
                Arguments.of("in.txt", "пирог",expected4)*/
        );
    }
    @ParameterizedTest
    @NullAndEmptySource
    public void testNullAndEmptyQuery(String query) throws FileNotFoundException {
        BufferedReader r = new BufferedReader(new FileReader("input.txt"));
        assertThrows(IllegalArgumentException.class, ()->Substring.search(r,query));
    }
    @ParameterizedTest
    @NullSource
    public void testNullReader(Reader r) {
        String q = "s";
        assertThrows(IllegalArgumentException.class, ()->Substring.search(r,q));
    }
}
