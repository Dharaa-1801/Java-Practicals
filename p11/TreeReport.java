import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

// Walks a sample folder tree and writes a report for every regular file.

public class TreeReport {
    private static final Path REPORT_FILE = Path.of("p11", "tree-report.txt");

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter folder path (press Enter for p11/sample-tree): ");
        String inputPath = scanner.nextLine().trim();
        Path rootDirectory = inputPath.isEmpty()
                ? Path.of("p11", "sample-tree")
                : Path.of(inputPath);
        if (!Files.exists(rootDirectory)) {
            Files.createDirectories(rootDirectory);
            createSampleTree(rootDirectory);
        } else if (!Files.isDirectory(rootDirectory)) {
            throw new IllegalArgumentException("Path is not a folder: " + rootDirectory);
        }

        try (Stream<Path> paths = Files.walk(rootDirectory)) {
            List<String> reportLines = paths
                    .filter(Files::isRegularFile)
                    .sorted(Comparator.naturalOrder())
                    .map(TreeReport::formatFileDetails)
                    .toList();

            Files.write(REPORT_FILE, reportLines);
            System.out.println("Report written to " + REPORT_FILE);
            System.out.println("Files reported: " + reportLines.size());
            for (String line : reportLines) {
                System.out.println(line);
            }
        }
    }

    private static String formatFileDetails(Path file) {
        try {
            BasicFileAttributes attributes = Files.readAttributes(
                    file, BasicFileAttributes.class);
            Instant modified = attributes.lastModifiedTime().toInstant();
            return file + " | " + attributes.size() + " bytes | modified " + modified;
        } catch (IOException exception) {
            throw new IllegalStateException("Could not read attributes for " + file, exception);
        }
    }

    private static void createSampleTree(Path rootDirectory) throws IOException {
        Path reportsDirectory = rootDirectory.resolve("reports");
        Path sourceDirectory = rootDirectory.resolve("source");
        Files.createDirectories(reportsDirectory);
        Files.createDirectories(sourceDirectory);
        Files.writeString(rootDirectory.resolve("README.txt"), "Sample folder tree\n");
        Files.writeString(reportsDirectory.resolve("summary.txt"), "Items: 3\n");
        Files.writeString(sourceDirectory.resolve("Main.java"), "class Main {}\n");
    }
}
