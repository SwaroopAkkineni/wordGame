package dailyWords;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import corpus.Corpus;

public class DailyWordsImpl implements GetDailyWords {
    int THREAD_COUNT = 10;

    public List<String> getDailyWords(Set<String> pattern) {
        var corpus = new Corpus();

        List<String> dailyWords = new ArrayList<>();
        for (String word : corpus.getCorpus()) {
            if (wordFitsPattern(word, pattern)) {
                dailyWords.add(word);
            }
        }
        return dailyWords;
    }

    private boolean wordFitsPattern(String word, Set<String> pattern) {
        for (String letter : word.split("")) {
            if (!pattern.contains(letter)) {
                return false;
            }
        }
        return true;
    }

}
