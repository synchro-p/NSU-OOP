package nsu.fit.oop;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

@Command(name = "notebook", mixinStandardHelpOptions = true)
class Process implements Callable<Integer> {
    @Override
    public Integer call() {
        Json fileProcessor = new Json(filename);
        if (addArgs != null) {
            fileProcessor.addEntry(addArgs[0],addArgs[1]);
            return 0;
        }
        else if (removeName != null) {
            fileProcessor.removeEntry(removeName);
            return 0;
        }
        else if (showArguments != null) {
            if (showArguments.length == 0) {
                fileProcessor.showAll();
            }
            else if (showArguments.length == 1) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                LocalDateTime lower = LocalDateTime.parse(showArguments[0], formatter);
                LocalDateTime upper = LocalDateTime.MAX;
                ArrayList<String> filters = new ArrayList<>(List.of(""));
                fileProcessor.showFiltered(lower,upper,filters);
            }
            else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                LocalDateTime lower = LocalDateTime.parse(showArguments[0], formatter);
                LocalDateTime upper = LocalDateTime.parse(showArguments[1], formatter);
                ArrayList<String> filters = new ArrayList<>(Arrays.asList(
                        Arrays.copyOfRange(showArguments, 2, showArguments.length - 1)));
                fileProcessor.showFiltered(lower, upper, filters);
            }
            return 0;
        }
        else return 0;
    }
    @Option(arity = "2..2", names = "-add", description = "Adds an entry to list and exits")
    String[] addArgs;

    @Option(arity = "1", names = "-rm", description = "Removes all entries with a specified name and exits")
    String removeName;

    @Option(arity = "0..*", names = "-show", description = """
            * Shows all entries between two date-times which contain 1+ filter words and exits.
            * With 1 argument - shows all entries after a date
            * With no arguments - shows all entries""")
    String[] showArguments;

    @Option(arity = "1",names = "-f",description = "Change name of output file (without the .json extension")
    String filename = "notebook";
}

public class Notebook {
    public static void main(String[] args) {
        new CommandLine(new Process()).execute(args);
    }
}
