import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

// Creates sample logs, then counts lines and keyword matches across all logs.

public class LogAnalyzer {
    private static final Path LOG_DIRECTORY = Path.of("p11", "logs");
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();
        if (keyword.isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be empty");
        }

        createSampleLogs();

        int totalLines = 0;
        int matchingLines = 0;
        try (Stream<Path> files = Files.list(LOG_DIRECTORY)) {
            for (Path file : files.filter(Files::isRegularFile).sorted().toList()) {
                long size = Files.size(file);
                int fileLines = 0;
                int fileMatches = 0;
                try (Stream<String> lines = Files.lines(file)) {
                    List<String> contents = lines.toList();
                    for (String line : contents) {
                        fileLines++;
                        if (line.contains(keyword)) {
                            fileMatches++;
                        }
                    }
                }
                totalLines += fileLines;
                matchingLines += fileMatches;
                System.out.println(file.getFileName() + ": " + size + " bytes, "
                        + fileLines + " lines, " + fileMatches + " matching lines");
            }
        }

        System.out.println("Total lines: " + totalLines);
        System.out.println("Lines containing \"" + keyword + "\": " + matchingLines);
    }

    private static void createSampleLogs() throws IOException {
        Files.createDirectories(LOG_DIRECTORY);
        Files.write(LOG_DIRECTORY.resolve("application-1.log"), List.of(
                "INFO Application started",
                "ERROR Database unavailable",
                "INFO Retrying connection"));
        Files.write(LOG_DIRECTORY.resolve("application-2.log"), List.of(
                "INFO User logged in",
                "WARN Disk space is low",
                "ERROR Request failed",
                "INFO Application stopped"));
    }
}
