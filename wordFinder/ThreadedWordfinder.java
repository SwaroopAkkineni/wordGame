package wordFinder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadedWordfinder implements WordFinder {
    List<Word> corpus;
    static int LETTERS_COUNT = 8;
    Set<String> letters;

    public ThreadedWordfinder() {
        this.corpus = new ArrayList<>();
        parseWords();
        this.letters = randomSetOfLetters();
    }

    public void startProgram() {
        System.out.println("Welcome to the dictionary program");
        System.out.println("print longest word with: " + letters);

        boolean exit = false;
        while (!exit) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a word to check if it is in the dictionary: ");
            String word = scanner.nextLine();

            if (!wordIsMadeOfLetters(word)) {
                System.out.println("Word must be made of letters: " + letters);
                continue;
            }

            try {
                if (new MultiThreadedSearch().search(word, corpus)) {
                    System.out.println("The word is in the dictionary");
                } else {
                    System.out.println("The word is not in the dictionary");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Do you want to continue? (y/n)");
            String exitString = scanner.nextLine();
            if (exitString.equals("n")) {
                scanner.close();
                exit = true;
            }
        }
    }

    private boolean wordIsMadeOfLetters(String word) {
        for (char c : word.toCharArray()) {
            if (!letters.contains(String.valueOf(c))) {
                return false;
            }
        }
        return true;
    }

    private Set<String> randomSetOfLetters() {
        Set<String> letters = new HashSet<>();
        for (int i = 0; i < LETTERS_COUNT; i++) {
            letters.add(String.valueOf((char) (97 + (int) (Math.random() * 26))));
        }
        return letters;
    }

    private void parseWords() {
        try {
            Scanner scanner = new Scanner(new File("./corpeus.txt"));
            while (scanner.hasNext()) {
                corpus.add(new Word(scanner.next()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
