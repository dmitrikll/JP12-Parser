import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookAnalyzer {

    public void getAnalyze (String bookName) {
        Path bookPath = Paths.get("src", bookName + ".txt");
        if (!Files.exists(bookPath)) {
            System.out.println("Book \"" + bookName + "\" not found");
            return;
        }

        try {
            // розділення рядка на масив слів за пробілами
            String bookContent = Files.readString(bookPath);
            String[] words = bookContent.split("\\s+");

            // видалення пунктуації,відбір слів
            List<String> cleanedWords = Arrays
                    .stream(words)
                    .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase())
                    .filter(word -> word.length() > 2)
                    .toList();

            // підрахунок слів
            Map<String, Integer> wordCountMap = new HashMap<>();
            for (String word : cleanedWords) {
                wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
            }

            // отримання 10 найуживаніших слів
            List<Map.Entry<String, Integer>> topWords = wordCountMap
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(10)
                    .toList();

            // друк статистики
            System.out.println("Statistics for the book \"" + bookName + "\":");
            for (Map.Entry<String, Integer> entry : topWords) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " times.");
            }
            System.out.println("Total number of unique words: " + wordCountMap.size());

            // запис у файл
            String outputFileName = bookName.replace(bookName, bookName + "_statistic.txt");
            try (PrintWriter writer = new PrintWriter(outputFileName)) {
                for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                    writer.println(entry.getKey() + " -> " + entry.getValue());
                }
                writer.println("Total number of words: " + cleanedWords.size());
            } catch (IOException e) {
                System.err.println("Error when writing statistics to a file.");
            }

        } catch (IOException e) {
            System.err.println("Error reading a file.");
        }
    }
}
