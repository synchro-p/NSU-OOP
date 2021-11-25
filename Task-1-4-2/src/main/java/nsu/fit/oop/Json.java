package nsu.fit.oop;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

public class Json {
    private ArrayList<Entry> entryList;
    private final ObjectMapper objectMapper = new ObjectMapper();
    File filename;

    public Json(String file) {
        this.filename = Paths.get(file + ".json").toFile();
    }

    public Json() {
        this.filename = Paths.get("notebook.json").toFile();
    }

    void removeEntry(String entryName) {
        refresh();
        entryList.removeIf(i -> Objects.equals(i.getName(), entryName));
        writeToFile();
    }

    void addEntry(String entryName, String content) {
        Entry e = new Entry(entryName, content, LocalDateTime.now());
        refresh();
        entryList.add(e);
        writeToFile();
    }

    void showAll() {
        refresh();
        for (Entry i : entryList) {
            printEntry(i);
        }
    }

    void showFiltered(LocalDateTime lower, LocalDateTime upper, ArrayList<String> nameFilters) {
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

    private void refresh() {
        try {
            entryList = new ArrayList<>(Arrays.asList(objectMapper.readValue(filename, Entry[].class)));
        } catch (IOException e) {
            entryList = new ArrayList<>();
        }
    }

    private void writeToFile() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filename, entryList);
        } catch (IOException e) {
            System.out.println("Unsuccessful write");
        }
    }

    private void printEntry(Entry i) {
        System.out.println("Name: " + i.getName());
        System.out.println("Content: " + i.getContent());
        System.out.println("Created: " + i.getCreationDateTime() + "\n-------");
    }

    void clear() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filename, "");
        } catch (IOException e) {
            System.out.println("Unsuccessful write");
        }
    }
}
