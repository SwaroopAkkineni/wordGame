package wordFinder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.io.File;

public class FindAllWords {
    List<Word> corpus;
    static int LETTERS_COUNT = 8;
    Set<String> letters;

    public FindAllWords() {
        this.corpus = new ArrayList<>();
        parseWords();
        this.letters = randomSetOfLetters();
    }

    public void startProgram() {
        System.out.println("Welcome to the dictionary program");
        System.out.println("print longest word with: " + letters);

        var findAllWords = findAllWordsThatFitLetters(letters);
        System.out.println("----------------------------------------------------");
        System.out.println("All words that fit letters: ");
        for (Word word : findAllWords) {
            System.out.println(word.word);
        }
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

    private Set<String> randomSetOfLetters() {
        Set<String> letters = new HashSet<>();
        for (int i = 0; i < LETTERS_COUNT; i++) {
            letters.add(String.valueOf((char) (97 + (int) (Math.random() * 26))));
        }
        return letters;
    }

    private List<Word> findAllWordsThatFitLetters(Set<String> letters) {
        List<Word> words = new ArrayList<>();
        for (Word word : corpus) {
            if (wordFitsLetters(word, letters)) {
                words.add(word);
            }
        }
        return words;
    }

    private boolean wordFitsLetters(Word word, Set<String> letters) {
        for (String letter : word.letters) {
            if (!letters.contains(letter)) {
                return false;
            }
        }
        return true;
    }
}
