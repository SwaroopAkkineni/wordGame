package wordFinder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadedWordfinder implements WordFinder {
    List<String> corpus;
    static int THREAD_COUNT = 10;

    public ThreadedWordfinder() {
        this.corpus = new ArrayList<>();
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

            try {
                if (multiThreadedSearch(word)) {
                    System.out.println("The word is in the dictionary");
                } else {
                    System.out.println("The word is not in the dictionary");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Do you want to exit? (y/n)");
            String exitString = scanner.nextLine();
            if (exitString.equals("y")) {
                exit = true;
            }
        }
    }

    private boolean searchForWord(String word) {
        return simpleSearch(word);
    }

    private boolean multiThreadedSearch(String word) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;

        List<Future<Boolean>> resultList = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            // executor.submit(new Task(i, corpus, word));
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

    private boolean simpleSearch(String word) {
        for (String w : corpus) {
            if (w.equals(word)) {
                return true;
            }
        }
        return false;
    }

    private void parseWords() {
        try {
            Scanner scanner = new Scanner(new File("./corpeus.txt"));
            while (scanner.hasNext()) {
                corpus.add(scanner.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Task implements Callable<Boolean> {
        int section;
        List<String> corpus;
        String word;

        public Task(int section, List<String> corpus, String word) {
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
                    if (corpus.get(i).equals(word)) {
                        // System.out.println("Found word: " + word + " in section: " + section);
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
