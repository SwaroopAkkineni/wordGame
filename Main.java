import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> words = parseWords();

        for (String word : words) {
            System.out.println(word);
        }
    }

    private static Set<String> parseWords() {
        Set<String> words = new HashSet<String>();

        try {
            Scanner scanner = new Scanner(new File("./corpeus.txt"));
            while (scanner.hasNext()) {
                words.add(scanner.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return words;
    }
}
