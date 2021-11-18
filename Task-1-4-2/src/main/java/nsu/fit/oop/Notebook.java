package nsu.fit.oop;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

@Command(name = "notebook")
class Process implements Callable<Integer> {
    @Override
    public Integer call() {
        if (addArgs != null) {
            Json.addEntry(addArgs[0],addArgs[1]);
        }
        if (removeName != null) {
            Json.removeEntry(removeName);
        }
        if (showArguments != null) {
            if (showArguments.length < 2) {
                Json.showAll();
            }
            else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                LocalDateTime lower = LocalDateTime.parse(showArguments[0], formatter);
                LocalDateTime upper = LocalDateTime.parse(showArguments[1], formatter);
                ArrayList<String> filters = new ArrayList<>(Arrays.asList(
                        Arrays.copyOfRange(showArguments, 2, showArguments.length - 1)));
                Json.showFiltered(lower, upper, filters);
            }
        }
        return 0;
    }
    @Option(arity = "2..2", names = "-add")
    String[] addArgs;

    @Option(arity = "1", names = "-rm")
    String removeName;

    @Option(arity = "0..*", names = "-show")
    String[] showArguments;
}

public class Notebook {
    public static void main(String[] args) {
        new CommandLine(new Process()).execute(args);
    }
}
