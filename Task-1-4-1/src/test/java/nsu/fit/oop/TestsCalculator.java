package nsu.fit.oop;

import java.util.Scanner;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsCalculator {
    private static Stream<Arguments> getPresetExpressions(){
        return Stream.of(
                Arguments.of("sin + - 1 2 1", 0),
                Arguments.of("pow 2 5", 32),
                Arguments.of("cos * 3,1415926535 + 0,6 0,4", -1.0),
                Arguments.of("* 25 6",150)
        );
    }

    @ParameterizedTest
    @MethodSource("getPresetExpressions")
    public void testExpressions(String expr, double expected){
        assertEquals(expected,Calculator.parseExpr(new Scanner(expr)));
    }
}
