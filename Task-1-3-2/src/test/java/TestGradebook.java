import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
public class TestGradebook {

    @ParameterizedTest
    @MethodSource ("presetGradebooks")
    public void testGradebooks(ArrayList<Gradebook> g){
        Gradebook gradebook = g.get(0); // my own gradebook
        assertFalse(gradebook.isExcellentDiploma());
        assertFalse(gradebook.isStipend(1)||gradebook.isStipend(2));
        assertEquals(gradebook.getAverage(), 4.083);

        gradebook = g.get(1); // excellent diploma without final paper
        assertTrue(gradebook.isStipend(1)&&gradebook.isStipend(2)&&
                gradebook.isStipend(3)&&gradebook.isStipend(4));
        assertThrows (NoSuchElementException.class, gradebook::isExcellentDiploma);
        assertEquals(4.8, gradebook.getAverage());

        gradebook = g.get(2); // excellent diploma with excellent final paper
        assertTrue(gradebook.isExcellentDiploma());
        assertEquals(4.810, gradebook.getAverage());

        gradebook = g.get(3); // excellent diploma with good final paper
        assertFalse(gradebook.isExcellentDiploma());
        assertEquals(gradebook.getAverage(), 4.762);
    }

    static ArrayList<ArrayList<Gradebook>> presetGradebooks(){
        ArrayList<Gradebook> res = new ArrayList<>();
        //Gradebook 1
        Gradebook myGradebook = new Gradebook();
        myGradebook.addSubject("MathAn");
        myGradebook.addMark("MathAn", 1, 4);
        myGradebook.addMark("MathAn", 2, 3);
        myGradebook.addSubject("Discrete");
        myGradebook.addMark("Discrete", 1, 5);
        myGradebook.addMark("Discrete", 2, 5);
        myGradebook.addSubject("Declarative");
        myGradebook.addMark("Declarative", 1, 3);
        myGradebook.addMark("Declarative", 2, 3);
        myGradebook.addSubject("Imperative");
        myGradebook.addMark("Imperative", 1, 5);
        myGradebook.addMark("Imperative", 2, 4);
        myGradebook.addSubject("English");
        myGradebook.addMark("English", 2, 4);
        myGradebook.addSubject("History");
        myGradebook.addMark("History", 1, 4);
        myGradebook.addSubject("OKR");
        myGradebook.addMark("OKR", 1, 5);
        myGradebook.addSubject("CP");
        myGradebook.addMark("CP", 2, 4);
        res.add(myGradebook);
        //Gradebook 2
        Gradebook n1 = new Gradebook();
        n1.addSubject("English");
        n1.addSubject("Business English");
        n1.addSubject("MathAn");
        n1.addSubject("Programming");
        n1.addSubject("Differential");
        n1.addSubject("Project");
        n1.addSubject("OS");
        n1.addSubject("Models");
        n1.addSubject("AI");
        n1.addMark("English", 1, 5);
        n1.addMark("English", 2, 5);
        n1.addMark("English", 3, 5);
        n1.addMark("Business English", 4, 5);
        n1.addMark("MathAn", 1, 5);
        n1.addMark("MathAn", 2, 5);
        n1.addMark("Programming", 1, 5);
        n1.addMark("Programming", 2, 5);
        n1.addMark("Programming", 3, 5);
        n1.addMark("Programming", 4, 5);
        n1.addMark("Differential", 3, 4);
        n1.addMark("Differential", 4, 5);
        n1.addMark("Project", 3, 4);
        n1.addMark("Project", 4, 5);
        n1.addMark("OS", 3,4);
        n1.addMark("OS", 4,4);
        n1.addMark("Models", 3,5);
        n1.addMark("Models", 4,5);
        n1.addMark("AI", 3, 5);
        n1.addMark("AI", 4, 5);
        // n1: 16/20 marks - excellent, no satisfactory, but no final work yet
        res.add(n1);
        Gradebook n2 = new Gradebook(n1);
        n2.addSubject("Final paper");
        n2.addMark("Final paper", 4, 5);
        // n2: 17/21 (76%) excellent, no satisfactory, final work - excellent -> excellent diploma
        res.add(n2);
        Gradebook n3 = new Gradebook(n1);
        n3.addSubject("Final paper");
        n3.addMark("Final paper", 4, 4);
        // n3: 16/21 excellent, no satisfactory, but no excellent final work -> no excellent diploma
        res.add(n3);
        ArrayList<ArrayList<Gradebook>> r = new ArrayList<>();
        r.add(res);
        return r;
    }
}
