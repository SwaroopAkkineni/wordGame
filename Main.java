import dailyLetters.DailyLettersImpl;
import dailyWords.DailyWordsImpl;

public class Main {
    public static void main(String[] args) {
        // generate daily letters
        var dailyLettersImpl = new DailyLettersImpl();
        var dailyLetters = dailyLettersImpl.getDailyLetters();
        System.out.println("Daily letters: " + dailyLetters);

        // Get all daily words
        var dailyWordsImpl = new DailyWordsImpl();
        var dailyWords = dailyWordsImpl.getDailyWords(dailyLetters);
        System.out.println("  Daily words: ");
        for (String dailyWord : dailyWords) {
            System.out.println(dailyWord);
        }

    }
}
