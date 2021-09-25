package nsu.fit.oop.substringTests;

import nsu.fit.oop.substring.Substring;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    @ParameterizedTest
    @ValueSource (strings = {"inp.txt", "input", "inPutC.txt", "multi-line.txt"})
    void testWrongFile(String string){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Substring.search(string, " "));
        assertEquals(e.getMessage(), "no such file in directory");
    }

    private static Stream<Arguments> giveVariousHardSituations(){
        return Stream.of(
                Arguments.of("bigdata.txt", "s", "{8192}"),
                Arguments.of("multiline.txt", "multiline", "{}"),
                Arguments.of("multiline.txt", "line", "{6}")
        );
    }
    @ParameterizedTest
    @MethodSource ("giveVariousHardSituations")
    void testHards(String file, String query, String expected){
        assertEquals(expected, Substring.search(file,query));
    }
}
