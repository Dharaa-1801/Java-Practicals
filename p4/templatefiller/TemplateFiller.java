import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateFiller {
    private static final Pattern PLACEHOLDER_PATTERN =
            Pattern.compile("\\{(\\w+)\\}");

    private TemplateFiller() {
        // Utility class; objects are not required.
    }

    public static String fill(String template, String[] names, String[] values) {
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(template);
        StringBuilder result = new StringBuilder();
        int previousEnd = 0;

        while (matcher.find()) {
            result.append(template, previousEnd, matcher.start());
            result.append(findValue(matcher.group(1), names, values));
            previousEnd = matcher.end();
        }

        result.append(template, previousEnd, template.length());
        return result.toString();
    }

    private static String findValue(String name, String[] names, String[] values) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(name)) {
                return values[i];
            }
        }
        return "[?]";
    }
}
