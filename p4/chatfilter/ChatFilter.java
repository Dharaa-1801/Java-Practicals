public class ChatFilter {
    private ChatFilter() {
        // Utility class; objects are not required.
    }

    public static String filter(String[] logLines, String keyword) {
        StringBuilder report = new StringBuilder();
        int matchCount = 0;

        for (String line : logLines) {
            // Keep the message as one part, even when it contains spaces.
            String[] parts = line.split(" ", 3);
            if (parts.length < 3) {
                continue;
            }

            String time = parts[0];
            String user = parts[1];
            String message = parts[2];

            if (message.toLowerCase().contains(keyword.toLowerCase())) {
                matchCount++;
                report.append(time)
                        .append(" ")
                        .append(user)
                        .append(": ")
                        .append(message)
                        .append(System.lineSeparator());
            }
        }

        return "Matches: " + matchCount + System.lineSeparator() + report;
    }
}
