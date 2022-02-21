package dailyLetters;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DailyLettersImpl implements GetDailyLetters {
    int LETTERS_COUNT = 10;
    final String[] CONSONANTS;
    final String[] VOWELS;

    public DailyLettersImpl() {
        this.CONSONANTS = new String[] {
                "b", "c", "d", "f", "g",
                "h", "j", "k", "l", "m",
                "n", "o", "p", "q", "r",
                "s", "t", "v", "w", "x", "y",
                "z"
        };
        this.VOWELS = new String[] {
                "a", "e", "i", "o", "u"
        };
    }

    @Override
    public Set<String> getDailyLetters() {
        var letters = new HashSet<String>();
        int numberOfConsonants = (int) (Math.random() * (1)) + 7;

        letters.addAll(generateRandomConsonants(numberOfConsonants));
        letters.addAll(generateRandomVowels(LETTERS_COUNT - numberOfConsonants));

        return letters;
    }

    private Set<String> generateRandomConsonants(int numberOfConsonants) {
        Set<String> consonants = new HashSet<>();
        var rand = new Random();

        while (consonants.size() < numberOfConsonants) {
            var randomConsonant = CONSONANTS[rand.nextInt(CONSONANTS.length)];
            consonants.add(randomConsonant);
        }
        return consonants;
    }

    private Set<String> generateRandomVowels(int numberOfVowels) {
        Set<String> vowels = new HashSet<>();
        var rand = new Random();

        while (vowels.size() < numberOfVowels) {
            var randomVowel = VOWELS[rand.nextInt(VOWELS.length)];
            vowels.add(randomVowel);
        }
        return vowels;
    }

}
