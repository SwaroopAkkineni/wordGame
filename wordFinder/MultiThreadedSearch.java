package wordFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadedSearch {
    static int THREAD_COUNT = 10;

    public boolean search(String word, List<Word> corpus) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        List<Future<Boolean>> resultList = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            Future<Boolean> result = executor.submit(new Task(i, corpus, word));
            resultList.add(result);
        }

        for (Future<Boolean> future : resultList) {
            try {
                if (future.get()) {
                    executor.shutdown();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // shut down the executor service now
        executor.shutdown();
        return false;
    }

    static class Task implements Callable<Boolean> {
        int section;
        List<Word> corpus;
        String word;

        public Task(int section, List<Word> corpus, String word) {
            this.section = section;
            this.corpus = corpus;
            this.word = word;
        }

        public Boolean call() throws Exception {
            try {
                int onePart = corpus.size() / THREAD_COUNT;
                int startIndex = section * onePart;
                int endIndex = onePart * (section + 1);

                for (int i = startIndex; i < endIndex; i++) {
                    if (corpus.get(i).word.equals(word)) {
                        return true;
                    }
                }

                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
