package nsu.fit.oop.substring;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    @ParameterizedTest
    @MethodSource("searchTestGenerator")
    public void testSearchNormal(String filename, String query, ArrayList<Integer> expected) throws IOException {
        File file = new File(filename);
        FileInputStream r = new FileInputStream(file);
        assertEquals(expected, Substring.search(r, query));
    }

    static Stream<Arguments> searchTestGenerator() {
        ArrayList<Integer> expected1 = new ArrayList<>(List.of(18, 43));
        ArrayList<Integer> expected2 = new ArrayList<>(List.of(7, 21));
        ArrayList<Integer> expected3 = new ArrayList<>(List.of(8192));
        ArrayList<Integer> expected4 = new ArrayList<>(List.of(7));
        String pirog = new String("пирог".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        return Stream.of(
                Arguments.of("input.txt", "re ", expected1),
                Arguments.of("multiline.txt", "line", expected2),
                Arguments.of("bigdata.txt", "s", expected3),
                Arguments.of("in.txt", pirog,expected4)
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void testNullAndEmptyQuery(String query) throws FileNotFoundException {
        FileInputStream r = new FileInputStream("input.txt");
        assertThrows(IllegalArgumentException.class, () -> Substring.search(r, query));
    }

    @ParameterizedTest
    @NullSource
    public void testNullReader(FileInputStream r) {
        String q = "s";
        assertThrows(IllegalArgumentException.class, () -> Substring.search(r, q));
    }
}
