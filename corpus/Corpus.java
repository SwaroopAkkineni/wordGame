package corpus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Corpus {
    List<String> corpus;

    public Corpus() {
        this.corpus = new ArrayList<>();
        loadCoprus();
    }

    private void loadCoprus() {
        try {
            Scanner scanner = new Scanner(new File("./corpeus.txt"));
            while (scanner.hasNext()) {
                corpus.add(scanner.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getCorpus() {
        return corpus;
    }
}
