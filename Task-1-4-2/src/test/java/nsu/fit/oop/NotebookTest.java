package nsu.fit.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.*;

public class NotebookTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setup() {
        Path notebook = Paths.get("write.json");
        try {
            Files.newBufferedWriter(notebook, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testWritingAndShowAll() {
        String[] args = {"-add", "someName", "someContents", "-f=write"};
        new CommandLine(new Process()).execute(args);
        String[] argsShow = {"-show", "-f=write"};
        new CommandLine(new Process()).execute(argsShow);
        assertTrue(outContent.toString().contains("Name: someName"));
        assertTrue(outContent.toString().contains("Content: someContents"));
        assertTrue(outContent.toString().contains("Created: "));
    }

    @Test
    public void testRemovalAndShowAll() {
        String[] args = {"-add", "someName", "someContents", "-f=write"};
        new CommandLine(new Process()).execute(args);
        new CommandLine(new Process()).execute(args);
        String[] args2 = {"-rm", "someName", "-f=write"};
        new CommandLine(new Process()).execute(args2);
        String[] argsShow = {"-show", "-f=write"};
        new CommandLine(new Process()).execute(argsShow);
        assertEquals(outContent.toString(), "");
    }

    @Test
    public void testShowOneArg() {
        String[] args = {"-show", "12.08.2020 00:00", "-f=testfile1"};
        new CommandLine(new Process()).execute(args);
        assertEquals(outContent.toString(), "");
        String[] args2 = {"-show", "11.08.2020 00:00", "-f=testfile1"};
        new CommandLine(new Process()).execute(args2);
        assertTrue(outContent.toString().contains("Name: someName"));
    }

    @Test
    public void testShowFiltered() {
        String[] args = {"-show", "01.01.1970 00:00", "01.01.2077 23:59", "z", "-f=testfile2"};
        new CommandLine(new Process()).execute(args);
        assertEquals(outContent.toString(), "");
        String[] args2 = {"-show", "01.01.1970 00:00", "02.01.2077 00:01", "z", "-f=testfile2"};
        new CommandLine(new Process()).execute(args2);
        assertNotEquals(outContent.toString(), "");
    }
}
