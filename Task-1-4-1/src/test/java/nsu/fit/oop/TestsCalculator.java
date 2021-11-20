package nsu.fit.oop;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Scanner;
import java.util.stream.Stream;

import static nsu.fit.oop.Calculator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestsCalculator {
    private static Stream<Arguments> getPresetExpressions(){
        return Stream.of(
                Arguments.of("sin + - 1 2 1", 0),
                Arguments.of("pow 2 5", 32),
                Arguments.of("cos * 3,1415926535 + 0,6 0,4", -1.0),
                Arguments.of("/ 25 5",5),
                Arguments.of("log 2,71828182845904523536",1),
                Arguments.of("sqrt 144", 12)
        );
    }

    @ParameterizedTest
    @MethodSource("getPresetExpressions")
    public void testExpressions(String expr, double expected){
        addDefault();
        assertEquals(expected,Calculator.parseExpr(new Scanner(expr)));
    }

    static Stream<String> getBadInputs(){
        return Stream.of("sh 0",
                "",
                "cos + 0",
                "that is not a calculator string",
                "a10"
                );
    }

    @ParameterizedTest
    @MethodSource("getBadInputs")
    public void testBadInput(String s) {
        addDefault();
        assertThrows(IllegalArgumentException.class, () -> parseExpr(new Scanner(s)));
    }
}
