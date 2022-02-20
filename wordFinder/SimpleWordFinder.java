package wordFinder;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SimpleWordFinder implements WordFinder {

    private Set<String> words;

    public SimpleWordFinder() {
        this.words = new HashSet<>();
        parseWords();
    }

    @Override
    public void startProgram() {
        System.out.println("Welcome to the dictionary program");
        boolean exit = false;
        while (!exit) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a word to check if it is in the dictionary: ");
            String word = scanner.nextLine();
            if (words.contains(word)) {
                System.out.println("The word is in the dictionary");
            } else {
                System.out.println("The word is not in the dictionary");
            }

            System.out.println("Do you want to exit? (y/n)");
            String exitString = scanner.nextLine();
            if (exitString.equals("n")) {
                exit = true;
            }
        }

    }

    private void parseWords() {
        try {
            Scanner scanner = new Scanner(new File("./corpeus.txt"));
            while (scanner.hasNext()) {
                words.add(scanner.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
