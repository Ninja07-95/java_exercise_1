import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class TextAnalyzer {
    
    public static void main(String[] args) throws IOException {
        if (args.length > 0 && args[0].equals("freq")) {
            if (args.length < 2) {
                System.err.println("Usage: java TextAnalyzer freq <file>");
                return;
            }

            String filePath = args[1];
            Path path = Paths.get(filePath);

            try {
                String text = Files.readString(path);
                analyzeText(text);
            } catch (IOException e) {
                System.err.println("Unreadable file: " + e.getClass().getName() + ", " + e.getMessage());
            }
        }
    }

    public static void analyzeText(String text) {
        Map<String, Long> wordFrequencies = Arrays.stream(text.toLowerCase().replaceAll("[^a-zA-Z ]", "").split("\\s+"))
                .filter(word -> !word.isBlank())
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        String topThreeWords = wordFrequencies.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .map(entry -> entry.getKey())
                .collect(Collectors.joining(" "));

        System.out.println(topThreeWords);
    }
}
