package nsu.fit.oop.substringTests;

import nsu.fit.oop.substring.Substring;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    @ParameterizedTest
    @ValueSource (strings = {"inp.txt", "input", "inPutC.txt", "multi-line.txt"})
    void wrongFile(String string){
        boolean flag = false;
        try {
            Substring.search(string, "s");
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("no such file in directory")) {
                flag = true;
            }
        }
        assert (flag);
    }

    @Test
    void superLong(){
        assert(Substring.search("1.txt","s").equals("{8192}"));
    }

    @Test
    void multilineDivided(){
        assertEquals(Substring.search("multiline.txt","multiline"),"{}");
    }

    @Test
    void multilineNormal(){
        assertEquals("{6}",Substring.search("multiline.txt","line"));
    }
}
