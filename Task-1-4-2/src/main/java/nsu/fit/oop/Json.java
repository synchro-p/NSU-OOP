package nsu.fit.oop;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Json {
    static private ArrayList<Entry> entryList;
    static ObjectMapper objectMapper = new ObjectMapper();
    static File filename = Paths.get("notebook.json").toFile();

    static void removeEntry (String entryName) {
        refresh();
        entryList.removeIf(i -> Objects.equals(i.getName(), entryName));
        writeToFile();
    }

    static void addEntry(String entryName, String content) {
        Entry e = new Entry(entryName, content);
        refresh();
        entryList.add(e);
        writeToFile();
    }

    static void showAll(){
        refresh();
        for (Entry i : entryList) {
            printEntry(i);
        }
    }

    static void showFiltered(LocalDateTime lower, LocalDateTime upper, ArrayList<String> nameFilters) {
        refresh();
        for (Entry i : entryList) {
            if (i.getCreationDateTime().isAfter(upper) || i.getCreationDateTime().isBefore(lower)) {
                continue;
            }
            for (String filter : nameFilters) {
                if (i.getName().contains(filter)) {
                    printEntry(i);
                }
            }
        }
    }

    private static void refresh(){
        try {
            entryList = new ArrayList<>(Arrays.asList(objectMapper.readValue(filename, Entry[].class)));
        } catch (IOException e) {
            entryList = new ArrayList<>();
        }
    }

    private static void writeToFile(){
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filename, entryList);
        } catch (IOException e) {
            System.out.println("Unsuccessful write");
        }
    }

    private static void printEntry(Entry i) {
        System.out.println("Name: "+i.getName());
        System.out.println("Content: "+i.getContent());
        System.out.println("Created: "+i.getCreationDateTime()+"\n");
    }
}
