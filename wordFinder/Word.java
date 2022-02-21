package wordFinder;

import java.util.HashSet;
import java.util.Set;

class Word {
    String word;
    int length;
    Set<String> letters;

    public Word(String word) {
        this.word = word;
        this.length = word.length();
        this.letters = new HashSet<>();
        for (char c : word.toCharArray()) {
            letters.add(String.valueOf(c));
        }
    }

    public boolean fits(Set<String> letters2) {
        return letters.contains(letters2);
    }
}